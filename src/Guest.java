public class Guest {
    private long id;
    private String firstName;
    private String lastName;
    private long roomNumber;
    private long startTimestamp;
    private long endTimestamp;


    public Guest(long id, String firstName, String lastName, long roomNumber, long startTimestamp, long endTimestamp){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roomNumber = roomNumber;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
    }

    public Guest(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getroomNumber(){
        return roomNumber;
    }

    public long getstartTime(){
        return startTimestamp;
    }

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public long getEndTimestamp(){
        return endTimestamp;
    }

    public String toString(){
        String description = firstName + " " + lastName;
        return description;
    }

    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Guest) {
            Guest guest = (Guest) obj;
            return (guest.firstName.equals(this.firstName) && guest.lastName.equals(this.lastName));
        } else {
            return false;
        }
    }
}
