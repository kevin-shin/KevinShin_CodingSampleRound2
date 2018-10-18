import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class buildMessage {
    private String[] ordering;
    private int index;
    private HashMap<String,String> messageMap;
    private String template;


    public buildMessage(String template){
        this.template = template;
    }

    public void writeNew(String filename){
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray data = (JSONArray) jsonParser.parse(new FileReader(filename));
            long size = (long) data.size();

            HashMap<String, String> messageMap = new HashMap<>();
            String modified = this.template.replaceAll("NAME", "-");
            modified = modified.replaceAll("COMPANY", "-");
            modified = modified.replaceAll("ROOM", "-");
            String[] message = modified.split("-");

            JSONObject object = new JSONObject();

            object.put("Index", size + 1);

            messageMap.put("1", message[0]);
            messageMap.put("2", message[1]);
            messageMap.put("3", message[2]);
            messageMap.put("4", message[3]);
            this.messageMap = messageMap;

            object.put("Message", messageMap);

            if (this.template.indexOf("NAME") < this.template.indexOf("COMPANY")
                    && this.template.indexOf("COMPANY") < this.template.indexOf("ROOM")) {
                String[] newOrdering = {"NAME", "COMPANY", "ROOM"};
                this.ordering = newOrdering;
                object.put("Ordering", ordering);
            }

            if (this.template.indexOf("NAME") < this.template.indexOf("ROOM")
                    && this.template.indexOf("ROOM") < this.template.indexOf("COMPANY")) {
                String[] newOrdering = {"NAME", "ROOM", "COMPANY"};
                this.ordering = newOrdering;
                object.put("Ordering", ordering);
            }

            if (this.template.indexOf("COMPANY") < this.template.indexOf("NAME")
                    && this.template.indexOf("NAME") < this.template.indexOf("ROOM")) {
                String[] newOrdering = {"COMPANY", "NAME", "ROOM"};
                this.ordering = newOrdering;
                object.put("Ordering", ordering);
            }
            if (this.template.indexOf("COMPANY") < this.template.indexOf("ROOM")
                    && this.template.indexOf("ROOM") < this.template.indexOf("NAME")) {
                String[] newOrdering = {"COMPANY", "ROOM", "NAME"};
                this.ordering = newOrdering;
            }

            data.add(object);
        }
        catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
    }
    public static void main(String[] args){
        buildMessage buildMessage = new buildMessage("123NAME456COMPANY789ROOMBLAH");
        buildMessage.writeNew("/Users/kevinshin/Desktop/KevinShin - CodingSample/CodingProject/messageTemplate.json");
    }
}
