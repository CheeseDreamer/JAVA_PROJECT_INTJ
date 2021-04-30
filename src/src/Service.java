public class Service extends Entity
{
    public Service(String name, String description, int id)
    {
        super(name, description, id);
    }
    @Override
    public String getDetails(){
        return super.getDetails() + "\nDonation type: Service";}
}
