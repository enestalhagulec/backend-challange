package net.etg.backendchallenge.exception;

public class NoCompanyException extends RuntimeException {

    public NoCompanyException(){
        super("Company doesn't exist");
    }
}
