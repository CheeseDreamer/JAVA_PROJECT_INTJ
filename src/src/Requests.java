public class Requests extends RequestDonationList{

    @Override
    public void add(int index, RequestDonation obj) {
        if (getRdEntities().contains(obj)) {
            RequestDonation.addQuantity();
        } else if (!(getRdEntities().contains(obj))) {
            getRdEntities().add(index, obj);

        }
    }

    @Override
    public void modify(RequestDonation obj, int index) {    //process of quantity
        //index: 1 for subtraction, 2 for addition
        if (index == 2) {
            RequestDonation.addQuantity();
        } else if (index == 1) {
            RequestDonation.subQuantity();
        }else{//(index!=1||index!=2)
            //Throws Exception
        }
        if (obj.getQuantity() < 0 && index == 1) {       //can't reduce the quantity, already 0.
            //Throws Exception
        }

    }

    public void validRequestDonation(){
    }
    public void commit(){}
}
