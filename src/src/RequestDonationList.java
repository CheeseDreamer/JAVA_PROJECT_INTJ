import java.util.ArrayList;

public class RequestDonationList {

    private ArrayList<RequestDonation> rdEntities = new ArrayList();

    public ArrayList<RequestDonation> get(Entity id){
        for (var rd:rdEntities){
            if (rd.id == id) return rd;
        }
        return rdEntities;
    }

    } // Entity class for id , forEach loop

    public void add(int index, RequestDonation obj){
    for(var x: rdEntities) {
        if(obj==x) {
            // enhmerwsh posothtas tou obj
        }
        else if(obj) {
            //exception
        }
        else {
         rdEntities.add(index,obj);
        }
    }
    rdEntities.add(index,obj);
    }

    public void remove(int index){
        rdEntities.remove(index);
    }

    public void modify(RequestDonation obj){    //process of quantity
         if(obj.getQuantity()<0) {       //can't reduce the quantity, already 0.
         }

    }

    public void monitor(){
        for(var rd: rdEntities){
            System.out.println(rd.getEntityInfo()+ );
        // + posothtes
        }
    }

    public void reset() {
    rdEntities.clear();
    }

    public void resetOne(int index){
    rdEntities.remove(index);
    }

}

