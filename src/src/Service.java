public class Service extends Entity {
    public Service(){}
    public Service(String name, String description, int id)
    {
        super(name, description, id);
    }

    @Override
    public double getLevel(Beneficiary ben) { return -1; }

    public String getType(){
        return "Service";
    }
    @Override
    public String getDetails(){
        return super.getDetails() + "\nDonation type: "+getType();}
}
