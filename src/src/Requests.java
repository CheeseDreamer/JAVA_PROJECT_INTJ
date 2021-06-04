import java.util.Scanner;

public class Requests extends RequestDonationList{
    private boolean found;
    private double maxAllowed;

    @Override
    public void add(RequestDonation rd, Organization org) {
        found = false;
        if (org.getEntityList().get(0).contains(rd.getEntity()) || org.getEntityList().get(1).contains(rd.getEntity())) {

            for (int i = 0; i < getRdEntities().size(); i++) {//Material or Service
                for (int j = 0; j < getRdEntities().get(i).size(); j++) {//Length of Material or Service
                    if (RequestDonation.compare(rd, getRdEntities().get(i).get(j))) {//If its not the first time requested.
                        found = true;
                        if (validRequestDonation(rd.getQuantity(), rd, org)) {//Check if its a valid Request
                            // enhmerwsh posothtas tou obj
                            if(getRdEntities().get(i).get(j).getEntityType().equals("Material")){
                                getRdEntities().get(0).get(j).addQuantity(rd.getQuantity());
                            }else if(getRdEntities().get(i).get(j).getEntityType().equals("Service")){
                                getRdEntities().get(1).get(j).addQuantity(rd.getQuantity());
                            }
                            System.out.println("Added Quantity");
                        }//valid check end
                        break;
                    } else if (!(getRdEntities().get(i).contains(rd))) {//if its the first time added, initialize
                        //initMaxAllowed=true;
                        if (validRequestDonation(rd.getQuantity(), rd, org)) {
                            if (rd.getEntityType().equals("Material")) {
                                getRdEntities().get(0).add(rd);
                            } else if (rd.getEntityType().equals("Service")) {
                                getRdEntities().get(1).add(rd);
                            }
                        }//valid check end
                    }
                }
            }
            if (!found) {//if you are requesting it for the first time
                if(validRequestDonation(rd.getQuantity(), rd, org)) {
                    System.out.println("Requesting entity for the first time");
                    if (rd.getEntityType().equals("Material")) {
                        getRdEntities().get(0).add(new RequestDonation(rd.getEntity(), rd.getQuantity()));
                        System.out.println("Added Material");
                    } else if (rd.getEntityType().equals("Service")) {
                        getRdEntities().get(1).add(new RequestDonation(rd.getEntity(), rd.getQuantity()));
                        System.out.println("Added Service");
                    }
                }//valid check end
            }
        } else {
            System.out.println("Requested Entity not found in Organization!");
            //Exception
        }
    }


    @Override
    public void modify(int modifyChoice, int offerID, Organization org, Scanner scan) {    //process of quantity
        int modQuantity;
        switch (modifyChoice) {
            case 1:
                System.out.print("Add Quantity: ");
                modQuantity = scan.nextInt();
                found = true;//Needs to be set to true
                if(validRequestDonation(modQuantity,org.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getWithID(offerID),org)) {
                    org.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getWithID(offerID).addQuantity(modQuantity);
                    System.out.println("Requested " + modQuantity + " quantity");
                    System.out.println("[id]: " + offerID + " [Current Request Quantity]: " + org.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getWithID(offerID).getQuantity());
                }else{
                    System.out.println("You've exceeded your maximum request quantity allowance!");
                }
                break;
            case 2:
                System.out.print("Sub Requested Quantity: ");
                modQuantity = scan.nextInt();
                double subtractedQuantityNum;
                if(org.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getWithID(offerID).getQuantity()-modQuantity>=0) {
                    subtractedQuantityNum = modQuantity;
                    org.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getWithID(offerID).subQuantity(modQuantity);
                    setMaxAllowed(getMaxAllowed()+modQuantity);
                }else{
                    subtractedQuantityNum = org.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getWithID(offerID).getQuantity();
                    org.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getWithID(offerID).subQuantity(org.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getWithID(offerID).getQuantity());
                    double level =org.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getWithID(offerID).getEntity().getLevel(org.getBeneficiaryList().get(Beneficiary.getPos()));
                    setMaxAllowed(10 * level);//Set to default
                    System.out.println("[Requested Quantity is set to 0]");
                }
                System.out.println("Subtracted "+ subtractedQuantityNum +" quantity");
                System.out.println("[id]: "+ offerID +" [Current Request Quantity]: " + org.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getWithID(offerID).getQuantity());
                break;
            case 3://Back
                break;
            default:
                break;
        }
    }

    public double getMaxAllowed(){return maxAllowed;}
    public void setMaxAllowed(double quantity){this.maxAllowed=quantity;}

    public boolean validRequestDonation(double requestedQuantity, RequestDonation rd,Organization org){
        double level;
        if(rd.getEntityType().equals("Material")){
            level = rd.getEntity().getLevel(org.getBeneficiaryList().get(Beneficiary.getPos()));
            //System.out.println("initMaxAllowed = "+ initMaxAllowed);
            if(!found) {//Only set it once, for each requested entity
                System.out.println("Initializing MaxAllowed");
                setMaxAllowed(10 * level);
            }
            if(requestedQuantity<=getMaxAllowed()){
                setMaxAllowed(getMaxAllowed()-requestedQuantity);
                return true;
            }else{
                System.out.println("You've exceeded your maximum Request Donation allowance");
                return false;
            }
        }else {
            return rd.getEntityType().equals("Service");
        }
    }

    public void commit(Organization org){ }
    public void addOffer(){
        System.out.println("Choose 1 for Material, 2 for Services:");
    }

}
