import java.util.ArrayList;
public class Beneficiary extends User
{
    private int noPersons = 1;
    private int pos;
    public  Beneficiary(){ }
    public Beneficiary(String name, String phone, int noPersons)
    {
        super(name,phone);
        this.noPersons = noPersons; // Make an exception, or check for correct input with if statements
    }

    private ArrayList<RequestDonationList> receivedList = new ArrayList();
    private ArrayList<Requests> requestsList = new ArrayList();

    public int getNoPersons(){return noPersons;}

    public boolean isBeneficiaryPhone(Organization org){
        for(int i = 0; i<org.getBeneficiaryList().size();i++){
            if(getPhone().equals((org.getBeneficiaryList().get(i).getPhone()))){
                setName(org.getBeneficiaryList().get(i).getName());
                pos=i;
                return true;
            }
        }
        return false;
    }
    public int getPos(){return pos;}

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