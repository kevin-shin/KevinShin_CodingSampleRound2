import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;


public class GuestManager {
    private List<Guest> list;

    public GuestManager(){
        this.list = new ArrayList<>();
    }


    private List<Guest> allGuests(String filename) {
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
    private static long getRoomNumber(JSONObject guest){
        JSONObject reservation = (JSONObject) guest.get("reservation");

        long roomNumber = (long) reservation.get("roomNumber");
        return roomNumber;

    }

    private static long getstartTimestamp(JSONObject guest){
        JSONObject reservation = (JSONObject) guest.get("reservation");

        long startTimestamp = (long) reservation.get("startTimestamp");
        return startTimestamp;

    }

    private static long getendTimestamp(JSONObject guest){
        JSONObject reservation = (JSONObject) guest.get("reservation");

        long endTimestamp = (long) reservation.get("endTimestamp");
        return endTimestamp;

    }
}