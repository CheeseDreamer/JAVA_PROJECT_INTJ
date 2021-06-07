public abstract class Entity
{
    private String name, description;
    private int id;

    //constructors
    public Entity(){}
    public Entity(String name, String description, int id)
    {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public void setName(String name){this.name=name;}
    public String getName(){return name;}
    public void setDescription(String description){this.description=description;}
    public String getDescription(){return description;}
    public void setId(int id){this.id=id;}
    public int getId(){ return id; }
    //
    public String getType(){ return "Entity"; } //Exists to be overrided
    public abstract double getLevel(Beneficiary ben);//{return 0;}//Exists so we can access the getLevel of materials from getRdEntities()

    public String getEntityInfo(){return "name: [" + name + "] description: ["+ description + "] id: ["+id+"]";}
    public String getDetails(){return  description;}

    public static boolean compare(Entity o1, Entity o2) {
        if (o1.getId()==o2.getId()){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){return "Name: " + getEntityInfo() + "\nDetails:" + getDetails(); }
}