package br.com.jesus.challenge.sienge.loaders;

import br.com.jesus.challenge.sienge.models.Transport;
import br.com.jesus.challenge.sienge.repositories.TransportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class TransportLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private TransportRepository transportRepository;

    private Logger logger = LoggerFactory.getLogger(TransportLoader.class);


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Transport truckTrunk = Transport.builder()
                .id(1L)
                .vehicleDescription("Caminhão Baú")
                .multiplierFactorCost(1.0f).build();

        transportRepository.save(truckTrunk);
        logger.info("Saving truckTrunk: " + truckTrunk.toString());

        Transport truckBucket = Transport.builder()
                .id(2L)
                .vehicleDescription("Caminhão Caçamba")
                .multiplierFactorCost(1.05f).build();

        transportRepository.save(truckBucket);
        logger.info("Saving truckBucket: " + truckBucket.toString());

        Transport truckCart = Transport.builder()
                .id(3L)
                .vehicleDescription("Carreta")
                .multiplierFactorCost(1.12f).build();

        transportRepository.save(truckCart);
        logger.info("Saving truckCart: " + truckBucket.toString());



    }
}
