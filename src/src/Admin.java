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
        if(getPhone().equals(org.getAdmin().getPhone())){
            setName(org.getAdmin().getName());
            isAdmin=true;
            return true;
        }
        isAdmin = false;
        return false;
    }

    public void setIsAdmin(boolean bool){isAdmin=bool;}
    public boolean getIsAdmin(){return isAdmin;}
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
