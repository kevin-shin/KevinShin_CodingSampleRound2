import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Scanner;

public class buildMessage {

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
            String[] tokens = {"COMPANY", "NAME", "ROOM"};

            String modifiedString = userInput;
            for (int i = 0; i < tokens.length; i++) {
                String delimiter = "{" + i + "}";
                modifiedString = modifiedString.replaceAll(tokens[i],delimiter);
            }

            MessageFormat form = new MessageFormat(modifiedString);
            System.out.println(form.format(tokens));

            JSONObject object = new JSONObject();

            object.put("Index", size + 1);
            data.add(object);

            FileWriter writer = new FileWriter("../data/messageTemplate.json");
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
