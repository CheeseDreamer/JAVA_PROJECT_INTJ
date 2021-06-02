import java.util.ArrayList;

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
    public RequestDonation getWithID(int entityID) {
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
        System.out.println("add function RequestDonationList Works");
        if (org.admin.getIsAdmin() || org.getEntityList().get(0).contains(rd.getEntity()) || org.getEntityList().get(1).contains(rd.getEntity())) {//org.getCurrentDonations().getRdEntities().get(1).contains(rd)) {
            System.out.println("if statement Works");
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

    public void remove(String entityType, int index){
        if(rdEntities.get(0).get(index).getEntityType()=="Material"){
            rdEntities.get(0).remove(index);
        }else if(rdEntities.get(1).get(index).getEntityType()=="Service") {
            rdEntities.get(1).remove(index);
        }else System.out.println("Entity was not found in list");
    }

    public void modify(RequestDonation obj, int index) {    //process of quantity
        //index: 1 for subtraction, 2 for addition
        if (index == 2) {
            //obj.addQuantity();
        } else if (index == 1) {
            //obj.subQuantity();
        }
        if (index != 1 || index != 2) {
            //Throw exception index out of bounds or smth
        }
        if (obj.getQuantity() < 0 && index == 1) {       //can't reduce the quantity, already 0.
            System.out.println("Cant reduce quantity anymore");
            //Throws Exception
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

