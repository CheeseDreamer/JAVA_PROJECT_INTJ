import java.util.Scanner;
public class Menu {
    Admin admin = new Admin();
    Beneficiary ben = new Beneficiary();
    Donator don = new Donator();
    public void initMenu() {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Welcome to System: Organization of Beneficiaries and Donators");
            System.out.print("Are you a registered user?(y/n): ");
            String logged = scan.nextLine();
            boolean isRegisteredUser=true;
            boolean isNamePhoneInit=false;
            boolean doLoop = true;
            boolean validNumber;
            String userType="User";
            do {
                if(!isNamePhoneInit) {
                    //If you are a new user
                    if (!(logged.equals("y") || logged.equals("Y"))) {
                        System.out.print("Welcome new user, would you like to sign in as a Beneficiary or as a Donator?: ");
                        do {
                            userType = scan.nextLine();
                            if(!(userType.equals("donator")||userType.equals("Donator")||userType.equals("beneficiary")||userType.equals("Beneficiary"))){
                                System.out.print("Please enter a valid user type: ");
                            }
                        }while(!(userType.equals("donator")||userType.equals("Donator")||userType.equals("beneficiary")||userType.equals("Beneficiary")));
                    }
                    System.out.print("Enter your name: ");
                    admin.setName(scan.nextLine());
                    System.out.print("Enter your phone: ");
                    admin.setPhone(scan.nextLine());
                    do {
                        validNumber = true;
                        try {
                            Long.parseLong(admin.getPhone());
                        } catch (NumberFormatException nfe) {
                            validNumber=false;
                            System.out.print("Give valid phone number: ");
                            admin.setPhone(scan.nextLine());
                        }
                    }while(!validNumber);
                    ben.setPhone(admin.getPhone());
                    don.setPhone(admin.getPhone());
                }
                //If you are a registered user
                if ((logged.equals("y") || logged.equals("Y")) && isRegisteredUser) {
                    isRegisteredUser=false;
                    if (admin.isAdminPhone()) {
                        System.out.println("Welcome Admin " + admin.getName());
                        userType="Admin";
                        doLoop=false;
                    } else if (ben.isBeneficiaryPhone()) {
                        System.out.println("Welcome Beneficiary " + admin.getName());
                        userType="Beneficiary";
                        doLoop=false;
                    } else if (don.isDonatorPhone()) {
                        System.out.println("Welcome Donator " + admin.getName());
                        userType="Donator";
                        doLoop=false;
                    } else {
                        System.out.print("Your credentials did not match our database" +
                                "\nDo you want to try again(y) or register(n) [y/n]: ");
                        logged = scan.nextLine();
                        isRegisteredUser=false;
                        if (logged.equals("y") || logged.equals("Y")) {
                            isRegisteredUser=true;
                            isNamePhoneInit = false;
                            doLoop = true;
                        }
                    }
                } else {//If you are a new user
                        //To go back to Logging in, change the logged = "y" and isRegisteredUser=true
                    if (admin.isAdminPhone() || ben.isBeneficiaryPhone() || don.isDonatorPhone()) {
                        System.out.println("Warning the credentials you've given belongs to a registered user");
                        System.out.print("Do you want to log in? (y/n): ");
                        logged = scan.nextLine();
                        isNamePhoneInit=false;
                        isRegisteredUser=false;
                        if(logged.equals("y")||logged.equals("Y")){
                            isNamePhoneInit=true;
                            isRegisteredUser=true;
                        }
                    }
                    if(userType.equals("donator")||userType.equals("Donator")){
                        System.out.println("Congratulations, you are now a new Donator");
                        doLoop=false;
                    }else if(userType.equals("beneficiary")||userType.equals("Beneficiary")){
                        System.out.println("Congratulations, you are now a new Beneficiary");
                        doLoop=false;
                    }
                }
            } while (doLoop);
            if(userType.equals("donator")||userType.equals("Donator")) {
                System.out.println("Welcome to Donator Menu, User: "+admin.getName());
                System.out.println("\t[1]Add Offer\n\t[2]Show Offers\n\t[3]Commit\n\t[4]Back\n\t[5]Logout\n\t[6]Exit");
            }else if(userType.equals("beneficiary")||userType.equals("Beneficiary")){
                System.out.println("Welcome to Beneficiary Menu, User: "+admin.getName());
                System.out.println("\t[1]Add Request\n\t[2]Show Requests\n\t[3]Commit\n\t[4]Back\n\t[5]Logout\n\t[6]Exit");
            }else if(userType.equals("admin")||userType.equals("Admin")){
                System.out.println("Welcome to Admin Menu, User: "+admin.getName());
                System.out.println("\t[1]View\n\t[2]Monitor Organization\n\t[3]Back\n\t[4]Logout\n\t[5]Exit");
            }
        }
    }
}
