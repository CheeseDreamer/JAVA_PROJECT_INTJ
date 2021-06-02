import java.util.ArrayList;

public class Organization
{
    String name;
    Admin admin;
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
    public void setAdmin(Admin admin){this.admin = admin;}
    public Admin getAdmin(){return this.admin;}

    public void setDonatorList(ArrayList<Donator> donatorList){this.donatorList=donatorList;}
    public void setBeneficiaryList(ArrayList<Beneficiary> beneficiaryList){this.beneficiaryList=beneficiaryList;}
    public void setCurrentDonations(RequestDonationList currentDonations){this.currentDonations=currentDonations;}

    public ArrayList<Donator> getDonatorList(){return donatorList;}
    public ArrayList<Beneficiary> getBeneficiaryList(){return beneficiaryList;}
    public RequestDonationList getCurrentDonations(){return currentDonations;}

    public void addEntity(Entity entity){
        if(entity.getType().equals("Material")){
            this.entityList.get(0).add(entity);
        }else if(entity.getType().equals("Service")){
            this.entityList.get(1).add(entity);
        }
        //Needs Exceptions if entity already exists
    }
    public void removeEntity(Entity entity){
        //if isAdmin=true;
        if(entity.getType().equals("Material")){
            this.entityList.get(0).remove(entity);
        }else if(entity.getType().equals("Service")){
            this.entityList.get(1).remove(entity);
        }
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
    public void listEntities(){
        System.out.println("Materials");
        for(var ent: entityList.get(0)){
            System.out.println("\t"+ent.getDetails());
        }
        System.out.println("Services");
        for(var ent:entityList.get(1)){
            System.out.println("\t"+ent.getDetails());
        }
    }
    public void listBeneficiaries(){
        System.out.println("Beneficiaries Names:");
        for(var ben: beneficiaryList){
            System.out.println("\t"+ben.getName() + " " + ben.getPhone() + " " + ben.getReceivedList());
        }
    }
    public void listDonators(){
        System.out.println("Donators Names:");
        for(var don:donatorList){
            System.out.print("\t"+don.getName() + " " + don.getPhone() + " ");
            don.showOffers();
            System.out.println();
        }
    }
    //currentDonations setters, getters are different from wrappers, also might need to make them
    //Setter is being done in Offers, Getter is done by the wrapper method get

    //"WRAPPER METHODS"(copy-pasting)
    //need to finish add() in RequestDonationList to replace the one bellow.
    //no point in having incomplete code in 2 places

    public void addCurrentDonations(RequestDonation rdEntity){
        boolean found = false;
        System.out.println("isAdmin: "+admin.getIsAdmin());//can alse return false when set
        //System.out.println(admin.isAdminPhone(this));//always returns true
        /*if(!getAdmin().getIsAdmin()) {//If you are not the admin
            if(rdEntity.getEntityType().equals("Material")){
                System.out.println("addCurrentDonations Reached here! Material");
                for(int i=0;i<getCurrentDonations().getRdEntities().get(0).size();i++) {
                    System.out.println("Reched loop, Material");
                    if(RequestDonation.compare(rdEntity, getCurrentDonations().getRdEntities().get(0).get(i))){
                    //if (rdEntity.getId() == getCurrentDonations().getRdEntities().get(0).get(i).getId()){
                        System.out.println("addCurrentDonations Reached here! ID Check, Material");
                        //currentDonations.add(rdEntity,this);
                        currentDonations.getRdEntities().get(0).get(i).addQuantity(rdEntity.getQuantity());
                        found=true;
                        break;//Might be unnecessary
                    }
                }
            }else if(rdEntity.getEntityType().equals("Service")){
                System.out.println("addCurrentDonations Reached here! Service");
                for(int i = 0; i<getCurrentDonations().getRdEntities().get(1).size();i++){
                    System.out.println("Reched loop, Service");
                    if (rdEntity.getId() == getCurrentDonations().getRdEntities().get(1).get(i).getId()){
                        System.out.println("addCurrentDonations Reached here! ID Check, Service");
                        currentDonations.getRdEntities().get(1).get(i).addQuantity(rdEntity.getQuantity());
                        found=true;
                        break;//Might be unnecessary
                    }
                }
            }
            //currentDonations.add(rdlEntity);
            if (found) {
                System.out.println("Entity not found in CurrentDonations of Organization");
            }
        }else {//If you are admin, also since you start with true isAdmin it initializes

         */
            System.out.println("Initialize currentDonations");
            //getCurrentDonations().add(rdEntity, this);
            if(rdEntity.getEntityType().equals("Material")) {
                currentDonations.getRdEntities().get(0).add(rdEntity);
            }else if(rdEntity.getEntityType().equals("Service")){
                currentDonations.getRdEntities().get(1).add(rdEntity);
            }
        //}

        //currentDonations.getRdEntities().get(0).add(rdEntity);
    }
}
    //public void addCurrentDonations(RequestDonation Entity){
    //    currentDonations.add(Entity);
    //}
