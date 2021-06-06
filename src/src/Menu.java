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
                        //Do you need to initialize Name and Phone? Or just jump straight into the menus
                        if (!isNamePhoneInit) {
                            //Print Welcome if you are a new user
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

                            if((userType.equals("Beneficiary")||userType.equals("beneficiary"))&& !(logged.equals("y")||logged.equals("Y"))) {
                                System.out.print("Enter number of people in your family: ");
                                ben.setNoPersons(scan.nextInt());
                                scan.nextLine();//Clear Buffer
                            }
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
                            } else {//If you are not registered anywhere
                                if (userType.equals("donator") || userType.equals("Donator")) {
                                    System.out.println("Congratulations, you are now a new Donator");
                                    don.setName(admin.getName());
                                    don.setPhone(admin.getPhone());
                                    organization.insertDonator(new Donator(don.getName(),don.getPhone()));
                                    don.isDonatorPhone(organization);//probably useless

                                    doLoop = false;
                                } else if (userType.equals("beneficiary") || userType.equals("Beneficiary")) {
                                    System.out.println("Congratulations, you are now a new Beneficiary");
                                    ben.setName(admin.getName());
                                    ben.setPhone(admin.getPhone());
                                    organization.insertBeneficiary(new Beneficiary(ben.getName(),ben.getPhone(), ben.getNoPersons()));
                                    ben.isBeneficiaryPhone(organization);//probably useless
                                    doLoop = false;
                                }
                            }
                        }
                    } while (doLoop);
                }

                boolean menuLoop = true;
                boolean subMenuLoop = false;
                int menuChoice;
                int subMenuChoice;
                int entityID;
                boolean validSubMenuChoice;

                if (userType.equals("donator") || userType.equals("Donator")) {
                    System.out.println("Welcome to "+organization.getName()+" Donator Menu, User: " + organization.getDonatorList().get(Donator.getPos()).getName() +" ["+ organization.getDonatorList().get(Donator.getPos()).getPhone() +"] Current Admin: "+organization.getName());

                    organization.getAdmin().setIsAdmin(false);

                    System.out.println("\t[1]Add Offer\n\t[2]Show Offers\n\t[3]Commit\n\t[4]Back\n\t[5]Logout\n\t[6]Exit");
                    System.out.print("Choice: ");
                    logAgain = false;

                    do {
                        validNumber = true;
                        try {
                            validSubMenuChoice = false;
                            menuChoice = scan.nextInt();//1fora to dinoume emeis, 2h fora, to dinei h catch
                            do {
                                switch (menuChoice) {
                                    case 1://[1]Add Offer
                                        int donationQuantity;
                                        //logAgain = false;
                                        System.out.println("Add Offer:\n\t[1]Material: Quantity(" + organization.getEntityList().get(0).size() + ")\n\t[2]Service: Quantity(" + organization.getEntityList().get(1).size() + ")" + "\n\t[3]Back");
                                        System.out.print("Choice: ");
                                        subMenuChoice = scan.nextInt();
                                        String moreDonations; //(y/n)
                                        String confirmDonation;//(y/n)
                                        subMenuLoop=false;
                                        do {//Loop for Material Donation Sub Menu
                                            switch (subMenuChoice) {
                                                case 1:
                                                    System.out.println("[1]Materials:");

                                                    for (int i = 0; i<organization.getCurrentDonations().getRdEntities().get(0).size();i++) {
                                                        System.out.println(organization.getEntityList().get(0).get(i).getEntityInfo() + " quantity: " + organization.getCurrentDonations().getRdEntities().get(0).get(i).getQuantity());
                                                    }
                                                    organization.getDonatorList().get(Donator.getPos()).addOffer(subMenuChoice,organization, scan);

                                                    System.out.print("Do you want to make another Donation?(y/n): ");
                                                    moreDonations = scan.nextLine();

                                                    if (moreDonations.equals("y") || moreDonations.equals("Y")) {
                                                        subMenuLoop = true;
                                                    } else {
                                                        subMenuLoop = false;
                                                    }
                                                    break;
                                                case 2://[2]Services
                                                    System.out.println("[2]Services:");

                                                    for (int i = 0; i<organization.getCurrentDonations().getRdEntities().get(1).size();i++) {
                                                        System.out.println(organization.getEntityList().get(1).get(i).getEntityInfo() + " quantity: " + organization.getCurrentDonations().getRdEntities().get(1).get(i).getQuantity());
                                                    }
                                                    organization.getDonatorList().get(Donator.getPos()).addOffer(subMenuChoice,organization, scan);

                                                    System.out.print("Do you want to make another Donation?(y/n): ");
                                                    moreDonations = scan.nextLine();
                                                    if (moreDonations.equals("y") || moreDonations.equals("Y")) {
                                                        subMenuLoop = true;
                                                    } else {
                                                        subMenuLoop = false;
                                                    }
                                                    break;
                                                case 3://[3]Back
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
                                    case 2://[2]Show Offers
                                        //note to self: Offer methods here
                                        //Show what donations the Donator has given (RequestDonationList), prob rdEntities
                                        boolean hasMadeOffers = false;
                                        System.out.println("\toffersList:");
                                        for(int i =0; i<organization.getDonatorList().get(Donator.getPos()).getOffersList().getRdEntities().size();i++) {
                                            for (int j = 0; j < organization.getDonatorList().get(Donator.getPos()).getOffersList().getRdEntities().get(i).size(); j++) {
                                                System.out.println("\t\t" + (j + 1) + ". " + organization.getDonatorList().get(Donator.getPos()).getOffersList().getRdEntities().get(i).get(j).getEntity().getEntityInfo() + " quantity " + organization.getDonatorList().get(Donator.getPos()).getOffersList().getRdEntities().get(i).get(j).getQuantity());
                                                hasMadeOffers = true; // If the list is not empty, it will be run, no need for ifs
                                            }
                                        }
                                        if(!hasMadeOffers){//Else, show message, you have no offers at this momment
                                            System.out.println("You have no offers at this momment");
                                        }else {
                                            subMenuLoop=true;
                                            do {//small loop for Show Offers Sub Menu //Uses subMenuLoop
                                                System.out.println("\t[1]Choose an offer Menu\n\t[2]Clear All\n\t[3]Commit\n\t[4]Back");
                                                System.out.print("Choice: ");
                                                subMenuChoice = scan.nextInt();
                                                boolean subSubMenuLoop=true;

                                                switch (subMenuChoice) {
                                                    case 1://[1]Choose an offer
                                                        do {//small loop for [1]Delete [2]Modify [3]Back
                                                            System.out.println("\t[1]Delete\n\t[2]Modify\n\t[3]Back");
                                                            System.out.print("Choice: ");
                                                            int subSubMenuChoice = scan.nextInt();
                                                            int offerID;
                                                            switch (subSubMenuChoice) {
                                                                case 1://[1]Delete
                                                                    System.out.print("Choose an offer ID to delete, or Enter -1 to go Back: ");
                                                                    offerID = scan.nextInt();
                                                                    if (offerID>=0) {//if int is inputed, delete RequestDonation
                                                                        System.out.println("Deleting....");
                                                                        organization.getDonatorList().get(Donator.getPos()).getOffersList().remove(organization.getDonatorList().get(Donator.getPos()).getOffersList().getWithID(offerID));

                                                                    } else { subSubMenuLoop = true; }
                                                                    break;
                                                                case 2://[2]Modify
                                                                    System.out.println("Choose an ID to modify: ");
                                                                    System.out.print("id: ");
                                                                    offerID = scan.nextInt();
                                                                    System.out.println("\t[1]Add Quantity\n\t[2]Subtract Quantity\n\t[3]Back");
                                                                    System.out.print("Choice: ");
                                                                    int modifyChoice = scan.nextInt();
                                                                    switch (modifyChoice) {
                                                                        case 1:
                                                                            organization.getDonatorList().get(Donator.getPos()).getOffersList().modify(modifyChoice,offerID,organization,scan);
                                                                            break;
                                                                        case 2:
                                                                            organization.getDonatorList().get(Donator.getPos()).getOffersList().modify(modifyChoice,offerID,organization,scan);
                                                                            break;
                                                                        case 3://Back
                                                                            break;
                                                                        default:
                                                                            break;
                                                                    }
                                                                    break;
                                                                case 3://[3]Back
                                                                    subSubMenuLoop=false;
                                                                    break;
                                                                default:
                                                                    System.out.println("Enter Valid Menu Choice: ");
                                                                    subSubMenuLoop=true;
                                                                    break;
                                                            }
                                                            break;
                                                        }while(subSubMenuLoop);//small loop for Show Offers Sub Menu [1]Delete [2]Modify [3]Back
                                                        break;
                                                    case 2://[2]Clear All
                                                        for(int i = 0; i< organization.getDonatorList().get(Donator.getPos()).getOffersList().getRdEntities().size();i++){
                                                            organization.getDonatorList().get(Donator.getPos()).getOffersList().getRdEntities().get(i).clear();
                                                        }
                                                        System.out.println("Offer List Has Been Cleared");
                                                        break;
                                                    case 3://[3]Commit
                                                        for(int i = 0; i< don.getOffersList().getRdEntities().size();i++)
                                                            System.out.println("0...organization.getDonatorList().get(don.getPos()).getOffersList().getRdEntities().get(i).size(): "+organization.getDonatorList().get(Donator.getPos()).getOffersList().getRdEntities().get(i).size());
                                                            //System.out.println("don.getOffersList(): "+don.getOffersList().getRdEntities().get(i).size());//returns 0
                                                        organization.getDonatorList().get(Donator.getPos()).getOffersList().commit(organization);
                                                        break;
                                                    case 4://[4]Back
                                                        subMenuLoop=false;
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }while(subMenuLoop);//small loop for Show Offers Sub Menu
                                        }
                                        menuLoop = false;
                                        break;
                                    case 3://[3]Commit
                                        organization.getDonatorList().get(Donator.getPos()).getOffersList().commit(organization);
                                        menuLoop = false;
                                        break;
                                    case 4://[4]Back is the same as Logout
                                    case 5://[5]Logout
                                        //System.out.println("Welcome to System: Organization of Beneficiaries and Donators");
                                        //JOptionPane.showMessageDialog(null,"Welcome to System: Organization of Beneficiaries and Donators");
                                        System.out.print("Are you a registered user?(y/n): ");
                                        scan.nextLine();//Clear buffer
                                        logged = Character.toString(scan.nextLine().charAt(0));//Get first char and turn it into String
                                        if (logged.equals("y") || logged.equals("Y")) {
                                            isRegisteredUser = true;
                                        }
                                        logAgain = true;
                                        menuLoop = false;
                                        break;
                                    case 6://[6]Exit
                                        //This one is complete, just as is
                                        exit = true;
                                        menuLoop = false;
                                        break;
                                    default:
                                        menuLoop = true;
                                        System.out.print("Enter Valid Menu Choice (1-6): ");
                                        menuChoice = scan.nextInt();
                                        break;
                                }
                            }while (menuLoop) ;
                        } catch (InputMismatchException ime) {
                            validNumber = false;
                            System.out.print("Give valid menu choice: ");
                            scan.nextLine();//We need nextLine to avoid having an exception inside the catch
                        }
                    } while (!validNumber);
                } else if (userType.equals("beneficiary") || userType.equals("Beneficiary")) {
                    System.out.println("Welcome to "+organization.getName()+" Beneficiary Menu, User: " + organization.getBeneficiaryList().get(Beneficiary.getPos()).getName() +" ["+organization.getBeneficiaryList().get(Beneficiary.getPos()).getPhone() +"] Current Admin: "+organization.getName());
                    System.out.println("\t[1]Add Request\n\t[2]Show Requests\n\t[3]Commit\n\t[4]Back\n\t[5]Logout\n\t[6]Exit");
                    System.out.print("Choice: ");
                    logAgain = false;
                    subMenuLoop=false;
                    organization.getAdmin().setIsAdmin(false);

                    do {
                        validNumber=true;
                        try {
                            validSubMenuChoice=false;
                            menuChoice = scan.nextInt();//1fora to dinoume emeis, 2h fora, to dinei h catch
                            do {
                                switch (menuChoice) {
                                    case 1://[1]Add Request
                                        int requestQuantity;
                                        //logAgain=false;
                                        System.out.println("Request Offer:\n\t[1]Material: Quantity(" + organization.getEntityList().get(0).size()+ ")\n\t[2]Service: Quantity(" + organization.getEntityList().get(1).size() +")"+ "\n\t[3]Back");
                                        System.out.print("Choice: ");
                                        subMenuChoice = scan.nextInt();
                                        String moreRequests; //(y/n)
                                        String confirmDonation;//(y/n)
                                        subMenuLoop=false;
                                        do {//Loop for Material Beneficiary Sub Menu
                                            switch (subMenuChoice){
                                                case 1:
                                                    System.out.println("[1]Materials:");

                                                    for (int i = 0; i<organization.getCurrentDonations().getRdEntities().get(0).size();i++) {
                                                        System.out.println(organization.getEntityList().get(0).get(i).getEntityInfo() + " quantity: " + organization.getCurrentDonations().getRdEntities().get(0).get(i).getQuantity());
                                                    }
                                                    organization.getBeneficiaryList().get(Beneficiary.getPos()).addRequest(subMenuChoice,organization, scan);

                                                    System.out.print("Do you want to make another Request?(y/n): ");
                                                    moreRequests = scan.nextLine();
                                                    if (moreRequests.equals("y") || moreRequests.equals("Y")) {
                                                        subMenuLoop = true;
                                                    } else {
                                                        subMenuLoop = false;
                                                    }
                                                    break;
                                                case 2:
                                                    System.out.println("[2]Services:");
                                                    for (int i = 0; i<organization.getCurrentDonations().getRdEntities().get(1).size();i++) {
                                                        System.out.println(organization.getEntityList().get(1).get(i).getEntityInfo() + " quantity: " + organization.getCurrentDonations().getRdEntities().get(1).get(i).getQuantity());
                                                    }
                                                    organization.getBeneficiaryList().get(Beneficiary.getPos()).addRequest(subMenuChoice,organization, scan);

                                                    System.out.print("Do you want to make another Request?(y/n): ");
                                                    moreRequests = scan.nextLine();
                                                    if (moreRequests.equals("y") || moreRequests.equals("Y")) {
                                                        subMenuLoop = true;
                                                    } else {
                                                        subMenuLoop = false;
                                                    }
                                                    break;
                                                case 3://[3]Back
                                                    break;
                                                default:
                                                    subMenuLoop=true;
                                                    System.out.print("Enter Valid Sub Menu Choice [1/2]: ");
                                                    subMenuChoice = scan.nextInt();
                                            }
                                        }while (subMenuLoop);
                                        menuLoop = false;
                                        break;
                                    case 2://[2]Show Requests
                                        boolean hasMadeRequests = false;
                                        System.out.println("\trequestsList:");
                                        for(int i =0; i<organization.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getRdEntities().size();i++) {
                                            for (int j = 0; j < organization.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getRdEntities().get(i).size(); j++) {
                                                System.out.println("\t\t" + (j + 1) + ". " + organization.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getRdEntities().get(i).get(j).getEntity().getEntityInfo() + " quantity " + organization.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getRdEntities().get(i).get(j).getQuantity());
                                                hasMadeRequests = true; // If the list is not empty, it will be run, no need for ifs
                                            }
                                        }
                                        if(!hasMadeRequests){//Else, show message, you have no offers at this momment
                                            System.out.println("You have no requests at this momment");
                                        }else {
                                            subMenuLoop=true;
                                            do {//small loop for Show Requests Sub Menu //Uses subMenuLoop
                                                System.out.println("\t[1]Choose a request Menu\n\t[2]Clear All\n\t[3]Commit\n\t[4]Back");
                                                System.out.print("Choice: ");
                                                subMenuChoice = scan.nextInt();
                                                boolean subSubMenuLoop=true;

                                                switch (subMenuChoice) {
                                                    case 1://[1]Choose a Request
                                                        do {//small loop for [1]Delete [2]Modify [3]Back
                                                            System.out.println("\t[1]Delete\n\t[2]Modify\n\t[3]Back");
                                                            System.out.print("Choice: ");
                                                            int subSubMenuChoice = scan.nextInt();
                                                            int offerID;
                                                            switch (subSubMenuChoice) {
                                                                case 1://[1]Delete
                                                                    System.out.print("Choose an offer ID to delete, or Enter -1 to go Back: ");
                                                                    offerID = scan.nextInt();
                                                                    if (offerID>=0) {//if int is inputed, delete RequestDonation
                                                                        System.out.println("Deleting....");
                                                                        organization.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().remove(organization.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getWithID(offerID));

                                                                    } else { subSubMenuLoop = true; }
                                                                    break;
                                                                case 2://[2]Modify
                                                                    System.out.println("Choose an ID to modify: ");
                                                                    System.out.print("id: ");
                                                                    offerID = scan.nextInt();
                                                                    System.out.println("\t[1]Add Quantity\n\t[2]Subtract Quantity\n\t[3]Back");
                                                                    System.out.print("Choice: ");
                                                                    int modifyChoice = scan.nextInt();
                                                                    switch (modifyChoice) {
                                                                        case 1://[1]Add Quantity
                                                                            organization.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().modify(modifyChoice,offerID,organization,scan);
                                                                            break;
                                                                        case 2://[1]Subtract Quantity
                                                                            organization.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().modify(modifyChoice,offerID,organization,scan);
                                                                            break;
                                                                        case 3://Back
                                                                            break;
                                                                        default:
                                                                            break;
                                                                    }
                                                                    break;
                                                                case 3://[3]Back
                                                                    subSubMenuLoop=false;
                                                                    break;
                                                                default:
                                                                    System.out.println("Enter Valid Menu Choice: ");
                                                                    subSubMenuLoop=true;
                                                                    break;
                                                            }
                                                            break;
                                                        }while(subSubMenuLoop);//small loop for Show Offers Sub Menu [1]Delete [2]Modify [3]Back
                                                        break;
                                                    case 2://[2]Clear All
                                                        for(int i = 0; i< organization.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getRdEntities().size();i++){
                                                            organization.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getRdEntities().get(i).clear();
                                                        }
                                                        System.out.println("Request List Has Been Cleared");
                                                        break;
                                                    case 3://[3]Commit
                                                        for(int i = 0; i< ben.getRequestsList().getRdEntities().size();i++)
                                                            System.out.println("0...organization.getDonatorList().get(don.getPos()).getOffersList().getRdEntities().get(i).size(): "+organization.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().getRdEntities().get(i).size());
                                                        //System.out.println("don.getOffersList(): "+don.getOffersList().getRdEntities().get(i).size());//returns 0
                                                        organization.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().commit(organization);
                                                        break;
                                                    case 4://[4]Back
                                                        subMenuLoop=false;
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }while(subMenuLoop);//small loop for Show Requests Sub Menu
                                        }
                                        menuLoop = false;
                                        break;
                                    case 3://[3]Commit
                                        organization.getBeneficiaryList().get(Beneficiary.getPos()).getRequestsList().commit(organization);
                                        menuLoop=false;
                                        break;
                                    case 4://[4]Back is the same as Logout
                                    case 5://[5]Logout
                                        System.out.print("Are you a registered user?(y/n): ");
                                        scan.nextLine();//Clear buffer
                                        logged = Character.toString(scan.nextLine().charAt(0));//Get first char and turn it into String
                                        if (logged.equals("y") || logged.equals("Y")) {
                                            isRegisteredUser = true;
                                        }
                                        logAgain = true;
                                        menuLoop = false;
                                        break;
                                    case 6://[6]Exit
                                        //This one is complete, just as is
                                        exit = true;
                                        menuLoop = false;
                                        break;
                                    default:
                                        menuLoop = true;
                                        System.out.print("Enter Valid Menu Choice (1-6): ");
                                        menuChoice = scan.nextInt();
                                        break;
                                }
                            }while(menuLoop);
                        }catch (InputMismatchException ime){
                            validNumber = false;
                            System.out.print("Give valid menu choice: ");
                            scan.nextLine();//We need nextLine to avoid having an exception inside the catch
                        }
                    }while (!validNumber);
                } else if (userType.equals("admin") || userType.equals("Admin")) {
                    System.out.println("Welcome to Admin Menu, User: " + organization.getAdmin().getName() +" ["+ organization.getAdmin().getPhone() +"]");
                    System.out.println("\t[1]View\n\t[2]Monitor Organization\n\t[3]Back\n\t[4]Logout\n\t[5]Exit");
                    System.out.print("Choice: ");

                    logAgain = false;
                    organization.getAdmin().setIsAdmin(true);
                    do {
                        validNumber=true;
                        try {
                            validSubMenuChoice=false;
                            menuChoice = scan.nextInt();//1fora to dinoume emeis, 2h fora, to dinei h catch
                            do {
                                switch (menuChoice) {
                                    case 1://[1]View
                                        System.out.println("View:\n\t[1]Material: Quantity(" + organization.getEntityList().get(0).size() + ")\n\t[2]Service: Quantity(" + organization.getEntityList().get(1).size() + ")" + "\n\t[3]Back");
                                        System.out.print("Choice: ");
                                        subMenuChoice = scan.nextInt();
                                        int subViewChoice;
                                        int subSubViewChoice;
                                        String moreDonations; //(y/n)
                                        String confirmDonation;//(y/n)
                                        subMenuLoop=false;
                                        do{//Loop for Admin's View Sub Menu
                                            switch (subMenuChoice) {
                                                case 1:
                                                    //do{//
                                                    System.out.println("[1]Materials:");
                                                    organization.listEntities(0);//0 to print Materials or 1 to print Services

                                                    System.out.println("Choose an id[1] or Add a new Material option[2] in donations, [3]Back [1,2,3]: ");
                                                    System.out.print("Choose: ");
                                                    subViewChoice=scan.nextInt();
                                                    switch (subViewChoice){
                                                        case 1://[1]Choose an id
                                                            System.out.print("id: ");
                                                            entityID = scan.nextInt();
                                                            System.out.print(organization.getCurrentDonations().getWithID(entityID).getEntity().getDetails() +" quantity: "+ organization.getCurrentDonations().getWithID(entityID).getQuantity()+"\n");
                                                            break;
                                                        case 2://[2]Add a new Material option in donations
                                                            //do{//subSubView menu
                                                            System.out.print("Enter material name:");
                                                            scan.nextLine();//Clear Buffer
                                                            String matName = scan.nextLine();
                                                            System.out.print("Enter material description: ");
                                                            String matDescription= scan.nextLine();
                                                            System.out.print("Enter material id: ");
                                                            entityID = scan.nextInt();

                                                            Material material = new Material(matName,matDescription,entityID);

                                                            System.out.print("Enter material quantity: ");
                                                            double matQuantity= scan.nextDouble();
                                                            RequestDonation reqDonMat = new RequestDonation(material,matQuantity);

                                                            organization.addEntity(material);
                                                            organization.addCurrentDonations(reqDonMat);
                                                            break;
                                                        case 3://[3]Back
                                                            break;
                                                        default:
                                                            System.out.println("invalid view menu choice");
                                                            break;
                                                    }

                                                    System.out.print("Do you want to view again?(y/n): ");
                                                    scan.nextLine();//Clear the buffer
                                                    moreDonations = scan.nextLine();

                                                    if (moreDonations.equals("y") || moreDonations.equals("Y")) {
                                                        subMenuLoop = true;
                                                    } else {
                                                        subMenuLoop = false;
                                                    }
                                                    break;
                                                case 2://[2]Services
                                                    System.out.println("[2]Services:");
                                                    organization.listEntities(1);//0 to print Materials or 1 to print Services

                                                    System.out.println("Choose an id[1] or Add a new Service option[2] in donations, [3]Back [1,2]: ");
                                                    System.out.print("Choose: ");
                                                    subViewChoice=scan.nextInt();
                                                    switch (subViewChoice){
                                                        case 1://[1]Choose an id
                                                            System.out.print("id: ");
                                                            entityID = scan.nextInt();
                                                            System.out.print(organization.getCurrentDonations().getWithID(entityID).getEntity().getDetails() +" quantity: "+ organization.getCurrentDonations().getWithID(entityID).getQuantity()+"\n");
                                                            break;
                                                        case 2://[2]Add a new Service option in donations
                                                            //do{//subSubView menu
                                                            System.out.print("Enter service name:");
                                                            scan.nextLine();//Clear Buffer
                                                            String servName = scan.nextLine();
                                                            System.out.print("Enter service description: ");
                                                            String servDescription= scan.nextLine();
                                                            System.out.print("Enter service id: ");
                                                            entityID = scan.nextInt();

                                                            Service service = new Service(servName,servDescription,entityID);

                                                            System.out.print("Enter service quantity: ");
                                                            double matQuantity= scan.nextDouble();
                                                            RequestDonation reqDonServ = new RequestDonation(service,matQuantity);

                                                            organization.addEntity(service);
                                                            organization.addCurrentDonations(reqDonServ);
                                                            break;
                                                        case 3://[3]Back
                                                            break;
                                                        default:
                                                            System.out.println("invalid view menu choice");
                                                            break;
                                                    }

                                                    System.out.print("Do you want to view again?(y/n): ");
                                                    scan.nextLine();//Clear the buffer
                                                    moreDonations = scan.nextLine();
                                                    if (moreDonations.equals("y") || moreDonations.equals("Y")) {
                                                        subMenuLoop = true;
                                                    } else {
                                                        subMenuLoop = false;
                                                    }
                                                    break;
                                                case 3://[3]Back
                                                    break;
                                                default:
                                                    subMenuLoop = true;
                                                    System.out.print("Enter Valid Sub Menu Choice [1/2]: ");
                                                    subMenuChoice = scan.nextInt();
                                                    break;
                                            }
                                        }while(subMenuLoop);

                                        menuLoop = false;
                                        break;
                                    case 2://[2]Monitor
                                        boolean monitorLoop = true;
                                        do {
                                            System.out.println("Monitor:");
                                            System.out.println("\t[1]List Beneficiaries\n\t[2]List Donators\n\t[3]Reset Beneficiaries Lists\n\t[4]Back");
                                            System.out.print("Choose: ");
                                            int monitorChoice = scan.nextInt();
                                            boolean phoneLoop;
                                            switch (monitorChoice) {
                                                case 1://[1]List Beneficiaries
                                                    System.out.println("List Beneficiaries:");
                                                    organization.listBeneficiaries();
                                                    System.out.print("Enter a phone: ");
                                                    scan.nextLine();//Clear Buffer
                                                    ben.setPhone(scan.nextLine());

                                                    do {
                                                        validNumber = true;
                                                        try {
                                                            Long.parseLong(ben.getPhone());
                                                        } catch (NumberFormatException nfe) {
                                                            validNumber = false;
                                                            System.out.print("Give valid phone number: ");
                                                            ben.setPhone(scan.nextLine());
                                                        }
                                                    } while (!validNumber);

                                                    //phoneLoop = false;
                                                    do {
                                                        phoneLoop=false;//For some reason, Beneficiary needs a constant reminder phoneLoop is false
                                                        if (ben.isBeneficiaryPhone(organization)) {
                                                            System.out.println("Beneficiary: ["+ organization.getBeneficiaryList().get(Beneficiary.getPos()).getName()+"] ["+organization.getBeneficiaryList().get(Beneficiary.getPos()).getPhone()+"]");
                                                            System.out.println("ReceivedList:");
                                                            for(int i=0;i<organization.getBeneficiaryList().get(Beneficiary.getPos()).getReceivedList().getRdEntities().size();i++) {//For the 2 Entity types(Materials,Services)
                                                                for (int j=0;j<organization.getBeneficiaryList().get(Beneficiary.getPos()).getReceivedList().getRdEntities().get(i).size();j++) {//For each entity
                                                                    System.out.println("\t"+organization.getBeneficiaryList().get(Beneficiary.getPos()).getReceivedList().getRdEntities().get(i).get(j).getEntity().getEntityInfo()+" quantity: ["+organization.getBeneficiaryList().get(Beneficiary.getPos()).getReceivedList().getRdEntities().get(i).get(j).getQuantity()+"]");
                                                                }
                                                            }

                                                            boolean monitorBenLoop=false;
                                                            do {
                                                                System.out.println("[1]Clear Received List\n[2]Delete Beneficiary\n[3]Back");
                                                                System.out.print("Choice: ");
                                                                int monitorBenChoice = scan.nextInt();
                                                                switch (monitorBenChoice) {
                                                                    case 1://[1]Clear Received List
                                                                        for(int i=0;i<organization.getBeneficiaryList().get(Beneficiary.getPos()).getReceivedList().getRdEntities().size();i++) {//For the 2 Entity types(Materials,Services)
                                                                            organization.getBeneficiaryList().get(Beneficiary.getPos()).getReceivedList().getRdEntities().get(i).clear();
                                                                        }
                                                                        System.out.println("Beneficiary: "+ organization.getBeneficiaryList().get(Beneficiary.getPos()).getName() +" Received List Cleared");
                                                                        break;
                                                                    case 2://[2]Delete Beneficiary
                                                                        organization.getBeneficiaryList().remove(Beneficiary.getPos());
                                                                        System.out.println("Deleted Beneficiary "+ ben.getName() +" from the organization");
                                                                        break;
                                                                    case 3://[3]Back
                                                                        break;
                                                                    default:
                                                                        System.out.print("invalid choice, do you want to try again?(y/n): ");
                                                                        scan.nextLine();//Clear Buffer
                                                                        String tryAgain = scan.nextLine();
                                                                        monitorBenLoop = tryAgain.equals("y") || tryAgain.equals("Y");
                                                                        break;
                                                                }
                                                            }while(monitorBenLoop);
                                                        } else {
                                                            System.out.println("Phone number not found in database!");
                                                            //Throw exception, for the message I guess?
                                                            System.out.print("Try again?(y/n): ");
                                                            String tryAgain = scan.nextLine();
                                                            phoneLoop= tryAgain.equals("y") || tryAgain.equals("Y");
                                                            if(phoneLoop){
                                                                System.out.print("Enter a phone: ");
                                                                ben.setPhone(scan.nextLine());
                                                            }
                                                        }
                                                    }while(phoneLoop);
                                                    break;

                                                case 2://[2]List Donators
                                                    System.out.println("List Donators:");
                                                    organization.listDonators();
                                                    System.out.print("Enter a phone: ");
                                                    scan.nextLine();//Clear Buffer
                                                    don.setPhone(scan.nextLine());

                                                    do {
                                                        validNumber = true;
                                                        try {
                                                            Long.parseLong(don.getPhone());
                                                        } catch (NumberFormatException nfe) {
                                                            validNumber = false;
                                                            System.out.print("Give valid phone number: ");
                                                            don.setPhone(scan.nextLine());
                                                        }
                                                    } while (!validNumber);

                                                    phoneLoop = false;
                                                    do {
                                                        if (don.isDonatorPhone(organization)) {
                                                            System.out.println("Donator: ["+ organization.getDonatorList().get(Donator.getPos()).getName()+"] ["+organization.getDonatorList().get(Donator.getPos()).getPhone()+"]");
                                                            System.out.println("Offers List:");
                                                            for(int i=0;i<organization.getDonatorList().get(Donator.getPos()).getOffersList().getRdEntities().size();i++) {//For the 2 Entity types(Materials,Services)
                                                                for (int j=0;j<organization.getDonatorList().get(Donator.getPos()).getOffersList().getRdEntities().get(i).size();j++) {//For each entity
                                                                    System.out.println("\t"+organization.getDonatorList().get(Donator.getPos()).getOffersList().getRdEntities().get(i).get(j).getEntity().getEntityInfo()+" quantity: ["+organization.getDonatorList().get(Donator.getPos()).getOffersList().getRdEntities().get(i).get(j).getQuantity()+"]");
                                                                }
                                                            }

                                                            boolean monitorDonLoop=false;
                                                            do {
                                                                System.out.println("[1]Delete Donator\n[2]Back");
                                                                System.out.print("Choice: ");
                                                                int monitorDonChoice = scan.nextInt();
                                                                switch (monitorDonChoice) {
                                                                    case 1://[1]Delete Beneficiary
                                                                        organization.getDonatorList().remove(Donator.getPos());
                                                                        System.out.println("Deleted Donator "+ don.getName() +" from the organization");
                                                                        break;
                                                                    case 2://[2]Back
                                                                        break;
                                                                    default:
                                                                        System.out.print("invalid choice, do you want to try again?(y/n): ");
                                                                        scan.nextLine();//Clear Buffer
                                                                        String tryAgain = scan.nextLine();
                                                                        monitorDonLoop = tryAgain.equals("y") || tryAgain.equals("Y");
                                                                        break;
                                                                }
                                                            }while(monitorDonLoop);
                                                        } else {
                                                            System.out.println("Phone number not found in database!");
                                                            //throw exception, for the message I guess?
                                                            System.out.print("Try again?(y/n): ");
                                                            String tryAgain = scan.nextLine();
                                                            phoneLoop= tryAgain.equals("y") || tryAgain.equals("Y");
                                                            if(phoneLoop){
                                                                System.out.print("Enter a phone: ");
                                                                don.setPhone(scan.nextLine());
                                                            }
                                                        }
                                                    }while(phoneLoop);
                                                    break;
                                                case 3://[3]Reset Beneficiaries Lists
                                                    for (int i = 0; i < organization.getBeneficiaryList().size(); i++) {//Number of beneficiaries
                                                        for (int j = 0; j < organization.getBeneficiaryList().get(i).getReceivedList().getRdEntities().size(); j++) {//Types of Entities(Materials, Services)
                                                            for (int k = 0; k < organization.getBeneficiaryList().get(i).getReceivedList().getRdEntities().get(j).size(); k++) {//Number of Entities
                                                                organization.getBeneficiaryList().get(i).getReceivedList().getRdEntities().get(j).remove(0);//Delete starting from 0, could also use clear...
                                                            }
                                                        }
                                                    }
                                                    System.out.println("All Beneficiaries Received List have been cleared");
                                                    break;
                                                case 4://[4]Back
                                                    monitorLoop=false;
                                                    break;
                                                default:
                                                    System.out.println("Invalid option, try again!");
                                                    break;
                                            }
                                        }while(monitorLoop);

                                        menuLoop = false;
                                        break;
                                    case 3://[3]Back is the same as Logout
                                    case 4://[4]Logout
                                        System.out.print("Are you a registered user?(y/n): ");
                                        scan.nextLine();//Clear buffer
                                        logged = Character.toString(scan.nextLine().charAt(0));//Get first char and turn it into String
                                        if (logged.equals("y") || logged.equals("Y")) {
                                            isRegisteredUser = true;
                                        }
                                        logAgain = true;
                                        menuLoop = false;
                                        break;
                                    case 5://[5]Exit
                                        //This one is complete, just as is
                                        exit = true;
                                        menuLoop = false;
                                        break;
                                    default:
                                        menuLoop = true;
                                        System.out.print("Enter Valid Menu Choice (1-6): ");
                                        menuChoice = scan.nextInt();
                                        break;
                                }
                            }while(menuLoop);
                        }catch (InputMismatchException ime){
                            validNumber = false;
                            System.out.print("Give valid menu choice: ");
                            scan.nextLine();//We need nextLine to avoid having an exception inside the catch
                        }
                    }while (!validNumber);
                }
            }while(!exit);
        }
    }
}