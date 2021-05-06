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
    public String getDetails(){return  description;}
    public String toString(){return getEntityInfo() + getDetails(); }
}
