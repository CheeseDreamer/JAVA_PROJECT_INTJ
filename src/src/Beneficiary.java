import java.util.ArrayList;
public class Beneficiary extends User
{
    private int noPersons = 1;
    ArrayList<String> beneficiaryPhones = new ArrayList<String>();
    ArrayList<String> beneficiaryNames = new ArrayList<>();

    public  Beneficiary(){ }
    public Beneficiary(String name, String phone, int noPersons)
    {
        super(name,phone);
        this.noPersons = noPersons; // Make an exception, or check for correct input with if statements
    }

    private ArrayList<RequestDonationList> receivedList = new ArrayList();
    private ArrayList<Requests> requestsList = new ArrayList();

    public int getNoPersons(){return noPersons;}

    public void init(){
        beneficiaryPhones.add("6915720947");
        beneficiaryPhones.add("6983746329");
        beneficiaryPhones.add("6984736150");

        beneficiaryNames.add("");
        beneficiaryNames.add("");
        beneficiaryNames.add("");
    }

    public boolean isBeneficiaryPhone(){
        for (var phone:beneficiaryPhones){
            if(getPhone().equals(phone)){
                return true;
            }
        }
        return false;
    }

    //"WRAPPER METHODS"(copy-pasting)
    //Need to finish RequestDonationList and Requests methods

    //Make the different methods for the 2 different ArrayLists
    public void addReceivedList(RequestDonationList obj){
        receivedList.add(obj);
    }
    public ArrayList<RequestDonationList> getReceivedList(){
        return receivedList;
    }
    public void removeReceivedList(int index){
        receivedList.remove(index);
    }
    public Requests addRequestList(){
        requestsList.add(new Requests());
        return requestsList.get(requestsList.size()-1);
    }
}
