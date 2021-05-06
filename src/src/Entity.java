public abstract class Entity
{
    private String name, description;
    private int id;
    public Entity(String name, String description, int id)
    {
        this.name = name;
        this.description = description;
        this.id = id;
    }
    public String getEntityInfo(){return name;}
    public int getId(){ return id; }
    public String getDetails(){return  description;}
    @Override
    public String toString(){return "Name: " + getEntityInfo() + "\nDetails:" + getDetails(); }
}