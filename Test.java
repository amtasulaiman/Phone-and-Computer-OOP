import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Test class --
 * This class is responsible for testing different type of Phones and
 * Computers and their respective functionalities. There are 7 Phones
 * and 5 Computers (both including 2 Smartphones that are phones as
 * well as computers. First of all phones are calling each other and
 * phones could leave the msgs if the other phone is a Landline &
 * is busy. Afterwards, games are installed on different type of
 * Computers and  then played only if they are installed. State of
 * Computers are changed from on to off to check whether installation
 * & game play works or not which it shouldn't. At last, two Array lists
 * are created to stores Phones & Computers; they are populated with
 * existing Phone & Computer objects. Initial lists are displayed first,
 * and then Phones are sorted in two different ways while Computers
 * are sorted in 3 different ways and displayed, respectively.
 */
public class Test {

    public static void main(String args[]) {
        //Apparent types of atleast one type of Phone is "Phone"
        Phone julie = new OldLandline("Julie", 9018772324L);
        OldLandline john = new OldLandline("John", 9018772323L);
        OldLandline bob = new OldLandline("Bob", 9018742223L);
        Phone charlie = new Landline("Charlie", 1237812373L);
        OldLandline sher = new Landline("Sher", 3247812373L);

        try {
            //will through an exception most likely
            Phone robin = new OldLandline("Robin", 90872324L);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        Phone suzie = new SmartPhone("Suzie", 8344563392L, 25, 130, 199);
        Computer paul = new SmartPhone("Paul", 5554567892L, 35, 110, 177);
        Laptop alex = new Laptop("HP", "Alex", 15, 180, 144);
        Computer robert = new Laptop("Dell", "Robert", 25, 150, 122);


        System.out.println("\n******Testing for Phones******");

        julie.call(john);
        bob.call(julie);
        john.end();
        charlie.call(sher);
        bob.call(charlie);
        bob.call(charlie);
        System.out.println("\nUNREAD msgs on Charlie's phone\n");
        ((Landline) charlie).readMessages(Landline.MSG_STATUS.UNREAD);
        System.out.println("\n");
        julie.call(charlie);
        john.call(charlie);
        ((SmartPhone) paul).call(charlie);
        System.out.println("\nREAD msgs on Charlie's phone\n");
        ((Landline) charlie).readMessages(Landline.MSG_STATUS.READ);
        System.out.println("\nAll Msgs on Charlie's phone\n");
        ((Landline) charlie).readMessages();
        System.out.println("\n");
        charlie.end();
        suzie.call((SmartPhone) paul);
        bob.call(suzie);
        System.out.println("\nUNREAD msgs on Suzie's phone\n");
        ((Landline) suzie).readMessages(Landline.MSG_STATUS.UNREAD);
        john.call(suzie);
        julie.call(suzie);
        System.out.println("\n\nAll msgs on Suzie's phone\n");
        ((Landline) suzie).readMessages();
        System.out.println("\n");
        ((SmartPhone) paul).end();

        System.out.println("\n******Testing for Computers******");
        ((SmartPhone) suzie).installGame("Bake Cake");
        ((SmartPhone) suzie).playGame("Bake Cookie");
        ((SmartPhone) suzie).playGame("Bake Cake");
        paul.installGame("FORTNITE");
        paul.installGame("PUBG");
        paul.installGame("KING OF FIGHTERS");
        paul.playGame("Pubg");
        paul.playGame("FORTNITE");
        paul.setState("off");
        paul.playGame("TEMPLE RUN");
        paul.installGame("TEMPLE RUN");
        try {
            paul.setState("Turn On"); //will throw an exception
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        paul.setState("ON");
        paul.installGame("RUBY");
        paul.installGame("TEMPLE RUN");
        paul.playGame("KING OF FIGHTERS");
        paul.installGame("JUMP!");//doesnt do anything since 5 games are already installed

        System.out.println("\n");

        alex.installGame("MR.Crap");
        try {
            alex.setState("off it"); //throws an exception
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        alex.setState("OFF");
        alex.playGame("MR.Crap");
        alex.installGame("DOTA 2");
        alex.setState("on");
        alex.playGame("MR.Crap");
        alex.installGame("DOTA 2");
        robert.installGame("MARIO");
        robert.setState("OFF");
        robert.playGame("mario");
        robert.setState("ON");
        robert.playGame("mario");

        /*
         * Here begins the testing of various ordering of all these objects. we have
         * two lists one for phones, the other for computers. They've been populated
         * with above objects, it first prints out the initial state of the list and
         * afterwards each lists are sorted using each comparator & displayed,
         * respectively.
         */

        List<Phone> allPhones = new ArrayList<Phone>();     //list of all phones
        List<Computer> allComps = new ArrayList<Computer>(); //list of all computers

        System.out.println("\n******Testing for Sorting Phones******");

        allPhones.add(julie);
        allPhones.add(john);
        allPhones.add(bob);
        allPhones.add(charlie);
        allPhones.add(sher);
        allPhones.add((SmartPhone) paul);

        System.out.println("\n\nInitial Phone list: ");
        for (Phone allPhone : allPhones)
            System.out.println(allPhone.toString());

        Collections.sort(allPhones, new Orderings.PhoneNumberComparator());
        System.out.println("\nAfter PhoneNumber sort: ");
        for (Phone allPhone : allPhones)
            System.out.println(allPhone.toString());

        Collections.sort(allPhones, new Orderings.PhoneOwnerComparator());
        System.out.println("\nAfter PhoneOwner sort: ");
        for (Phone allPhone : allPhones)
            System.out.println(allPhone.toString());

        System.out.println("\n******Testing for Sorting Computers******");

        allComps.add(paul);
        allComps.add(alex);
        allComps.add((SmartPhone) suzie);
        allComps.add(robert);

        System.out.println("\nInitial Computers list: ");
        for (Computer allComp : allComps)
            System.out.println(allComp.toString());

        Collections.sort(allComps, new Orderings.ComputerRAMComparator());
        System.out.println("\nAfter ComputerRAM sort: ");
        for (Computer allComp : allComps)
            System.out.println(allComp.toString());

        Collections.sort(allComps, new Orderings.ComputerScreenComparator());
        System.out.println("\nAfter ComputerScreen sort: ");
        for (Computer allComp : allComps)
            System.out.println(allComp.toString());

        Collections.sort(allComps, new Orderings.ComputerBrandComparator());
        System.out.println("\nAfter ComputerBrand sort: ");
        for (Computer allComp : allComps)
            System.out.println(allComp.toString());
    }
}

