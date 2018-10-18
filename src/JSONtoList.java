import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;



public class JSONtoList {

    public static void main(String[] args){
        JSONtoList json = new JSONtoList();
        List guests = json.returnList("/Users/kevinshin/Desktop/KevinShin - CodingSample/CodingProject/Guests.json");
        for (Object obj: guests){

        }
    }
    public List<Map<String, String>> returnList(String filename) {
        JSONParser jsonParser = new JSONParser();
        List<Map<String, String>> list = new ArrayList<>();

        try {
            JSONArray data = (JSONArray) jsonParser.parse(new FileReader(filename));
            for (Object obj : data) {
                Map<String, String> personMap = new HashMap<>();
                JSONObject person = (JSONObject) obj;
                String firstName = (String) person.get("firstName");
                String lastName = (String) person.get("lastName");
                long IDnum = (long) person.get("id");
                long roomNumber = getRoomNumber(person);
                long startTimestamp = getstartTimestamp(person);
                long endTimestamp = getendTimestamp(person);

                personMap.put("firstName", firstName);
                personMap.put("lastName", lastName);
                personMap.put("id", Long.toString(IDnum));
                personMap.put("roomNumber", Long.toString(roomNumber));
                personMap.put("startTimestamp", Long.toString(startTimestamp));
                personMap.put("endTimestamp", Long.toString(endTimestamp));

                list.add(personMap);
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
