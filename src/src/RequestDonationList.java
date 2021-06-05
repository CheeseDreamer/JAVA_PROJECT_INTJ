import java.util.ArrayList;
import java.util.Scanner;

public class RequestDonationList {
    private int entityTypesCount = 2;//0 for Material, 1 for Services
    private ArrayList<ArrayList<RequestDonation>> rdEntities = new ArrayList<>(entityTypesCount);
    public RequestDonationList(){
        //Initialize rdEntities
        for(int i=0; i<entityTypesCount;i++){
            rdEntities.add(new ArrayList());
        }
    }

    //Needs to return RequestDonation
    public RequestDonation getWithID(int entityID) {//Whatever calls this, will need to check for NullPointerException
        for(int i=0; i<entityTypesCount;i++){
            int entitiesCount = rdEntities.get(i).size();
            for(int j=0;j<entitiesCount;j++){
                if(rdEntities.get(i).get(j).getId()==entityID){
                    return rdEntities.get(i).get(j);
                }
            }
        }
        System.out.println("Entity Not Found!");
        return null;
    }

    public ArrayList<ArrayList<RequestDonation>> getRdEntities() {
        return rdEntities;
    }

    public void add(RequestDonation rd, Organization org) {//is being run by offersList,...
        boolean found = false;
        //System.out.println("add function RequestDonationList Works");
        if (org.getAdmin().getIsAdmin() || org.getEntityList().get(0).contains(rd.getEntity()) || org.getEntityList().get(1).contains(rd.getEntity())) {//org.getCurrentDonations().getRdEntities().get(1).contains(rd)) {
            //System.out.println("if statement Works");
            //if you are the admin, or the Request Donation exists within the Organization
            for (int i = 0; i < entityTypesCount; i++) {//Materials or Services
                for (int j = 0; j < getRdEntities().get(i).size(); j++) {
                    if (RequestDonation.compare(rd, getRdEntities().get(i).get(j))) {//if you've already given the same stuff
                        // enhmerwsh posothtas tou rd
                        //idea: rd.search(rdEntities).addQuantity();
                        getRdEntities().get(i).get(j).addQuantity(rd.getQuantity());
                        System.out.println("Added Quantity");
                        found = true;
                        break;
                    }
                }
            }
            if(!found) {//if you are giving it for the first time
                System.out.println("Giving it for the first time");
                if (rd.getEntityType().equals("Material")) {
                    getRdEntities().get(0).add(new RequestDonation(rd.getEntity(), rd.getQuantity()));
                    System.out.println("Added Material");
                } else if (rd.getEntityType().equals("Service")) {
                    getRdEntities().get(1).add(new RequestDonation(rd.getEntity(), rd.getQuantity()));
                    System.out.println("Added Service");
                }
            }
        }else{//If you are not the admin and the request Donation is not found within the Organization
            System.out.println("Entity not found in Organization!");
            //exception
        }
    }

    public void remove(RequestDonation rd){
        for(int i =0;i<getRdEntities().size();i++) {
            if (rd.getEntityType().equals("Material")) {
                getRdEntities().get(0).remove(rd);
            } else if (rd.getEntityType().equals("Service")) {
                getRdEntities().get(1).remove(rd);

            } else System.out.println("Entity was not found in list");
            System.out.println("Successfully Deleted Offer List Request Donation Element");
        }
    }

    public void modify(int modifyChoice, int offerID, Organization org, Scanner scan) {    //process of quantity
        //modifyChoice: 1 for subtraction, 2 for addition
        int modQuantity;
        switch (modifyChoice) {
            case 1:
                System.out.print("Add Quantity: ");
                modQuantity = scan.nextInt();
                org.getDonatorList().get(Donator.getPos()).getOffersList().getWithID(offerID).addQuantity(modQuantity);
                System.out.println("Added "+ modQuantity +" quantity");
                System.out.println("[id]: "+ offerID +" [Current Quantity]: " + org.getDonatorList().get(Donator.getPos()).getOffersList().getWithID(offerID).getQuantity());
                break;
            case 2:
                System.out.print("Sub Quantity: ");
                modQuantity = scan.nextInt();
                double subtractedQuantityNum;
                if(org.getDonatorList().get(Donator.getPos()).getOffersList().getWithID(offerID).getQuantity()-modQuantity>=0) {
                    subtractedQuantityNum=modQuantity;
                    org.getDonatorList().get(Donator.getPos()).getOffersList().getWithID(offerID).subQuantity(modQuantity);
                }else{
                    subtractedQuantityNum=org.getDonatorList().get(Donator.getPos()).getOffersList().getWithID(offerID).getQuantity();
                    org.getDonatorList().get(Donator.getPos()).getOffersList().getWithID(offerID).subQuantity(org.getDonatorList().get(Donator.getPos()).getOffersList().getWithID(offerID).getQuantity());
                    System.out.println("[Request Donation Quantity is set to 0]");
                }
                System.out.println("Subtracted "+ subtractedQuantityNum +" quantity");
                System.out.println("[id]: "+ offerID +" [Current Quantity]: " + org.getDonatorList().get(Donator.getPos()).getOffersList().getWithID(offerID).getQuantity());
                break;
            case 3://Back
                break;
            default:
                break;
        }
    }

    public void monitor(){
        for(int i = 0;i<entityTypesCount;i++){      
            int entitiesCount = rdEntities.get(i).size();
            for(int j=0;j<entitiesCount;j++) {
                System.out.println(rdEntities.get(i).get(j).getEntity().getEntityInfo() + rdEntities.get(i).get(j).getQuantity());
            }
        }
    }

    public void reset() {
        rdEntities.clear();
    }     //clears all items

}

