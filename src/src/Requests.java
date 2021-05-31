public class Requests extends RequestDonationList{

    @Override
    public void add(int index, RequestDonation obj) {
        for(int i=0;i<getRdEntities().size();i++) {
            if (getRdEntities().get(i).contains(obj)) {
                obj.addQuantity();      // quantity=quantity+1
            } else if (!(getRdEntities().get(i).contains(obj))) {
                getRdEntities().get(i).add(index, obj);
            }
        }
    }

    @Override
    public void modify(RequestDonation obj, int index) {    //process of quantity
        //index: 1 for subtraction, 2 for addition
        if (index == 2) {
            obj.addQuantity();
        } else if (index == 1) {
            obj.subQuantity();
        }else{//(index!=1||index!=2)
            try {
                throw new RequestsException();
            } catch (RequestsException e) {
                e.printStackTrace();
            }
        }
        if (obj.getQuantity() < 0 && index == 1) {       //can't reduce the quantity, already 0.
            //Throws Exception (RequestsBeneficiaryException)

        }

    }

    public void validRequestDonation(){
        getRdEntities();
    }
    public void commit(){ }
    public void addOffer(){
        System.out.println("Choose 1 for Material, 2 for Services:");
    }

}
