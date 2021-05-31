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
        /* //Old code
        for (var rd : rdEntities) {
            if (rd.getId() == entityID) {
                return rd.getEntity();
            }
        }
        return null;
        */
    }

    public ArrayList<ArrayList<RequestDonation>> getRdEntities() {
        return rdEntities;
    }

    public void add(RequestDonation rd, Organization org){
        if(org.admin.getIsAdmin()||org.getCurrentDonations().getRdEntities().contains(rd)) {
            //if you are the admin, or the Request Donation exists within the Organization
            for (int i = 0; i < entityTypesCount; i++) {
                if (rdEntities.get(i).contains(rd)) {//if you've already given the same stuff
                    // enhmerwsh posothtas tou obj
                    //idea: rd.search(rdEntities).addQuantity();
                    rd.addQuantity(rd.getQuantity());
                } else if (!(rdEntities.get(i).contains(rd))) {//if you are giving it for the first time
                    for(int j=0;j<rdEntities.get(i).size();j++) {
                        if (rdEntities.get(0).get(j).getEntityType().equals("Material")) {
                            rdEntities.get(0).add(rd);
                        } else if (rdEntities.get(1).get(i).getEntityType().equals("Service")) {
                            rdEntities.get(1).add(rd);
                        }
                    }
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

