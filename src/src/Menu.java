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
            //Offers offersList = new Offers(); //Need to find a place in the ifs were its safe to make a new Offers();
            do {//Back
                if(logAgain) {
                    logAgain=false;
                    do {
                        //Do you need to initialize Name and Phone?
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
                            if(userType.equals("Beneficiary")||userType.equals("beneficiary")) {
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
                    //offersList = new Offers();//Make a new OffersList everytime you log again
                }
                RequestDonation reqDonMat = new RequestDonation(new Material(), 10);
                RequestDonation reqDonServ = new RequestDonation(new Service(), 5);
                RequestDonationList rdlList = new RequestDonationList();

                //rdlMat.add(0, reqDonMat, organization);
                //rdlServ.add(0, reqDonServ, organization);

                boolean menuLoop = true;
                boolean subMenuLoop = false;
                int menuChoice;
                int subMenuChoice;
                int entityID;
                boolean validSubMenuChoice;

                if (userType.equals("donator") || userType.equals("Donator")) {
                    System.out.println("Welcome to Donator Menu, User: " + organization.getDonatorList().get(Donator.getPos()).getName());

                    organization.getAdmin().setIsAdmin(false);

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
                                    case 1://[1]Add Offer
                                        int donationQuantity;
                                        logAgain = false;
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
                                                    //----------------------------------------------------------------------------------------------------------------------------------
                                                    /*System.out.println("Insert the id of the Material you want to offer: ");
                                                    System.out.print("id: ");
                                                    entityID = scan.nextInt();

                                                    reqDonMat = new RequestDonation(new Material(), 0);
                                                    reqDonMat.getEntity().setId(entityID);
                                                    for (int i = 0; i < organization.getCurrentDonations().getRdEntities().get(0).size(); i++) {
                                                        if (RequestDonation.compare(reqDonMat, organization.getCurrentDonations().getRdEntities().get(0).get(i))) {
                                                            entityID = organization.getCurrentDonations().getRdEntities().get(0).get(i).getId();
                                                            reqDonMat.setEntity(organization.getCurrentDonations().getRdEntities().get(0).get(i).getEntity());
                                                            break;
                                                        } else {
                                                            entityID = -1;
                                                        }
                                                    }
                            //----------------------------------------------------------------------------------------------------------------------------------
                                                    if (entityID != -1) {//if requestDonation found in organization do below stuff
                                                        System.out.println("Insert how much you want to give");
                                                        System.out.print("Quantity: ");
                                                        donationQuantity = scan.nextInt();
                                                        scan.nextLine();//Clear the buffer
                                                        System.out.print("Confirm?(y/n): ");
                                                        confirmDonation = scan.nextLine();
                                                        if (confirmDonation.equals("y") || confirmDonation.equals("Y")) {
                                                            System.out.println("you gave:\n\t" + reqDonMat.getEntity().getEntityInfo() + " quantity: " + donationQuantity);
                                                            //offersList.getRdEntities().get(0).add(new RequestDonation(reqDonMat.getEntity(),donationQuantity));
                                                            offersList.add(reqDonMat,organization);
                                                            //offersList.add(new RequestDonation(reqDonMat.getEntity(),reqDonMat.getQuantity()),organization);
                                                            //For Debug

                                                            //rdlList.add(reqDonMat, organization);//In Commit, do a for loop and add everything to currentDonations, also, rdList might need add(new RequestDonation()) like below.
                                                            //Calls addCurrentDonation

                                                            //organization.getCurrentDonations().add(new RequestDonation(reqDonMat.getEntity(),reqDonMat.getQuantity()),organization);
                                                        } else {
                                                            donationQuantity = 0;
                                                        }
                                                        */
                                                    //----------------------------------------------------------------------------------------------------------------------------------
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
                    System.out.println("Welcome to Beneficiary Menu, User: " + organization.getBeneficiaryList().get(Beneficiary.getPos()).getName());
                    System.out.println("\t[1]Add Request\n\t[2]Show Requests\n\t[3]Commit\n\t[4]Back\n\t[5]Logout\n\t[6]Exit");
                    System.out.print("Choice: ");
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
                                        logAgain=false;
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
                    System.out.println("Welcome to Admin Menu, User: " + organization.getAdmin().getName());
                    System.out.println("\t[1]View\n\t[2]Monitor Organization\n\t[3]Back\n\t[4]Logout\n\t[5]Exit");
                    System.out.print("Choice: ");
                    organization.getAdmin().setIsAdmin(true);
                    do {
                        validNumber=true;
                        try {
                            validSubMenuChoice=false;
                            menuChoice = scan.nextInt();//1fora to dinoume emeis, 2h fora, to dinei h catch
                            do {
                                switch (menuChoice) {
                                    case 1://[1]View
                                        System.out.println("currentDonations: "+ organization.getCurrentDonations());
                                        //for(int i = 0; i<1;i++){
                                        for (int i = 0; i<organization.getCurrentDonations().getRdEntities().get(0).size();i++) {
                                            System.out.println(organization.getCurrentDonations().getRdEntities().get(0).get(i).getEntity().getEntityInfo());//+" Quantity: ["+organization.getCurrentDonations().getRdEntities().get(0).get(i).getQuantity());
                                        }
                                        menuLoop = false;
                                        break;
                                    case 2://[2]Monitor
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