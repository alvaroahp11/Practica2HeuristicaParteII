package exceptions;

public class ManageStateException extends Exception {
    
    String message;

    public ManageStateException(String message) {

        this.message = message;
    }

    public String getMessage() {

        return this.message;
    }
}
