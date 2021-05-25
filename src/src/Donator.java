import java.util.ArrayList;
import java.util.Scanner;

public class Donator extends User
{
    private ArrayList<Offers> offersList; //Maybe do it 2D ArrayList

    public Donator(){}
    public Donator(String name, String phone)
    {
        super(name,phone);
    }

    //MENU NECESSARY METHOD
    public boolean isDonatorPhone(Organization org){
        for (var phone:org.getDonatorList()){
            if(getPhone().equals(phone.getPhone())){
                return true;
            }
        }
        return false;
    }

    //ADD METHOD WRAPPER
    public void addOffer(){
        try(Scanner s = new Scanner(System.in)) {
            int choice;
            String cancel="n";
            System.out.println("Choose 1 for Material["+"]"+
                    ", 2 for Service["+"] or other to cancel");
            choice = s.nextInt();
            do{
                String donateChoice;
                switch (choice) {
                    //READ THIS!!
                    /*We place the materials at the beginning of the list
                    and the services at the end of the list*/
                    case 1:
                        System.out.println("You choose Materials:\n\tDo you want to donate?(y/n)");
                        donateChoice = s.nextLine();
                        if (donateChoice =="y"){
                            offersList.add(0,new Offers());
                        }else{ cancel = "y";}
                        break;
                    case 2:
                        System.out.println("You choose Services:\n\tDo you want to donate?(y/n)");
                        donateChoice = s.nextLine();
                        if (donateChoice =="y"){
                            offersList.add(offersList.size()-1,new Offers());
                        }else{ cancel = "y";}
                        break;
                    default:
                        System.out.println("Do you want to cancel?(y/n)");
                        cancel = s.nextLine();
                        //We can replace cancel var with the Back method, not sure how to make the method for now
                        break;
                }
            }while(cancel!="y");
        }
    }
    public void showOffers() {
        if(!offersList.isEmpty()){
            for (var obj:offersList){
                obj.monitor();
            }
        }else{ System.out.println("You have no offers right now"); }
    }
    public void commit(Organization currentDonations, RequestDonationList rdEntities){
        currentDonations.addCurrentDonations(rdEntities);
        //if successful
        rdEntities.reset();
    }
}