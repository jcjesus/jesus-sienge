package br.com.jesus.challenge.sienge.controllers;

import br.com.jesus.challenge.sienge.handler.exceptions.TransportNotFoundException;
import br.com.jesus.challenge.sienge.models.Transport;
import br.com.jesus.challenge.sienge.services.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class TransportController {

    @Autowired
    private TransportService transportService;

    @GetMapping("/transports")
    public ResponseEntity<List<Transport>> retrieveAllTransports(){
        List<Transport> transportList = transportService.findAll();
        return new ResponseEntity<List<Transport>>(transportList, HttpStatus.OK);
    }

    @GetMapping("/transports/{transportId}")
    public ResponseEntity<Transport> retrieveTransportById(@PathVariable Long transportId) {
        Transport t = transportService.findById(transportId).orElseThrow(() -> new TransportNotFoundException("id-" + transportId));
        return new ResponseEntity<Transport>(t,HttpStatus.OK);
    }

    @PostMapping("/transports")
    public ResponseEntity<Void> save(@RequestBody Transport transport, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/transport/{id}").buildAndExpand(transport.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/transports")
    public ResponseEntity<Transport> update(@RequestBody Transport transport) {
        Transport t = transportService.save(transport);
        return new ResponseEntity<Transport>(t,HttpStatus.OK);
    }

    @DeleteMapping("/transports/{id}")
    public ResponseEntity<Void> deleteTransport(@PathVariable long id) {
        transportService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
