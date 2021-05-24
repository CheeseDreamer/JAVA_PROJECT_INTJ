import java.util.ArrayList;
import java.util.Arrays;
public class Admin extends User{
    private boolean isAdmin = true;
    ArrayList<String> adminPhones = new ArrayList<String>();
    ArrayList<String> adminNames = new ArrayList<>();
    //Admin sets the type of products available!
    public Admin(){}
    public Admin(String name, String phone, boolean isAdmin)
    {
        super(name, phone);
        this.isAdmin= isAdmin;
    }

    public boolean isAdminPhone(){
        for (var phone:adminPhones){
            if(getPhone().equals(phone)){
                isAdmin = false;
                return true;
            }
        }
        return false;
    }
    public void init(){
        adminPhones.add("6922546678");
        adminPhones.add("6972643844");
        adminPhones.add("6976432144");

        adminNames.add("Marios");
        adminNames.add("Vicky");
        adminNames.add("");
    }
    public void view(){
        if(isAdmin){

        }else{
            System.out.println("You're not an admin!");
        }
    }
    public void monitorOrganization(){
        if(isAdmin){

        }else{
            System.out.println("You're not an admin!");
        }
    }
    public void back(){
        if(isAdmin){

        }else{
            System.out.println("You're not an admin!");
        }
    }
    public void logout() {
        if(isAdmin){

        }else{
            System.out.println("You're not an admin!");
        }
    }
    public void exit(){
        if(isAdmin){

        }else{
            System.out.println("You're not an admin!");
        }
    }
}
