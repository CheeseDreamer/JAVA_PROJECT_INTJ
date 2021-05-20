public abstract class User
{
    private String name;
    private String phone;

    User(String name,String phone){
        this.name=name;
        this.phone=phone;
    }
    String getName(){
        return name;
    }
    String getPhone(){
        return phone;
    }
}
