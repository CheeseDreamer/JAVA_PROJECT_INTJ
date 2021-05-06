import java.util.ArrayList;

public class RequestDonationList {

    private ArrayList<RequestDonation> rdEntities = new ArrayList<>();

    public ArrayList<RequestDonation> get(Entity entity){
        for (var rd:rdEntities){
            if (rd.getId(entity) == entity.getId()) {
                return rdEntities;
            }
        }
        return null;

   /* public ArrayList<RequestDonation> getRdEntities(Entity id) {
        return rdEntities;
    }
    */

}    // Entity class for id , forEach loop

    public void add(int index, RequestDonation obj){
        if(rdEntities.contains(obj)) {
            // enhmerwsh posothtas tou obj
        }
        else if(!(rdEntities.contains(obj))){
            rdEntities.add(index,obj);
        }
        /*else if(!(rdEntities.contains(obj))|| //needs method in organisation) {
            //exception
        }*/

    rdEntities.add(index,obj);
    }

    public void remove(int index){
        rdEntities.remove(index);
    }

    public void modify(RequestDonation obj){    //process of quantity
         if(obj.getQuantity()<0) {       //can't reduce the quantity, already 0.
         }
    }

    public void monitor(Entity entity){
        for(var rd: rdEntities){
            System.out.println(rd.getEntityInfo(entity) + rd.getQuantity());
        // + posothtes
        }
    }

    public void reset() {
    rdEntities.clear();
    }     //clears all items

}

