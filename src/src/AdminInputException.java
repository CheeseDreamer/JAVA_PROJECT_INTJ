public class AdminInputException extends Exception{
    public AdminInputException(){
        super (" ");                    // if entity entered by Admin already exists.
    }
    public AdminInputException(String message){
        super(message);
    }
}
