import java.util.ArrayList;
public class Beneficiary extends User
{
    private int noPersons = 1;
    private ArrayList<RequestDonationList> receivedList = new ArrayList();
    private ArrayList<Requests> requestsList = new ArrayList();
    public Beneficiary(String name, String phone, int noPersons, ArrayList<RequestDonationList> receivedList, ArrayList<Requests> requestsList)
    {
        super(name,phone);
        this.noPersons = noPersons; // Make an exception, or check for correct input with if statements
        this.receivedList = receivedList;
        this.requestsList = requestsList;
    }
    //Make the different methods for the 2 different ArrayLists
    /*public ArrayList<RequestDonation> get(){
        return rdEntities;
    }
    public void add(RequestDonation obj){
        rdEntities.add(obj);
    }
    public void remove(int index){
        rdEntities.remove(index);
    }
    public void modify(int index, RequestDonation obj){
        rdEntities.add(index,obj);
    }*/
}
