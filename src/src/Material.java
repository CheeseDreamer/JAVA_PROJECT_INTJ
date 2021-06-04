public class Material extends Entity
{
    private double level1,level2,level3;
    //Beneficiary takes the levels

    public Material(){}
    public Material(String name, String description, int id)
    {
        super(name, description, id);
    }

    //A getter for the levels, it might be needed
    //Depending on what index you choose, it will set the corresponding level value

    public double getLevel(Beneficiary ben){
        if(ben.getNoPersons()==1) {
            return level1 = 1;
        }else if(ben.getNoPersons()>2 && ben.getNoPersons()<=4){
            return level2 = ben.getNoPersons();
        }else{
            return level3 = ben.getNoPersons();
        }
    }

    public String getType(){
        return "Material";
    }
    @Override
    public String getDetails(){
        return  super.getDetails() +
            "\nLevels:" + "\n\tLevel 1: " + level1 + "\n\tLevel 2: " +
            level2 + "\n\tLevel 3: " + level3 +
            "\nDonation type: "+getType();}
}
