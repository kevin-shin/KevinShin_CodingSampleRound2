import java.util.HashMap;

public class Guest {
    private long id;
    private String firstName;
    private String lastName;
    private HashMap<String,Long> reservation;


    public Guest(long id, String firstName, String lastName, HashMap<String,Long> reservation){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.reservation = reservation;
    }

    private long getroomNumber(){
        return reservation.get("roomNumber");
    }

    private long getstartTime(){
        return reservation.get("startTimestamp");
    }

    private String getFirstName(){
        return firstName;
    }
    private String getLastName(){
        return lastName;
    }
}
