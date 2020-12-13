package exceptions;

public class CosmosException extends Exception {
    
    String message;

    public CosmosException(String message) {

        this.message = message;
    }

    public String getMessage() {

        return this.message;
    }
}
