import java.util.ArrayList;

public class Donator extends User
{
    private Offers offersList;
    public Donator(String name, String phone, Offers offersList)
    {
        super(name,phone);
        this.offersList = offersList;
    }
    public void commit(Organization currentDonations, RequestDonationList rdEntities){
        currentDonations.addCurrentDonations(rdEntities);
        //if successful
        rdEntities.reset();
    }
}