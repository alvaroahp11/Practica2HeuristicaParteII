package Exceptions;

public class ManageSateliteException extends Exception {
    
    String message;

    public ManageSateliteException(String message) {

        this.message = message;
    }

    public String getMessage() {

        return this.message;
    }
}
