public class RequestsBeneficiaryException extends Exception{
    public RequestsBeneficiaryException(){
        super ("The entity quantity you've entered surpasses its available quantity or is of negative value!");
    }
    public RequestsBeneficiaryException(String message){
        super(message);
    }
}
