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

    private long getroomNumber(){
        return roomNumber;
    }

    private long getstartTime(){
        return startTimestamp;
    }

    private String getFirstName(){
        return firstName;
    }
    private String getLastName(){
        return lastName;
    }
    private long getEndTimestamp(){
        return endTimestamp;
    }

    public String toString(){
        String description = "Name: " + firstName + " " + lastName + "\n" + "Room Number: " + Long.toString(roomNumber);
        return description;
    }
}
