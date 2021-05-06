import java.util.ArrayList;

public class Organization
{
    String name;
    Admin admin;
    ArrayList<Entity> entityList = new ArrayList<Entity>();
    ArrayList<Entity> donatorList = new ArrayList<Entity>();
    ArrayList<Entity> beneficiaryList = new ArrayList<Entity>();
    RequestDonationList currentDonations;
    public Organization(String name, Admin admin, ArrayList<Entity> entityList, ArrayList<Entity> donatorList, ArrayList<Entity> beneficiaryList, RequestDonationList currentDonations)
    {
        this.name = name;
        this.admin = admin;
        this.entityList = entityList;
        this.donatorList = donatorList;
        this.beneficiaryList = beneficiaryList;
        this.currentDonations = currentDonations;
    }

    public void setAdmin(){this.admin = admin;} //delete this from the parametres then?
    public Admin getAdmin(){return this.admin;}
    public void addEntity(Entity entity){
        entityList.add(entity);
        //Needs Exceptions as well?
    }
    public void removeEntity(){}
    public void insertDonator(){}
    public void removeDonator(){}
    public void insertBeneficiary(){}
    public void removeBeneficiary(){}
    public void listEntities(){}
    public void listBeneficiaries(){}
    public void listDonators(){}
    public void addCurrentDonations(){}//proeretika wrapper methods, prob setter getters?.
    public void getCurrentDonations(){}

}
