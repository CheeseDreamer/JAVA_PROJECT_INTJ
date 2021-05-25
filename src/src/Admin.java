import java.util.ArrayList;
import java.util.Arrays;
public class Admin extends User{
    private boolean isAdmin = true;

    //Admin sets the type of products available!
    public Admin(){}
    public Admin(String name, String phone, boolean isAdmin)
    {
        super(name, phone);
        this.isAdmin= isAdmin;
    }

    public boolean isAdminPhone(Organization org){
        for (var phone:org.getAdminListPhone()){
            if(getPhone().equals(phone)){
                isAdmin = false;
                return true;
            }
        }
        return false;
    }
    public void view(){

    }
    public void monitorOrganization(){

    }
    public void back(){

    }
    public void logout() {

    }
    public void exit(){

    }
}
