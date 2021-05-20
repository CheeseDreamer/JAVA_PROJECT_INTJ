import java.util.ArrayList;

public class Organization
{
    String name;
    Admin admin;
    ArrayList<Entity> entityList = new ArrayList<Entity>();
    ArrayList<Donator> donatorList = new ArrayList<Donator>();
    ArrayList<Beneficiary> beneficiaryList = new ArrayList<Beneficiary>();
    ArrayList<RequestDonationList> currentDonations = new ArrayList<RequestDonationList>();
    public Organization(String name, ArrayList<Entity> entityList, ArrayList<Donator> donatorList, ArrayList<Beneficiary> beneficiaryList, ArrayList<RequestDonationList> currentDonations)
    {
        this.name = name;
        this.entityList = entityList;
        this.donatorList = donatorList;
        this.beneficiaryList = beneficiaryList;
        this.currentDonations = currentDonations;
    }

    public void setAdmin(){this.admin = admin;}
    public Admin getAdmin(){return this.admin;}
    public void addEntity(Entity entity){
        entityList.add(entity);
        //Needs Exceptions if entity already exists
    }
    public void removeEntity(Entity entity){
        //if isAdmin=true;
        entityList.remove(entity);
        //Exception in case it doesnt exist.
    }
    public void insertDonator(Donator donator){
        donatorList.add(donator);
        //Exception needed
    }
    public void removeDonator(Donator donator){
        donatorList.remove(donator);
        //Exception needed
    }
    public void insertBeneficiary(Beneficiary benef){
        beneficiaryList.add(benef);
        //Exception needed
    }
    public void removeBeneficiary(Beneficiary benef){
        beneficiaryList.remove(benef);
    }
    public void listEntities(){
        System.out.println("Material/Services");
        for(var ent: entityList){
            System.out.println("\t"+ent.getDetails());
        }
    }
    public void listBeneficiaries(){
        System.out.println("Beneficiaries Names:");
        for(var ben: beneficiaryList){
            System.out.println("\t"+ben.getName());
        }
    }
    public void listDonators(){
        System.out.println("Donators Names:");
        for(var don:donatorList){
            System.out.println("\t"+don.getName());
        }
    }
    //currentDonations setters, getters are different from wrappers, also might need to make them
    //Setter is being done in Offers, Getter is done by the wrapper method get

    //"WRAPPER METHODS"(copy-pasting)
    //need to finish add() in RequestDonationList to replace the one bellow.
    //no point in having incomplete code in 2 places
    public void addCurrentDonations(RequestDonationList rdEntity){
        currentDonations.add(rdEntity);
    }
    //public void getCurrentDonations(){} //Left this one as reminder on what to do, it's actually useless
    /*public Entity get(int entityID) {
        for (var rd : currentDonations) {
            //Might need to override getId() in RequestDonationList;
            if (rd.getId() == entityID) {
                return rd.getEntity();
            }
        }
        return null;
    }*/
}