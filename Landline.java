import java.util.*;

public class Landline extends OldLandline {

    protected enum MSG_STATUS {
        READ, UNREAD;
    }

    //this class has a Map data type to store string msgs & their statuses
    private Map<String, MSG_STATUS> messages;


    public Landline(String owner, long myNumber) {

        super(owner, myNumber);
        //instantiating a LinkedHashMap to store msgs
        messages = new LinkedHashMap<String, MSG_STATUS>();
    }

    public void readMessages() {
        //this method prints all the msgs, whether read or unread
        for (Map.Entry<String, MSG_STATUS> map : messages.entrySet()) {
            System.out.println(map); //prints the msg first
            //if the msg is unread then it changes its status to READ after printing it
            if (map.getValue().equals(MSG_STATUS.UNREAD))
                map.setValue(MSG_STATUS.READ);
        }
    }

    public void readMessages(MSG_STATUS status) {
        //using switch case for READ or UNREAD msg display request
        switch (status) {
            case UNREAD:
                for (Map.Entry<String, MSG_STATUS> map : messages.entrySet()) {
                    //checking if map's value is unread, printing the msg afterwards
                    if (map.getValue().equals(status)) {
                        System.out.println(map);
                        //here changing the msg status value to read
                        map.setValue(MSG_STATUS.READ);
                    }
                }
            case READ:
                for (Map.Entry<String, MSG_STATUS> map : messages.entrySet()) {
                    if (map.getValue().equals(status)) {
                        System.out.println(map);
                    }
                }
        }
    }

    /**
     * leaveMsg method--
     * it allows any phone to leaveMsg to a Landline phone. It has a scanner
     * object that lets the user enter standard input.
     *
     * @param from any type of phone i.e. Oldlandline, Landline, Smartphone
     */
    protected void leaveMsg(Phone from) {

        Scanner in = new Scanner(System.in);

        System.out.println("Do you wanna leave a message?[Y/N]");

        try {
            String leave = in.nextLine(); //taking Y or N input from user
            //leave msg only when the input is Y or y
            if (leave.equalsIgnoreCase("Y")) {
                leave = in.nextLine(); //taking the msg input
                this.addMessages(leave, from);

            } else if (leave.equalsIgnoreCase("N"))
                System.out.println("You decided NOT to leave the message, thanks!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "for leaving msg.");
        }
    }

    protected void addMessages(String msg, Phone from) {
        //adding the msg entered by the phone along with UNREAD status by default
        messages.put(from.getOwner() + ": " + msg, MSG_STATUS.UNREAD);
        System.out.println(from.getOwner() + " has left the message, successfully.");
    }
}
