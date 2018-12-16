package br.com.jesus.challenge.sienge.handler.exceptions;

public class TransportNotFoundException extends RuntimeException {
    public TransportNotFoundException(String exception) {
        super(exception);
    }

    public TransportNotFoundException(){
        super("Transporte não foi encontrado.");
    }
}
