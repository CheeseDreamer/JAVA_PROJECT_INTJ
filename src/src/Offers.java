public class Offers extends RequestDonationList
{
    public Offers()
    {
    }
    //Commit is unfinished...
    public void commit(RequestDonation rdEntity, Organization org){
        boolean found = false;
        System.out.println("isAdmin: "+org.getAdmin().getIsAdmin());//can alse return false when set
        //System.out.println(admin.isAdminPhone(this));//always returns true
        //if(!org.getAdmin().getIsAdmin()) {//If you are not the admin
            if(rdEntity.getEntity().getType().equals("Material")){
                System.out.println("addCurrentDonations Reached here! Material");
                for(int i=0;i<org.getCurrentDonations().getRdEntities().get(0).size();i++) {
                    System.out.println("Reched loop, Material");
                    //if(RequestDonation.compare(rdEntity,org.getCurrentDonations().getRdEntities().get(0).get(i))){
                    if (rdEntity.getId() == org.getCurrentDonations().getRdEntities().get(0).get(i).getId()){
                        //Never reaches here, if it goes here, all is good!
                        System.out.println("addCurrentDonations Reached here! ID Check, Material");
                        //currentDonations.add(rdEntity,this);
                        org.getCurrentDonations().getRdEntities().get(0).get(i).addQuantity(rdEntity.getQuantity());
                        found=true;
                        break;//Might be unnecessary
                    }
                }
            }else if(rdEntity.getEntityType().equals("Service")){
                System.out.println("addCurrentDonations Reached here! Service");
                for(int i = 0; i<org.getCurrentDonations().getRdEntities().get(1).size();i++){
                    System.out.println("Reched loop, Service");
                    if (rdEntity.getId() == org.getCurrentDonations().getRdEntities().get(1).get(i).getId()){
                        System.out.println("addCurrentDonations Reached here! ID Check, Service");
                        org.getCurrentDonations().getRdEntities().get(1).get(i).addQuantity(rdEntity.getQuantity());
                        found=true;
                        break;//Might be unnecessary
                    }
                }
            }
            //currentDonations.add(rdlEntity);
            if (found) {
                System.out.println("Entity not found in CurrentDonations of Organization");
            }
        //}else {//If you are admin, also since you start with true isAdmin it initializes
        /*    System.out.println("Initialize currentDonations");
            //getCurrentDonations().add(rdEntity, this);
            if(rdEntity.getEntityType().equals("Material")) {
                org.getCurrentDonations().getRdEntities().get(0).add(rdEntity);
            }else if(rdEntity.getEntityType().equals("Service")){
                org.getCurrentDonations().getRdEntities().get(1).add(rdEntity);
            }
         */
        //}
    }
        //org.addCurrentDonations(rdEntities);
        //if successful, prob done with search function
        //rdEntities.reset();
}