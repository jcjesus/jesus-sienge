package br.com.jesus.challenge.sienge.controllers;

import br.com.jesus.challenge.sienge.JesusSiengeApplication;
import br.com.jesus.challenge.sienge.services.TransportService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.*;

@ContextConfiguration(classes = {TransportController.class, TransportService.class, JesusSiengeApplication.class})
public class TransportControllerTest {


    @After
    public void tearDown() throws Exception {
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void retrieveAllTransports() {

    }

    @Test
    public void retrieveTransportById() {
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteTarnsport() {
    }
}