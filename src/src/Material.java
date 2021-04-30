public class Material extends Entity
{
    private double level1,level2,level3;
    //Beneficiary takes the levels

    public Material(String name, String description, int id)
    {
        super(name, description, id);
    }
    @Override
    public String getDetails(){return  super.getDetails() +
            "\nLevels:" + "\n\tLevel 1: " + level1 + "\n\tLevel 2: " +
            level2 + "\n\tLevel 3: " + level3 +
            "\nDonation type: Material";}
}
