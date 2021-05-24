public abstract class Entity
{
    private String name, description;
    private int id;
    public Entity(){}
    public Entity(String name, String description, int id)
    {
        this.name = name;
        this.description = description;
        this.id = id;
    }
    public void setName(String name){this.name=name;}
    public void setDescription(String description){this.description=description;}
    public void setId(int id){this.id=id;}
    public String getEntityInfo(){return name + " "+ description + " "+id;}
    public int getId(){ return id; }
    public String getDetails(){return  description;}
    @Override
    public String toString(){return "Name: " + getEntityInfo() + "\nDetails:" + getDetails(); }
}