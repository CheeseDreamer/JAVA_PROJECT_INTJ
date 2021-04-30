public class Material extends Entity
{
    private double level1,level2,level3;
    //Beneficiary takes the levels

    public Material(String name, String description, int id)
    {
        super(name, description, id);
    }
    //A setter for the levels, it might be needed
    //Depending on what index you choose, it will set the corresponding level value
    public void setLevel(int index, double value){
        //The index values should be from 1-3.
        switch (index) {
            case 1:
                level1 = value;
                break;
            case 2:
                level2 = value;
                break;
            case 3:
                level3 = value;
                break;
            default:
                System.out.println("Error, invalid index given");
                break;
            }
        }


    @Override
    public String getDetails(){
        return  super.getDetails() +
            "\nLevels:" + "\n\tLevel 1: " + level1 + "\n\tLevel 2: " +
            level2 + "\n\tLevel 3: " + level3 +
            "\nDonation type: Material";}
}
