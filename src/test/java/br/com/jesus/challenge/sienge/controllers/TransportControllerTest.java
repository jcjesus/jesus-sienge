package br.com.jesus.challenge.sienge.controllers;

import br.com.jesus.challenge.sienge.JesusSiengeApplication;
import br.com.jesus.challenge.sienge.JesusSiengeApplicationTests;
import br.com.jesus.challenge.sienge.models.Transport;
import br.com.jesus.challenge.sienge.services.TransportService;
import br.com.jesus.challenge.sienge.utils.TestUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ReadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

import static com.jayway.jsonpath.JsonPath.using;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JesusSiengeApplicationTests.class, TransportController.class})
//@ContextConfiguration(classes = { JesusSiengeApplicationTests.class, TransportController.class })
@WebAppConfiguration
public class TransportControllerTest {

    private Logger logger = LoggerFactory.getLogger(TransportControllerTest.class);

    @Autowired
    private TransportService transportService;

    @Autowired
    private TransportController transportController;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private List<Transport> transportList;

    Configuration conf = Configuration.builder().options(Option.AS_PATH_LIST).build();

    @After
    public void tearDown() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

//        MockitoAnnotations.initMocks(this);
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        Transport truckTrunk = Transport.builder()
                .id(1L)
                .vehicleDescription("Caminhão Baú")
                .multiplierFactorCost(1.0f).build();

        Transport truckBucket = Transport.builder()
                .id(2L)
                .vehicleDescription("Caminhão Caçamba")
                .multiplierFactorCost(1.05f).build();

        Transport truckCart = Transport.builder()
                .id(3L)
                .vehicleDescription("Carreta")
                .multiplierFactorCost(1.12f).build();

        transportList = new ArrayList<>();
        transportList.addAll(Arrays.asList(truckTrunk,truckBucket, truckCart));

        when(transportService.findAll()).thenReturn(transportList);

    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webApplicationContext.getBean("transportController"));
    }

    @Test
    public void givenTransports_whenGetTransports_thenReturnHalAndJsonArray()
            throws Exception {


        final ResultActions resultActions = this.mockMvc.perform(get("/api/transports").contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().is(200));
        assertEquals("application/hal+json;charset=UTF-8", resultActions.andReturn().getResponse().getContentType());
    }

    @Test
    public void givenTransports_whenGetTransports_thenReturnExpectSize()
            throws Exception {

        final int expectedSize = transportList.size();

        final ResultActions resultActions = this.mockMvc.perform(get("/api/transports").contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().is(200));
        resultActions.andExpect(jsonPath("$.length()").value(expectedSize));

    }

    @Test
    public void givenTransports_whenGetTransports_thenReturnExpectAnyVehicleDescription()
            throws Exception {

        final String[] expectedVehicleDescription = transportList.stream().map(Transport::getVehicleDescription)
                .collect(Collectors.toList()).toArray(new String[transportList.size()]);

        final ResultActions resultActions = this.mockMvc.perform(get("/api/transports").contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().is(200));
        resultActions.andExpect(jsonPath("$._embedded.transports[*].vehicleDescription", containsInAnyOrder(expectedVehicleDescription)));

    }

    @Test
    public void givenTransports_whenGetTransports_thenReturnVehicleDescriptionIsCaminhaoBau()
            throws Exception {


        final ResultActions resultActions = this.mockMvc.perform(get("/api/transports/1").contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().is(200));

        final ReadContext ctx = JsonPath.parse(resultActions.andReturn().getResponse().getContentAsString());
        final String vehicleDescription  = ctx.read("$.vehicleDescription").toString();

        assertEquals(transportList.get(0).getVehicleDescription(), vehicleDescription);
    }

    @Test
    public void givenTransports_whenGetTransports_thenReturnMultiplierFactorCostNotIsOneDotZeroFive()
            throws Exception {

        final ResultActions resultActions = this.mockMvc.perform(get("/api/transports/1").contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().is(200));

        final ReadContext ctx = JsonPath.parse(resultActions.andReturn().getResponse().getContentAsString());
        final Float multiplierFactorCost  = Float.parseFloat(ctx.read("$.multiplierFactorCost").toString());

        assertNotEquals(transportList.get(1).getMultiplierFactorCost(), multiplierFactorCost);
    }

    @Test
    public void givenTransports_whenGetTransports_thenReturnMultiplierFactorCostIsOneDotZeroFive()
            throws Exception {

        final ResultActions resultActions = this.mockMvc.perform(get("/api/transports/2").contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().is(200));

        final ReadContext ctx = JsonPath.parse(resultActions.andReturn().getResponse().getContentAsString());
        final Float multiplierFactorCost  = Float.parseFloat(ctx.read("$.multiplierFactorCost").toString());

        assertEquals(transportList.get(1).getMultiplierFactorCost(), multiplierFactorCost);
    }

    @Test
    public void saveTransport_whenPostTransportSave_thenReturnTransportSavedIsCreated(){

        final Transport t = Transport.builder().multiplierFactorCost(5f).vehicleDescription("Outro Caminhão").build();

        try {
            mockMvc.perform(post("/api/transports")
                    .contentType(TestUtils.APPLICATION_JSON_UTF8)
                    .content(TestUtils.convertObjectToJsonBytes(t))
            ).andExpect(status().isCreated());
        } catch (Exception e) {
            logger.error("Error saving transport test occurred.", e);
        }
    }

}