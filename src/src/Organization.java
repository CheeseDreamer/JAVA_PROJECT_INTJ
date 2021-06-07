import java.util.ArrayList;

public class Organization
{
    private String name;
    private Admin admin;
    private int entityTypes=2;
    //Temporary saves what the donator will donate
    private ArrayList<ArrayList<Entity>> entityList = new ArrayList<>(entityTypes);
    private ArrayList<Donator> donatorList = new ArrayList<>();
    private ArrayList<Beneficiary> beneficiaryList = new ArrayList<>();
    //The total available Donations in the Organization,currentDonations.getRdEntities() = row(0) is Materials, row(1) is Services
    private RequestDonationList currentDonations = new RequestDonationList();

    public Organization(){
        for(int i = 0;i<entityTypes;i++) {
            entityList.add(new ArrayList<>());
        }
    }

    public void setName(String name){this.name=name;}
    public String getName(){return name;}
    public void setAdmin(Admin admin){this.admin = admin;}
    public Admin getAdmin(){return this.admin;}

    public void setDonatorList(ArrayList<Donator> donatorList){this.donatorList=donatorList;}
    public void setBeneficiaryList(ArrayList<Beneficiary> beneficiaryList){this.beneficiaryList=beneficiaryList;}
    public void setCurrentDonations(RequestDonationList currentDonations){this.currentDonations=currentDonations;}

    public ArrayList<Donator> getDonatorList(){return donatorList;}
    public ArrayList<Beneficiary> getBeneficiaryList(){return beneficiaryList;}
    public RequestDonationList getCurrentDonations(){return currentDonations;}

    public void addEntity(Entity entity) throws AddEntityOrgException {
        boolean found = false;
        for(int i=0; i<this.getCurrentDonations().getRdEntities().size();i++){
            for(int j=0; j<this.getCurrentDonations().getRdEntities().get(i).size();j++){
                if(Entity.compare(entity,this.getCurrentDonations().getRdEntities().get(i).get(j).getEntity())){
                    found = true;
                    break;
                }
            }
        }
       try {
           if (found) {//if the admin inserts a new donation option that exists
               //System.out.println("Donation option already exists!");
               throw new AddEntityOrgException("Donation option already exists!");
           } else {
               if (entity.getType().equals("Material")) {
                   this.entityList.get(0).add(entity);
               } else if (entity.getType().equals("Service")) {
                   this.entityList.get(1).add(entity);
               }
               //Needs Exceptions if entity already exists
           }
       }catch(Exception AddEntityOrgException){System.out.println("Exception occured: ");}
    }
    public void removeEntity(Entity entity){
        //if isAdmin=true;
       try {
           if (entity.getType().equals("Material")) {
               this.entityList.get(0).remove(entity);
           } else if (entity.getType().equals("Service")) {
               this.entityList.get(1).remove(entity);
           }
       }catch(Exception RemoveEntityOrgException){System.out.println("Exception occured: ");}
        //Exception in case it doesnt exist.
    }
    public ArrayList<ArrayList<Entity>> getEntityList(){return entityList;}

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
        //Exception needed
    }
    public void listEntities(int entityType){
        switch (entityType){
            case 0:
                System.out.println("Materials");
                for(var ent: entityList.get(entityType)){
                    System.out.println("\t"+ent.getEntityInfo() +" quantity: ["+ getCurrentDonations().getWithID(ent.getId()).getQuantity() +"]");
                }
                break;
            case 1:
                System.out.println("Services");
                for(var ent:entityList.get(entityType)){
                    System.out.println("\t"+ent.getEntityInfo() +" quantity: ["+ getCurrentDonations().getWithID(ent.getId()).getQuantity() +"]");
                }
                break;
            default:
                System.out.println("Please enter either 1 or 2");
                break;
        }
    }
    public void listBeneficiaries(){
        System.out.println("Beneficiaries:");
        for(var ben: beneficiaryList){
            System.out.println("\t"+ben.getName() + " " + ben.getPhone());
        }
    }
    public void listDonators(){
        System.out.println("Donators:");
        for(var don:donatorList){
            System.out.print("\t"+don.getName() + " " + don.getPhone());
            don.showOffers();
            System.out.println();
        }
    }


    public void addCurrentDonations(RequestDonation rdEntity){//This will be usable only by Admin
        boolean found = false;
        for(int i=0; i<this.getCurrentDonations().getRdEntities().size();i++){
            for(int j=0; j<this.getCurrentDonations().getRdEntities().get(i).size();j++){
                if(RequestDonation.compare(rdEntity,this.getCurrentDonations().getRdEntities().get(i).get(j))){
                    found = true;
                    break;
                }
            }
        }
        if(found){//if the admin inserts a new donation option that exists
            System.out.println("Donation option already exists!");
            //throw exception
        }else {
            if (rdEntity.getEntityType().equals("Material")) {
                currentDonations.getRdEntities().get(0).add(rdEntity);
            } else if (rdEntity.getEntityType().equals("Service")) {
                currentDonations.getRdEntities().get(1).add(rdEntity);
            }
        }
    }
}
