public class Offers extends RequestDonationList
{
    public Offers()
    {
    }
    //Commit is unfinished...
    public void commit(Organization org){
        boolean found = false;
        for(int i = 0; i<org.getCurrentDonations().getRdEntities().size();i++) {//0 for Materials/ 1 Services
            for (int j = 0; j < org.getCurrentDonations().getRdEntities().get(i).size(); j++) {
                for (int k = 0; k < org.getDonatorList().get(Donator.getPos()).getOffersList().getRdEntities().get(i).size(); k++) {
                    if (RequestDonation.compare(org.getDonatorList().get(Donator.getPos()).getOffersList().getRdEntities().get(i).get(k), org.getCurrentDonations().getRdEntities().get(i).get(j))) {
                        found = true;
                        org.getCurrentDonations().getRdEntities().get(i).get(j).addQuantity(org.getDonatorList().get(Donator.getPos()).getOffersList().getRdEntities().get(i).get(k).getQuantity());
                        System.out.println("Added Quantity to CurrentDonations Successfully");
                        break;
                    }
                }
            }
        }
        if(!found){System.out.println("You have no Offers to Commit");}
        else {
            for (int i = 0; i < org.getDonatorList().get(Donator.getPos()).getOffersList().getRdEntities().size(); i++) {
                org.getDonatorList().get(Donator.getPos()).getOffersList().getRdEntities().get(i).clear();
                System.out.println("Commited Successfully, OfferList Cleared");
            }
        }
    }
}