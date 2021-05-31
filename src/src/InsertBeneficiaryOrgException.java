public class InsertBeneficiaryOrgException extends Exception{
    public InsertBeneficiaryOrgException(){
        super("Beneficiary already exists !");
    }
    public InsertBeneficiaryOrgException(String message){
        super(message);
    }
}
