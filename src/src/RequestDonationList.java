import java.util.ArrayList;

public class RequestDonationList {
    int entityTypesCount = 2;//0 for Material, 1 for Services
    private ArrayList<ArrayList<RequestDonation>> rdEntities = new ArrayList<>(entityTypesCount);
    public RequestDonationList(){
        //Initialize rdEntities
        for(int i=0; i<entityTypesCount;i++){
            rdEntities.add(new ArrayList());
        }
    }

    public Entity get(int entityID) {
        for(int i=0; i<entityTypesCount;i++){
            int entitiesCount = rdEntities.get(i).size();
            for(int j=0;j<entitiesCount;j++){
                if(rdEntities.get(i).get(j).getId()==entityID){
                    return rdEntities.get(i).get(j).getEntity();
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

    public void add(int index, RequestDonation obj, Organization org){
        for(int i=0;i<entityTypesCount;i++) {
            if (rdEntities.get(i).contains(obj)) {
                // enhmerwsh posothtas tou obj
                //idea: obj.search(rdEntities).addQuantity();
                obj.addQuantity(3);
            } else if (!(rdEntities.contains(obj))) {
                rdEntities.get(i).add(index, obj);
            }
            else if(!(org.getCurrentDonations()).contains(obj)){
                System.out.println("Entity not found in Organization!");
                //exception
            }
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

