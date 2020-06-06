
public class PhoneNumber {

    private final long phoneNumber;

    public PhoneNumber(long number) {
        int length = String.valueOf(number).length();
        //if the length of no. is anything other than 10, throws exception
        if (length != 10) {
            throw new IllegalArgumentException("Illegal Argument " +
                    "for Phone Number(10 digits allowed only)");
        }
        phoneNumber = number;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }
}
