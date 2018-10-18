import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.ArrayList;

public class sampleMessages {
    private String message;
    private Guest guest;
    private Company company;

    public sampleMessages(Guest guest, Company company) {
        this.guest = guest;
        this.company = company;
}

    public ArrayList<String> returnOrdering(String filename) {
        ArrayList<String> order = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray messageTemplates = (JSONArray) jsonParser.parse(new FileReader(filename));
            for (Object obj : messageTemplates) {
                JSONObject message = (JSONObject) obj;
                JSONArray ordering = (JSONArray) message.get("Ordering");
                for (int i = 0; i<ordering.size();i++){
                    String myMessage = (String) ordering.get(i);
                    order.add(myMessage);
                }
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        return order;
    }

    public String buildMessage(String filename){

        String testMessage = "";
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray messageTemplates = (JSONArray) jsonParser.parse(new FileReader(filename));
            for (Object obj : messageTemplates) {
                JSONObject message = (JSONObject) obj;
                String part1 = getString1(message);
                String part2 = getString2(message);
                String part3 = getString3(message);
                String part4 = getString4(message);

                String finalMessage = "~greeting Varible~ " + this.guest.getFirstName() + part1 + part2 + this.company.getCompany() + part3
                        + this.guest.getroomNumber() + part4;
                System.out.println(finalMessage);
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        return testMessage;
    }

    public String getString1(JSONObject message){
        JSONObject messageTemplate = (JSONObject) message.get("Message");

        String string1 = (String) messageTemplate.get("1");
        return string1;

    }
    public String getString2(JSONObject message){
        JSONObject messageTemplate = (JSONObject) message.get("Message");

        String string2 = (String) messageTemplate.get("2");
        return string2;

    }public String getString3(JSONObject message){
        JSONObject messageTemplate = (JSONObject) message.get("Message");

        String string3 = (String) messageTemplate.get("3");
        return string3;

    }public String getString4(JSONObject message){
        JSONObject messageTemplate = (JSONObject) message.get("Message");

        String string4 = (String) messageTemplate.get("4");
        return string4;

    }
}
