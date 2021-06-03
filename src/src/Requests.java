import java.util.Scanner;

public class Requests extends RequestDonationList{

    @Override
    public void add(RequestDonation rd, Organization org) {
        boolean found = false;
        if (org.getEntityList().get(0).contains(rd.getEntity()) || org.getEntityList().get(1).contains(rd.getEntity())) {
            //if(validRequestDonation()){}else{//throw exception}
            for (int i = 0; i < getRdEntities().size(); i++) {
                for (int j = 0; j < getRdEntities().get(i).size(); j++) {
                    if (RequestDonation.compare(rd, getRdEntities().get(i).get(j))) {
                        // enhmerwsh posothtas tou obj
                        getRdEntities().get(i).get(j).addQuantity(rd.getQuantity());
                        System.out.println("Added Quantity");
                        found = true;
                        break;
                    } else if (!(getRdEntities().get(i).contains(rd))) {
                        if (rd.getEntityType().equals("Material")) {
                            getRdEntities().get(0).add(rd);
                        } else if (rd.getEntityType().equals("Service")) {
                            getRdEntities().get(1).add(rd);
                        }
                    }
                }
            }
            if(!found) {//if you are requesting it for the first time
                System.out.println("Requesting entity for the first time");
                if (rd.getEntityType().equals("Material")) {
                    getRdEntities().get(0).add(new RequestDonation(rd.getEntity(), rd.getQuantity()));
                    System.out.println("Added Material");
                } else if (rd.getEntityType().equals("Service")) {
                    getRdEntities().get(1).add(new RequestDonation(rd.getEntity(), rd.getQuantity()));
                    System.out.println("Added Service");
                }
            }
        }else{
            System.out.println("Requested Entity not found in Organization!");
            //Exception
        }
    }

    //@Override
    public void modify(int modifyChoice, int offerID, Organization org, Scanner scan) {    //process of quantity
        int modQuantity;
        switch (modifyChoice) {
            case 1:
                System.out.print("Add Quantity: ");
                modQuantity = scan.nextInt();
                org.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getWithID(offerID).addQuantity(modQuantity);
                System.out.println("Added "+ modQuantity +" quantity");
                System.out.println("[id]: "+ offerID +" [Current Quantity]: " + org.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getWithID(offerID).getQuantity());
                break;
            case 2:
                System.out.print("Sub Quantity: ");
                modQuantity = scan.nextInt();
                if(org.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getWithID(offerID).getQuantity()-modQuantity>=0) {
                    org.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getWithID(offerID).subQuantity(modQuantity);

                }else{
                    org.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getWithID(offerID).subQuantity(org.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getWithID(offerID).getQuantity());
                    System.out.println("[Request Donation Quantity is set to 0]");
                }
                System.out.println("Subtracted "+ modQuantity +" quantity");
                System.out.println("[id]: "+ offerID +" [Current Quantity]: " + org.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getWithID(offerID).getQuantity());
                break;
            case 3://Back
                break;
            default:
                break;
        }
    }

    public boolean validRequestDonation(){
        return false;
    }
    public void commit(Organization org){ }
    public void addOffer(){
        System.out.println("Choose 1 for Material, 2 for Services:");
    }

}
