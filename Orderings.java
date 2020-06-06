import java.util.Comparator;

public class Orderings {

    public static class PhoneNumberComparator implements Comparator<Phone> {
        //Compare Phones by their numbers
        @Override
        public int compare(Phone p1, Phone p2) {
            return Long.compare(p1.number().getPhoneNumber(), p2.number().getPhoneNumber());
        }
    }

    public static class PhoneOwnerComparator implements Comparator<Phone> {
        //Compare phones by their owner names (alphabetically, case-insensitive)
        @Override
        public int compare(Phone p1, Phone p2) {
            return p1.getOwner().compareToIgnoreCase(p2.getOwner());
        }
    }

    public static class ComputerScreenComparator implements Comparator<Computer> {
        //Compare Computers by their increasing screen size
        @Override
        public int compare(Computer c1, Computer c2) {
            return Integer.compare(c1.getScreenSize(), c2.getScreenSize());
        }
    }

    public static class ComputerBrandComparator implements Comparator<Computer> {
        //Compare Computers by their brand names and if brand names doesn't exist,
        //put them in the beginning
        @Override
        public int compare(Computer c1, Computer c2) {
            //if both Computers are laptops, then compare w.r.t. their brand names
            if (c1 instanceof Laptop && c2 instanceof Laptop)
                return ((Laptop) c1).getBrand().compareToIgnoreCase(((Laptop) c2).getBrand());
                //if first one is Smartphone, it will move the Smartphone in the beginning
            else if (c1 instanceof SmartPhone && c2 instanceof Laptop)
                return -1;
            else if (c1 instanceof Laptop && c2 instanceof SmartPhone)
                return 1;//returns 1 since laptop is needed to be moved after the smartphone
            return 0; //no change if none of the cases exist i.e. both Smartphones
        }
    }

    public static class ComputerRAMComparator implements Comparator<Computer> {
        //Compare Computers according to their increasing amount of RAM
        @Override
        public int compare(Computer c1, Computer c2) {
            return Integer.compare(c1.getRAM(), c2.getRAM());
        }
    }

}
