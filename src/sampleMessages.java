import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class sampleMessages {
    private String[] messageOptions;
    private Guest guest;
    private Company company;


    public sampleMessages(Guest guest, Company company) {
        this.guest = guest;
        this.company = company;
}


    public String buildMessage(String filename){
        greetingVariable greetingVariable = new greetingVariable(this.company, this.guest);
        String testMessage = "";
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray messageTemplates = (JSONArray) jsonParser.parse(new FileReader(filename));
            String[] messages = new String[messageTemplates.size()];
            int i=0;
            for (Object obj : messageTemplates) {
                JSONObject message = (JSONObject) obj;
                JSONArray ordering = (JSONArray) message.get("Ordering");
                String part1 = getString1(message);
                String part2 = getString2(message);
                String part3 = getString3(message);
                String part4 = getString4(message);

                if (ordering.equals(new ArrayList<>(Arrays.asList("name","company","room")))){
                    System.out.println("Message " + Integer.toString(i+1)+ ":");
                    String finalMessage = greetingVariable.returnGreeting(this.guest.getstartTime()) + part1 + this.guest.getFirstName() +
                            part2 + this.company.getCompany() + part3 + this.guest.getroomNumber() + part4;
                    System.out.println(finalMessage);
                    messages[i] = finalMessage;
                    i++;
                }
                if (ordering.equals(new ArrayList<>(Arrays.asList("company", "name" ,"room")))){
                    System.out.println("Message " + Integer.toString(i+1)+ ":");
                    String finalMessage = greetingVariable.returnGreeting(this.guest.getstartTime()) + part1 + this.company.getCompany() +
                            part2 + this.guest.getFirstName() + part3 + this.guest.getroomNumber() + part4;
                    System.out.println(finalMessage);
                    messages[i] = finalMessage;
                    i++;
                }
            }

            this.messageOptions = messages;

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        return testMessage;
    }
    public String returnMessage(int index){
        return this.messageOptions[index-1];
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
