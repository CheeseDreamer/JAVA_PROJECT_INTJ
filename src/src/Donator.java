import java.util.ArrayList;
import java.util.Scanner;

public class Donator extends User
{
    private Offers offersList=new Offers(); //Maybe do it 2D ArrayList
    private static int pos;
    public Donator(){}
    public Donator(String name, String phone)
    {
        super(name,phone);
    }

    //MENU NECESSARY METHOD
    public boolean isDonatorPhone(Organization org){
        for(int i = 0; i<org.getDonatorList().size();i++){
            if(getPhone().equals(org.getDonatorList().get(i).getPhone())){
                setName(org.getDonatorList().get(i).getName());
                pos = i;
                return true;
            }
        }
        return false;
    }
    public static int getPos(){return pos;}
    //ADD METHOD WRAPPER
    public void addOffer(int choice, Organization org, Scanner sc){

        boolean subMenuLoop=false;
        RequestDonation reqDon;
        int entityID;
        int donationQuantity;
        String confirmDonation;
        String donateChoice;
        switch (choice) {
            //READ THIS!!
            /*We place the materials at the beginning of the list
            and the services at the end of the list*/
            case 1://[1]Materials

                System.out.println("Insert the id of the Material you want to offer: ");
                System.out.print("id: ");
                entityID = sc.nextInt();
                reqDon = new RequestDonation(new Material(), 0);
                reqDon.getEntity().setId(entityID);

                for(int i=0;i<org.getCurrentDonations().getRdEntities().get(0).size();i++) {
                    if (RequestDonation.compare(reqDon, org.getCurrentDonations().getRdEntities().get(0).get(i))) {
                        //if(reqDon.getEntity().getId()==org.getCurrentDonations().getRdEntities().get(0).get(i).getId()){
                        entityID = org.getCurrentDonations().getRdEntities().get(0).get(i).getId();
                        reqDon.setEntity(org.getCurrentDonations().getRdEntities().get(0).get(i).getEntity());
                        break;
                    } else {
                         entityID = -1;
                    }
                }
                System.out.println("ENTITY ID: "+ entityID);
                if(entityID != -1){//if requestDonation found in organization do below stuff
                    System.out.println("Insert how much you want to give");
                    System.out.print("Quantity: ");
                    donationQuantity = sc.nextInt();
                    sc.nextLine();//Clear the buffer
                    System.out.print("Confirm?(y/n): ");
                    confirmDonation = sc.nextLine();
                    if (confirmDonation.equals("y") || confirmDonation.equals("Y")) {
                        System.out.println("you gave:\n\t" + reqDon.getEntity().getEntityInfo() + " quantity: " + donationQuantity);
                        //the add has all the necessary logic, for adding either quantity or new Entities
                        getOffersList().add(new RequestDonation(reqDon.getEntity(), donationQuantity),org);
                    }
                }else {//if requestDonation not found in organization
                    System.out.println("Material ID does not exist within Organization");
                    sc.nextLine();//Clear Buffer
                }
                break;
            case 2://[2]Services
                System.out.println("Insert the id of the Service you want to offer: ");
                System.out.print("id: ");
                entityID = sc.nextInt();
                reqDon = new RequestDonation(new Service(), 0);
                reqDon.getEntity().setId(entityID);

                for(int i=0;i<org.getCurrentDonations().getRdEntities().get(1).size();i++) {
                    if (RequestDonation.compare(reqDon, org.getCurrentDonations().getRdEntities().get(1).get(i))) {
                        entityID = org.getCurrentDonations().getRdEntities().get(1).get(i).getId();
                        reqDon.setEntity(org.getCurrentDonations().getRdEntities().get(1).get(i).getEntity());
                        break;
                    } else {
                        entityID = -1;
                    }
                }

                System.out.println("ENTITY ID: "+ entityID);
                if(entityID != -1){//if requestDonation found in organization do below stuff
                    System.out.println("Insert how much you want to give");
                    System.out.print("Quantity: ");
                    donationQuantity = sc.nextInt();
                    sc.nextLine();//Clear the buffer
                    System.out.print("Confirm?(y/n): ");
                    confirmDonation = sc.nextLine();
                    if (confirmDonation.equals("y") || confirmDonation.equals("Y")) {
                        System.out.println("you gave:\n\t" + reqDon.getEntity().getEntityInfo() + " quantity: " + donationQuantity);
                        //offersList.add(new RequestDonation(reqDonMat.getEntity(), reqDonMat.getQuantity()),org);
                        //offersList.getRdEntities().get(1).add(new RequestDonation(reqDon.getEntity(), donationQuantity));
                        getOffersList().add(new RequestDonation(reqDon.getEntity(), donationQuantity),org);
                    }
                }else {//if requestDonation not found in organization
                    System.out.println("Service ID does not exist within Organization");
                    sc.nextLine();//Clear Buffer
                }

                break;
            default:
                System.out.println("Invalid Choice!!! How is that even possible?");
                break;
        }
    }
    public Offers getOffersList(){return offersList;}
    public void showOffers() {
        /*if(!offersList.isEmpty()){
            for (var obj:offersList){
                obj.monitor();
            }
        }else{ System.out.println("You have no offers right now"); }
         */
    }
    public void commit(Organization org){//Wrapper method
        getOffersList().commit(org);
    }
}