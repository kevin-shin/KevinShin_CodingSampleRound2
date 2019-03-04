import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class sampleMessages {
    private String[] messageOptions;
    private Guest guest;
    private Company company;

    public sampleMessages() {
}

    public String buildMessage(String filename){
        String testMessage = "";
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray messageTemplates = (JSONArray) jsonParser.parse(new FileReader(filename));
            for (Object obj : messageTemplates) {
                JSONObject message = (JSONObject) obj;
                String messageTemplate = (String) message.get("text");
                String[] tokens = convertJSONArray((JSONArray)message.get("tokens"));

                MessageFormat form = new MessageFormat(messageTemplate);
                System.out.println(form.format(tokens));
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
    public String returnMessage(int index){
        return this.messageOptions[index-1];
    }

    public void setGuest(Guest guest){
        this.guest = guest;
    }

    public void setCompany(Company company){
        this.company = company;
    }

    private String[] convertJSONArray(JSONArray array){
        String[] newArray = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            newArray[i] = (String) array.get(i);
        }
        return newArray;
    }
}
