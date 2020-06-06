import java.awt.*;
import java.util.Scanner;

public class OldLandline implements Phone {

    private String owner;
    private PhoneNumber number;  //wrapper class instance
    private boolean busy; //the current busy state of the phone
    private Phone connectedTo; //the phone this class is connected to

    public OldLandline(String owner, long myNumber) {

        this.owner = owner;
        number = new PhoneNumber(myNumber);
        busy = false;
        connectedTo = null;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public void call(Phone phone) {

        if (!isBusy()) {  //if im not busy
            if (!phone.isBusy()) {  //if the phone we are calling isn't busy

                phone.receive(this); //the other phone will receive
                this.busy = true;   //updating this phone's busy status
                connectedTo = phone;    //updating the line connection
                System.out.println(this.getOwner() + " is on the phone with "
                        + phone.getOwner());
            } else { //if the other phone is busy, put the appropriate msg
                System.out.println(this.getOwner() + " is unable to call " + phone.getOwner() +
                        ". Line is currently Busy!");
                //if the other phone is landline, it has ability to receive msgs
                if (phone instanceof Landline) {
                    //allow this phone to leave msg on the other phone
                    ((Landline) phone).leaveMsg(this);

                }
            }
        }
    }

    @Override
    public void receive(Phone from) {

        if (!this.isBusy()) { //if the phone isnt busy, receive the call
            this.busy = true;   //update the busy status
            connectedTo = from; //update the line connection

        } else { //else put the appropriate msg
            System.out.println(this.getOwner() + " is unable to receive " + from.getOwner() +
                    "'s call. Line is currently Busy!");
        }
    }

    @Override
    public void end() {
        //only end if this phone is busy
        if (this.isBusy()) {
            //let the other phone know youre ending the call
            connectedTo.receiveEndSignal(this);
            this.busy = false; //set the current phone to not busy
            connectedTo = null;
        }
    }

    @Override
    public boolean isBusy() {
        return busy;
    }

    @Override
    public void receiveEndSignal(Phone from) {

        System.out.println(from.getOwner() + " has ended the call to " + this.getOwner());
        this.busy = false; //reset the busy status
        connectedTo = null; //end the connection
    }

    @Override
    public PhoneNumber number() {
        return this.number;
    }

    public String toString() {
        return this.getOwner() + "|" + this.number().getPhoneNumber();
    }
}
