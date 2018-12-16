package br.com.jesus.challenge.sienge.services;

import br.com.jesus.challenge.sienge.models.Transport;

import java.util.List;
import java.util.Optional;

public interface TransportService {
    List<Transport> findAll();
    Optional<Transport> findById(Long id);
    Transport save(Transport transport);
    void delete(Long id);
    void delete(Transport transport );
}
