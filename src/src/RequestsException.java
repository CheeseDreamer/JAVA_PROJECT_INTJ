
public class RequestsException extends Exception{

    public RequestsException() {                        // if beneficiary doesn't deserve the quantity
        super("Unavailable entity quantity in our Organization ! ");                       //By calling super(message), we initialize the exception's error message and the base class takes care of setting up the custom message, according to the message.
    }

    public RequestsException(String message){
        super(message);
    }
}

