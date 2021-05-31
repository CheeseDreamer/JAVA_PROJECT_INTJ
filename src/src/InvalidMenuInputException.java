public class InvalidMenuInputException extends Exception {
    public InvalidMenuInputException() {
        super("The choice you've entered is wrong/invalid.");
    }

    public InvalidMenuInputException(String message){
        super(message);
    }
}
