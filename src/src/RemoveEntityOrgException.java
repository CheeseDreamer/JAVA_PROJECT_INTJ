public class RemoveEntityOrgException extends Exception{
    public RemoveEntityOrgException(){
        super("Entity does not exist.");
    }
    public RemoveEntityOrgException(String message){
        super(message);
    }
}
