package br.com.jesus.challenge.sienge.services;

import br.com.jesus.challenge.sienge.models.Transport;
import br.com.jesus.challenge.sienge.repositories.TransportRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransportServiceImplTest {

    Transport truckTrunk = null;
    Transport truckBucket = null;
    Transport truckCart = null;
    Transport t = null;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TransportRepository transportRepository;

    @Before
    public void setUp() throws Exception {
        truckTrunk = Transport.builder()
                .vehicleDescription("Caminhão Baú")
                .multiplierFactorCost(1.0f).build();

        truckBucket = Transport.builder()
                .vehicleDescription("Caminhão Caçamba")
                .multiplierFactorCost(1.05f).build();

        truckCart = Transport.builder()
                .vehicleDescription("Carreta")
                .multiplierFactorCost(1.12f).build();

        transportRepository.save(truckTrunk);
        transportRepository.save(truckBucket);
        transportRepository.save(truckCart);

        t = Transport.builder().multiplierFactorCost(5f).vehicleDescription("other").build();

    }

    @After
    public void tearDown() throws Exception {
        transportRepository.delete(truckTrunk);
        transportRepository.delete(truckBucket);
        transportRepository.delete(truckCart);
    }

    @Test
    public void findAll() {
        List<Transport> list = new ArrayList<>();
        transportRepository.findAll().forEach(t -> list.add(t));
        assertEquals(3, list.size());
    }

    @Test
    public void findById() {
        Transport first = transportRepository.findAll().get(0);
        Optional<Transport> t = transportRepository.findById(first.getId());
        assertEquals("Caminhão Baú", t.get().getVehicleDescription());
    }

    @Test
    public void save() {
        Transport newer = transportRepository.save(t);
        assertEquals(t.getMultiplierFactorCost(), newer.getMultiplierFactorCost());
    }

    @Test
    public void delete() {
        transportRepository.delete(t);
        Optional<Transport> deleted = transportRepository.findById(t.getId());
        assertEquals(false, deleted.isPresent());
    }

    @Test
    public void deleteById() {
        Transport newer = transportRepository.save(t);
        transportRepository.flush();
        transportRepository.deleteById(newer.getId());
        boolean deleted = transportRepository.existsById(newer.getId());
        assertEquals(false, deleted);
    }
}