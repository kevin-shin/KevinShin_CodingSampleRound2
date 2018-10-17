import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;

public class guestMessage {
    public static void main(String[] args) {

        JSONParser jsonParser = new JSONParser();

        try {
            JSONArray data = (JSONArray) jsonParser.parse(new FileReader("/Users/kevinshin/Desktop/KipsuCodingProject/Guests.json"));

            for (Object obj : data){

                JSONObject person = (JSONObject) obj;
                String firstName = (String) person.get("firstName");
                System.out.println("First Name: " + firstName);

                String lastName = (String) person.get("lastName");
                System.out.println("Last Name: " + lastName);

                long job = (long) person.get("id");
                System.out.println("ID: " + Long.toString(job));

                long roomNumber = getRoomNumber(person);
                System.out.println("Room Number: " + Long.toString(roomNumber));

                long startTimestamp = getstartTimestamp(person);
                System.out.println("Start Time: " + Long.toString(startTimestamp));

                long endTimestamp = getendTimestamp(person);
                System.out.println("End Time: " + Long.toString(endTimestamp));



            }
        }
        catch(FileNotFoundException exception) { exception.printStackTrace();}
        catch(IOException exception) { exception.printStackTrace();}
        catch(ParseException exception) { exception.printStackTrace();}
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
