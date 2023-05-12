package net.etg.backendchallenge.exception;

public class NoEmployeeException extends  RuntimeException{

    public NoEmployeeException(){
        super("Employee doesn't exist");
    }
}
