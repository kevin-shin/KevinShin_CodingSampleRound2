import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;

/**
 * Wrapper class to extract data from JSON file and format for ease of access.
 * Called when program is initialize to load Guests.json, create a Guest object based on the data read,
 * and store in an ArrayList.
 */


public class GuestManager {
    private List<Guest> list;

    public GuestManager(){
        this.list = new ArrayList();
    }

    public ArrayList<Guest> allGuests(String filename) {
        ArrayList list = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray data = (JSONArray) jsonParser.parse(new FileReader(filename));
            for (Object obj : data) {
                JSONObject person = (JSONObject) obj;
                String firstName = (String) person.get("firstName");
                String lastName = (String) person.get("lastName");
                long IDnum = (long) person.get("id");
                long roomNumber = getRoomNumber(person);
                long startTimestamp = getstartTimestamp(person);
                long endTimestamp = getendTimestamp(person);

                Guest guest = new Guest(IDnum, firstName, lastName, roomNumber, startTimestamp, endTimestamp);
                list.add(guest);
            }

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        return list;
    }

    //Getters for nested data structures inside the JSONObject representing one guest.
    public long getRoomNumber(JSONObject guest){
        JSONObject reservation = (JSONObject) guest.get("reservation");
        long roomNumber = (long) reservation.get("roomNumber");
        return roomNumber;
    }
    public long getstartTimestamp(JSONObject guest){
        JSONObject reservation = (JSONObject) guest.get("reservation");
        long startTimestamp = (long) reservation.get("startTimestamp");
        return startTimestamp;
    }
    public long getendTimestamp(JSONObject guest){
        JSONObject reservation = (JSONObject) guest.get("reservation");
        long endTimestamp = (long) reservation.get("endTimestamp");
        return endTimestamp;

    }
}