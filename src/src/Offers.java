public class Offers extends RequestDonationList
{
    public Offers()
    {
    }
    public void commit(Organization org, RequestDonation rdEntities){
        org.addCurrentDonations(rdEntities);
        //if successful, prob done with search function
        //rdEntities.reset();
    }
}