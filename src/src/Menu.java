import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;
public class Menu {
    //Organization organization = new Organization();
    Admin admin = new Admin();
    Beneficiary ben = new Beneficiary();
    Donator don = new Donator();
    public void initMenu(Organization organization) {
        try (Scanner scan = new Scanner(System.in)) {
            //System.out.println("Welcome to System: Organization of Beneficiaries and Donators");
            JOptionPane.showMessageDialog(null,"Welcome to System: Organization of Beneficiaries and Donators");
            System.out.print("Are you a registered user?(y/n): ");
            String logged = scan.nextLine();
            boolean isRegisteredUser=true;
            boolean isNamePhoneInit=false;
            boolean doLoop = true;
            boolean validNumber;
            String userType="User";
            boolean logAgain= true;//true when back() or logout();
            boolean exit = false;
            do {//Back
                if(logAgain) {
                    logAgain=false;
                    do {
                        //Do you need to initialize Name and Phone?
                        if (!isNamePhoneInit) {
                            //If you are a new user
                            if (!(logged.equals("y") || logged.equals("Y"))) {
                                System.out.print("Welcome new user, would you like to sign in as a Beneficiary or as a Donator?: ");
                                do {
                                    userType = scan.nextLine();
                                    if (!(userType.equals("donator") || userType.equals("Donator") || userType.equals("beneficiary") || userType.equals("Beneficiary"))) {
                                        System.out.print("Please enter a valid user type: ");
                                    }
                                } while (!(userType.equals("donator") || userType.equals("Donator") || userType.equals("beneficiary") || userType.equals("Beneficiary")));
                                System.out.print("Enter your name: ");
                                admin.setName(scan.nextLine());
                            }
                            //You enter your phone eitherways
                            System.out.print("Enter your phone: ");
                            admin.setPhone(scan.nextLine());
                            do {
                                validNumber = true;
                                try {
                                    Long.parseLong(admin.getPhone());
                                } catch (NumberFormatException nfe) {
                                    validNumber = false;
                                    System.out.print("Give valid phone number: ");
                                    admin.setPhone(scan.nextLine());
                                }
                            } while (!validNumber);
                            ben.setPhone(admin.getPhone());
                            don.setPhone(admin.getPhone());
                        }
                        //If you are a registered user
                        if ((logged.equals("y") || logged.equals("Y")) && isRegisteredUser) {
                            isRegisteredUser = false;
                            if (admin.isAdminPhone(organization)) {
                                System.out.println("Welcome Admin " + admin.getName());
                                userType = "Admin";
                                doLoop = false;
                            } else if (ben.isBeneficiaryPhone(organization)) {
                                System.out.println("Welcome Beneficiary " + ben.getName());
                                userType = "Beneficiary";
                                doLoop = false;
                            } else if (don.isDonatorPhone(organization)) {
                                System.out.println("Welcome Donator " + don.getName());
                                userType = "Donator";
                                doLoop = false;
                            } else {
                                System.out.print("Your credentials did not match our database" +
                                        "\nDo you want to try again(y) or register(n) [y/n]: ");
                                logged = scan.nextLine();
                                isRegisteredUser = false;
                                if (logged.equals("y") || logged.equals("Y")) {
                                    isRegisteredUser = true;
                                    isNamePhoneInit = false;
                                    doLoop = true;
                                }
                            }
                        } else {//If you are a new user
                            //To go back to Logging in, change the logged = "y" and isRegisteredUser=true
                            //If the phone you've given belongs to an already registered user
                            if (admin.isAdminPhone(organization) || ben.isBeneficiaryPhone(organization) || don.isDonatorPhone(organization)) {
                                System.out.println("Warning the credentials you've given belongs to a registered user");
                                System.out.print("Do you want to log in? (y/n): ");
                                logged = scan.nextLine();
                                if(logged.equals("y")||logged.equals("Y")){
                                    if(admin.isAdminPhone(organization)){
                                        userType="Admin";
                                    }else if(ben.isBeneficiaryPhone(organization)){
                                        userType="Beneficiary";
                                    }else if(don.isDonatorPhone(organization)){
                                        userType="Donator";
                                    }
                                }
                                isNamePhoneInit = false;
                                isRegisteredUser = false;
                                if (logged.equals("y") || logged.equals("Y")) {
                                    isNamePhoneInit = true;
                                    isRegisteredUser = true;
                                }
                            } else {
                                if (userType.equals("donator") || userType.equals("Donator")) {
                                    System.out.println("Congratulations, you are now a new Donator");
                                    don.setName(admin.getName());
                                    don.setPhone(admin.getPhone());
                                    organization.insertDonator(new Donator(don.getName(),don.getPhone()));
                                    don.isDonatorPhone(organization);

                                    doLoop = false;
                                } else if (userType.equals("beneficiary") || userType.equals("Beneficiary")) {
                                    System.out.println("Congratulations, you are now a new Beneficiary");
                                    ben.setName(admin.getName());
                                    ben.setPhone(admin.getPhone());
                                    organization.getBeneficiaryList().add(ben);
                                    ben.isBeneficiaryPhone(organization);
                                    doLoop = false;
                                }
                            }
                        }
                    } while (doLoop);
                }
                Material mat1 = new Material();
                Service serv1 = new Service();
                RequestDonation reqDonMat = new RequestDonation(mat1, 10);
                RequestDonation reqDonServ = new RequestDonation(serv1, 5);
                RequestDonationList rdlMat = new RequestDonationList();
                RequestDonationList rdlServ = new RequestDonationList();

                rdlMat.add(0, reqDonMat);
                rdlServ.add(0, reqDonServ);

                boolean menuLoop = true;
                boolean subMenuLoop = false;
                int menuChoice;
                int subMenuChoice;
                boolean validSubMenuChoice;
                if (userType.equals("donator") || userType.equals("Donator")) {
                    System.out.println("Welcome to Donator Menu, User: " + don.getName());

                    /*
                    System.out.println("Donator List");
                    for(int i=0;i<organization.getDonatorList().size();i++) {
                        System.out.println(organization.getDonatorList().get(i).getName() + " " + organization.getDonatorList().get(i).getPhone());
                    }
                    System.out.println("End of Donator List");*/

                    System.out.println("\t[1]Add Offer\n\t[2]Show Offers\n\t[3]Commit\n\t[4]Back\n\t[5]Logout\n\t[6]Exit");
                    System.out.print("Choice: ");
                    do {
                        validNumber = true;
                        try {
                            validSubMenuChoice = false;
                            menuChoice = scan.nextInt();//1fora to dinoume emeis, 2h fora, to dinei h catch
                            do {
                                switch (menuChoice) {
                                    case 1:
                                        logAgain=false;
                                        System.out.println("Add Offer:\n\t[1]Material: Quantity(" + organization.getEntityList().get(0).size() + ")\n\t[2]Service: Quantity(" + organization.getEntityList().get(1).size() + ")" + "\n\t[3]Back");
                                        System.out.print("Choice: ");
                                        subMenuChoice = scan.nextInt();
                                        do {
                                            switch (subMenuChoice) {
                                                case 1:
                                                    System.out.println("[1]Materials:");
                                                    for(int i = 0;i<organization.getEntityList().get(0).size()-1;i++) {
                                                        System.out.println(organization.getEntityList().get(0).get(i).getEntityInfo());
                                                    }
                                                    //rdlMat.monitor();
                                                    subMenuLoop = false;
                                                    break;
                                                case 2:
                                                    System.out.println("[2]Services:");
                                                    for(int i = 0;i<organization.getEntityList().get(1).size()-1;i++) {
                                                        System.out.println(organization.getEntityList().get(1).get(i).getEntityInfo());
                                                    }
                                                    //rdlServ.monitor();
                                                    subMenuLoop = false;
                                                    break;
                                                case 3:
                                                    //[3]Break
                                                    break;
                                                default:
                                                    subMenuLoop = true;
                                                    System.out.print("Enter Valid Sub Menu Choice [1/2]: ");
                                                    subMenuChoice = scan.nextInt();
                                                    break;
                                            }
                                        } while (subMenuLoop);
                                        menuLoop = false;
                                        break;
                                    case 2:
                                        menuLoop = false;
                                        break;
                                    case 3:
                                        menuLoop = false;
                                        break;
                                    case 4://Back is the same as Logout
                                    case 5:
                                        //System.out.println("Welcome to System: Organization of Beneficiaries and Donators");
                                        //JOptionPane.showMessageDialog(null,"Welcome to System: Organization of Beneficiaries and Donators");
                                        System.out.print("Are you a registered user?(y/n): ");
                                        scan.nextLine();//Clear buffer
                                        logged = Character.toString(scan.nextLine().charAt(0));//Get first char and turn it into String
                                        if(logged.equals("y")||logged.equals("Y")){
                                            isRegisteredUser=true;
                                        }
                                        logAgain=true;
                                        menuLoop = false;
                                        break;
                                    case 6:
                                        //This one is complete, just as is
                                        exit=true;
                                        menuLoop = false;
                                        break;
                                    default:
                                        menuLoop = true;
                                        System.out.print("Enter Valid Menu Choice (1-6): ");
                                        menuChoice = scan.nextInt();
                                        break;
                                }
                            } while (menuLoop);
                        } catch (InputMismatchException ime) {
                            validNumber = false;
                            System.out.print("Give valid menu choice: ");
                            scan.nextLine();//We need nextLine to avoid having an exception inside the catch
                        }
                    } while (!validNumber);
                } else if (userType.equals("beneficiary") || userType.equals("Beneficiary")) {
                    System.out.println("Welcome to Beneficiary Menu, User: " + admin.getName());
                    System.out.println("\t[1]Add Request\n\t[2]Show Requests\n\t[3]Commit\n\t[4]Back\n\t[5]Logout\n\t[6]Exit");
                } else if (userType.equals("admin") || userType.equals("Admin")) {
                    System.out.println("Welcome to Admin Menu, User: " + admin.getName());
                    System.out.println("\t[1]View\n\t[2]Monitor Organization\n\t[3]Back\n\t[4]Logout\n\t[5]Exit");
                }
            }while(!exit);
        }
    }
}
