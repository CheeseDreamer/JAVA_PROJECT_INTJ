import java.util.ArrayList;

public class RequestDonationList {

    private ArrayList<RequestDonation> rdEntities = new ArrayList<>();

    public Entity get(int entityID) {
        for (var rd : rdEntities) {
            if (rd.getId() == entityID) {
                return rd.getEntity();
            }
        }
        return null;
    }

    public ArrayList<RequestDonation> getRdEntities() {
        return rdEntities;
    }

    public void add(int index, RequestDonation obj){
        if(rdEntities.contains(obj)) {
            // enhmerwsh posothtas tou obj
            //RequestDonation.addQuantity();
            //might need rework
        }
        else if(!(rdEntities.contains(obj))){
            rdEntities.add(index,obj);
        }
        /*else if(!(rdEntities.contains(obj))|| //needs method in organization) {
            //exception
        }*/
    }

    public void remove(int index){
        rdEntities.remove(index);
    }

    public void modify(RequestDonation obj, int index) {    //process of quantity
        //index: 1 for subtraction, 2 for addition
        if (index == 2) {
            RequestDonation.addQuantity();
        } else if (index == 1) {
            RequestDonation.subQuantity();
        }
        if (index != 1 || index != 2) {
            //Throw exception index out of bounds or smth
        }
        if (obj.getQuantity() < 0 && index == 1) {       //can't reduce the quantity, already 0.
            //Throws Exception
        }
    }

    public void monitor(){
        for(var rd: rdEntities){
            System.out.println(rd.getEntityInfo() + rd.getQuantity());
        }
    }

    public void reset() {
        rdEntities.clear();
    }     //clears all items

}

