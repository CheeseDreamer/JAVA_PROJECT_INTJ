public class Offers extends RequestDonationList
{
    public Offers() {}
    public void commit(Organization currentDonations, RequestDonationList rdEntities){
        currentDonations.addCurrentDonations(rdEntities);
        //if successful, prob done with search function
        rdEntities.reset();
    }
}