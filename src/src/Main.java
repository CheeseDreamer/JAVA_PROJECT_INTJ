public class Main {

    public static void main(String[] args) throws AddEntityOrgException {

        Organization org = new Organization();
        org.setName("Rainbow Factory");
        Material milk = new Material("Milk","Cow Milk",1);
        Material sugar = new Material("Sugar","Cane Sugar",2);
        Material rice = new Material("Rice","Canned Rice", 3);

        Service MedicalSupport = new Service("Medical Support","24/7",4);
        Service NurserySupport = new Service("Nursery Support","12 Hours",5);
        Service BabySitting = new Service("Baby Sitting","4 Hours",6);

        Admin bruh = new Admin("John Bruh","6942122953",true);
        org.setAdmin(bruh);

        Beneficiary benef1 = new Beneficiary("Elon","6745810936",2);
        Beneficiary benef2 = new Beneficiary("Musk","6745810937",4);
        Donator don = new Donator("Chris","691232762691");

        RequestDonation rdMilk = new RequestDonation(milk,15);
        RequestDonation rdSugar = new RequestDonation(sugar,100);
        RequestDonation rdRice = new RequestDonation(rice,500);

        RequestDonation rdMedicalSupport = new RequestDonation(MedicalSupport,4);
        RequestDonation rdNurserySupport = new RequestDonation(NurserySupport,12);
        RequestDonation rdBabySitting = new RequestDonation(BabySitting,24);

        org.addEntity(milk);
        org.addEntity(sugar);
        org.addEntity(rice);

        org.addEntity(MedicalSupport);
        org.addEntity(NurserySupport);
        org.addEntity(BabySitting);

        org.addCurrentDonations(rdMilk);
        org.addCurrentDonations(rdSugar);
        org.addCurrentDonations(rdRice);

        org.addCurrentDonations(rdMedicalSupport);
        org.addCurrentDonations(rdNurserySupport);
        org.addCurrentDonations(rdBabySitting);

        org.insertBeneficiary(benef1);
        org.insertBeneficiary(benef2);
        org.insertDonator(don);

        Menu menu = new Menu();
        menu.initMenu(org);
    }
}
