public class InsertDonatorOrgException extends Exception{
    public InsertDonatorOrgException(){
        super("Donator already exists!");
    }
    public InsertDonatorOrgException(String message){
        super(message);
    }
}
