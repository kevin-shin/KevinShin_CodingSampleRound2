import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class messageComposer {

    public messageComposer(String filename) {

    JSONParser jsonParser = new JSONParser();
        try {
        JSONArray data = (JSONArray) jsonParser.parse(new FileReader(filename));
        for (Object obj : data) {
            JSONObject person = (JSONObject) obj;
            String sentence1 = (String) person.get("firstName");
            String lastName = (String) person.get("lastName");
            long IDnum = (long) person.get("id");

        }

    } catch (
    FileNotFoundException exception) {
        exception.printStackTrace();
    } catch (
    IOException exception) {
        exception.printStackTrace();
    } catch (
    ParseException exception) {
        exception.printStackTrace();
    }
}
   public void getsentence1(JSONObject message){
        JSONObject sentence1 = (JSONObject) message.get("");

       // long roomNumber = (long) reservation.get("roomNumber");
      //  return roomNumber;

    }

    private long getstartTimestamp(JSONObject guest){
        JSONObject reservation = (JSONObject) guest.get("reservation");

        long startTimestamp = (long) reservation.get("startTimestamp");
        return startTimestamp;

    }

    private long getendTimestamp(JSONObject guest){
        JSONObject reservation = (JSONObject) guest.get("reservation");

        long endTimestamp = (long) reservation.get("endTimestamp");
        return endTimestamp;

    }
}
