package enicarthage.Projetweb.service;


public class DeadlineNotFoundException extends RuntimeException {

    public DeadlineNotFoundException(String message) {
        super(message);
    }
}