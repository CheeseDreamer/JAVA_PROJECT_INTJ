public class Donator extends User
{
    private Offers offersList;
    public Donator(String name, String phone, Offers offersList)
    {
        super(name,phone);
        this.offersList = offersList;
    }
}