public abstract class User
{
    private String name;
    private String phone;

    public User(){ }
    public User(String name,String phone){
        this.name=name;
        this.phone=phone;
    }
    //Needed for the 1st constructor
    public void setName(String name){
        this.name = name;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }
}
