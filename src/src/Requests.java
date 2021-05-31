public class Requests extends RequestDonationList{

    @Override
    public void add(RequestDonation obj, Organization org) {
        for(int i=0;i<getRdEntities().size();i++) {
            if (getRdEntities().get(i).contains(obj)) {
                // enhmerwsh posothtas tou obj
                obj.addQuantity(getRdEntities().get(0).get(i).getQuantity());
            } else if (!(getRdEntities().get(i).contains(obj))) {
                if(obj.getEntityType().equals("Material")) {
                    getRdEntities().get(0).add(obj);
                }else if(obj.getEntityType().equals("Service")){
                    getRdEntities().get(1).add(obj);
                }
            }
        }
    }

    @Override
    public void modify(RequestDonation obj, int index) {    //process of quantity
        //index: 1 for subtraction, 2 for addition
        if (index == 2) {
            //obj.addQuantity();
        } else if (index == 1) {
            //obj.subQuantity();
        }else{//(index!=1||index!=2)
            //Throws Exception
        }
        if (obj.getQuantity() < 0 && index == 1) {       //can't reduce the quantity, already 0.
            //Throws Exception
        }
    }

    public void validRequestDonation(){ }
    public void commit(){ }
    public void addOffer(){
        System.out.println("Choose 1 for Material, 2 for Services:");
    }

}
