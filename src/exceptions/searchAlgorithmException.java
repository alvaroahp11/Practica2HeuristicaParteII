package exceptions;

public class searchAlgorithmException extends Exception {
    
    String message;

    public searchAlgorithmException(String message) {

        this.message = message;
    }

    public String getMessage() {

        return this.message;
    }
}
