import java.util.Comparator;

public class RequestDonation implements Comparator {
    //use interface Comparator, or check id for grouping same kind of entities.
    private Entity entity;
    private static double quantity;
    public RequestDonation(Entity entity, double quantity)
    {
        this.entity = entity;
        this.quantity = quantity;
    }

    public Entity getEntity(){
        return entity;
    }
    public double getQuantity(){ return quantity; }
    public static void addQuantity(){ quantity++; }
    public static void subQuantity(){ quantity--; }

    public int getId(){ return entity.getId(); }
    public String getEntityInfo(){return entity.getEntityInfo();}

    @Override
    public int compare(Object o1, Object o2) {
        // if they are equal it returns 1, otherwise it returns 0.

        //The excercise asks for RequestDonation tata type, but the method compare needs object data type to be overriden,
        // will need to look over again when main is done and we can test it
        if (o1.equals(o2)){
            return 1;
        }else{
            return 0;
        }
    }
}