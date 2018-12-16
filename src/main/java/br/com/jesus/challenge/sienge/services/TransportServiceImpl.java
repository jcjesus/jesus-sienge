package br.com.jesus.challenge.sienge.services;

import br.com.jesus.challenge.sienge.models.Transport;
import br.com.jesus.challenge.sienge.repositories.TransportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransportServiceImpl implements TransportService {

    private Logger logger = LoggerFactory.getLogger(TransportServiceImpl.class);

    @Autowired
    private TransportRepository transportRepository;

    @Override
    public List<Transport> findAll() {
        logger.info("Senge findAll execute.");
        List<Transport> list = new ArrayList<>();
        transportRepository.findAll().forEach(t -> list.add(t));
        return list;
    }

    @Override
    public Optional<Transport> findById(Long id) {
        logger.info("Senge findById execute. id: " + id);
        return transportRepository.findById(id);
    }

    @Override
    public Transport save(Transport transport) {
        logger.info("Senge save execute. " + transport);
        return transportRepository.save(transport);
    }

    @Override
    public void delete(Long id) {
        logger.info("Senge deleteById execute. id: " + id);
        transportRepository.deleteById(id);
    }

    @Override
    public void delete(Transport transport) {
        logger.info("Senge delete execute. " + transport);
        transportRepository.delete(transport);
    }
}
