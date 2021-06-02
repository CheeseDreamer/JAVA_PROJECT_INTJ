import java.util.Comparator;

public class RequestDonation {
    //use interface Comparator, or check id for grouping same kind of entities.
    private Entity entity;
    private double quantity=0.0;
    public RequestDonation(){}
    public RequestDonation(Entity entity, double quantity) {
        this.entity = entity;
        this.quantity = quantity;
    }

    public void setEntity(Entity entity){ this.entity=entity; }
    public Entity getEntity(){
        return entity;
    }
    public double getQuantity(){ return quantity; }
    public void addQuantity(double quantity){ this.quantity+=quantity; }
    public void subQuantity(double quantity){ this.quantity-=quantity; }

    public int getId(){ return entity.getId(); }
    public String getEntityType(){return entity.getType();}

    public static boolean compare(RequestDonation o1, RequestDonation o2) {
        if (o1.getId()==o2.getId()){
            return true;
        }
        return false;
    }
}