import java.util.ArrayList;
import java.util.Scanner;

public class Beneficiary extends User
{
    private int noPersons = 1;
    private static int pos;
    public  Beneficiary(){ }
    public Beneficiary(String name, String phone, int noPersons)
    {
        super(name,phone);
        this.noPersons = noPersons; // Make an exception, or check for correct input with if statements
    }

    private RequestDonationList receivedList = new RequestDonationList();
    private Requests requestsList = new Requests();

    public void setNoPersons(int noPersons){this.noPersons=noPersons;}
    public int getNoPersons(){return noPersons;}

    public boolean isBeneficiaryPhone(Organization org){
        for(int i = 0; i<org.getBeneficiaryList().size();i++){
            if(getPhone().equals((org.getBeneficiaryList().get(i).getPhone()))){
                setName(org.getBeneficiaryList().get(i).getName());
                pos=i;
                return true;
            }
        }
        return false;
    }
    public static int getPos(){return pos;}

    //"WRAPPER METHODS"(copy-pasting)
    //Need to finish RequestDonationList and Requests methods

    //Make the different methods for the 2 different ArrayLists

    public RequestDonationList getReceivedList(){
        return receivedList;
    }
    public Requests getRequestsList(){return requestsList;}

    public void addRequest(int choice, Organization org, Scanner sc){
        boolean subMenuLoop=false;
        RequestDonation reqDon;
        int entityID;
        int requestQuantity;
        String confirmRequest;
        switch (choice){
            case 1://[1]Materials
                System.out.println("Insert the id of the Material you want to request: ");
                System.out.print("id: ");
                entityID = sc.nextInt();
                reqDon = new RequestDonation(new Material(),0);
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
                if(entityID != -1) {//if requestDonation found in organization do below stuff
                    System.out.println("Insert how much you want to take");
                    System.out.print("Quantity: ");
                    requestQuantity = sc.nextInt();
                    sc.nextLine();//Clear the buffer
                    System.out.print("Confirm?(y/n): ");
                    confirmRequest = sc.nextLine();
                    if (confirmRequest.equals("y") || confirmRequest.equals("Y")) {
                        System.out.println("you took:\n\t" + reqDon.getEntity().getEntityInfo() + " quantity: " + requestQuantity);
                        //the add has all the necessary logic, for adding either quantity or new Entities, and validRequestDonation
                        getRequestsList().add(new RequestDonation(reqDon.getEntity(), requestQuantity), org);
                    }
                }else{//if requestDonation not found in organization
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
                    System.out.println("Insert how much you want to take");
                    System.out.print("Quantity: ");
                    requestQuantity = sc.nextInt();
                    sc.nextLine();//Clear the buffer
                    System.out.print("Confirm?(y/n): ");
                    confirmRequest = sc.nextLine();
                    if (confirmRequest.equals("y") || confirmRequest.equals("Y")) {
                        System.out.println("you took:\n\t" + reqDon.getEntity().getEntityInfo() + ", " + requestQuantity + " Hours");
                        getRequestsList().add(new RequestDonation(reqDon.getEntity(), requestQuantity), org);
                    }
                }else {//if requestDonation not found in organization
                    System.out.println("Service ID does not exist within Organization");
                    sc.nextLine();//Clear Buffer
                }
                break;
            default:
                System.out.println("Invalid Choice, how?!?");
                break;
        }
    }

}
