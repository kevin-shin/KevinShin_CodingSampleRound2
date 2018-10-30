import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class buildMessage {
    private String template;

    public buildMessage(){
    }

    public void writeNew(String filename) {
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray data = (JSONArray) jsonParser.parse(new FileReader(filename));
            long size = (long) data.size();

            System.out.println(">>");
            Scanner scanner = new Scanner(System.in);

            String userInput = scanner.nextLine();
            this.template = userInput;

            HashMap<String, String> messageMap = new HashMap<>();
            String modified = userInput.replaceAll("NAME", "-");
            modified = modified.replaceAll("COMPANY", "-");
            modified = modified.replaceAll("ROOM", "-");
            String[] message = modified.split("-");

            JSONObject object = new JSONObject();

            object.put("Index", size + 1);

            messageMap.put("1", " " + message[0]);
            messageMap.put("2", message[1]);
            messageMap.put("3", message[2]);
            messageMap.put("4", message[3]);

            object.put("Message", messageMap);

            if (this.template.indexOf("NAME") < this.template.indexOf("COMPANY")
                    && this.template.indexOf("COMPANY") < this.template.indexOf("ROOM")) {
                JSONArray jsonArray = new JSONArray();
                jsonArray.add("name");
                jsonArray.add("company");
                jsonArray.add("room");
                object.put("Ordering",jsonArray);
            }

            if (this.template.indexOf("NAME") < this.template.indexOf("ROOM")
                    && this.template.indexOf("ROOM") < this.template.indexOf("COMPANY")) {
                JSONArray jsonArray = new JSONArray();
                jsonArray.add("name");
                jsonArray.add("room");
                jsonArray.add("company");
                object.put("Ordering",jsonArray);
            }

            if (this.template.indexOf("COMPANY") < this.template.indexOf("NAME")
                    && this.template.indexOf("NAME") < this.template.indexOf("ROOM")) {
                JSONArray jsonArray = new JSONArray();
                jsonArray.add("company");
                jsonArray.add("name");
                jsonArray.add("room");
                object.put("Ordering",jsonArray);
            }
            if (this.template.indexOf("COMPANY") < this.template.indexOf("ROOM")
                    && this.template.indexOf("ROOM") < this.template.indexOf("NAME")) {
                JSONArray jsonArray = new JSONArray();
                jsonArray.add("company");
                jsonArray.add("room");
                jsonArray.add("name");
                object.put("Ordering",jsonArray);
            }

            data.add(object);

            FileWriter writer = new FileWriter("./data/messageTemplate.json");
            writer.write(data.toJSONString());
            writer.close();

        }catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ParseException exception) {
            exception.printStackTrace();
        }

    }
}
