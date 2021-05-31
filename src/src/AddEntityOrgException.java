public class AddEntityOrgException extends Exception{
    public AddEntityOrgException(){       // Entity already exists
        super("The entered entity already exists.");
    }

    public AddEntityOrgException(String message){       // Entity already exists
        super(message);
    }
}
