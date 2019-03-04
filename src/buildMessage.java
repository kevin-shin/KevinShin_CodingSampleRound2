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
import java.util.Scanner;

public class buildMessage {

    private ArrayList<String> tokens;
    private Scanner scanner = new Scanner(System.in);
    private JSONParser jsonParser = new JSONParser();

    public buildMessage(){
        this.tokens = new ArrayList<>(Arrays.asList("firstName", "lastName", "roomNumber","company","city","greeting"));
    }

    public JSONObject writeNew() {
        System.out.println("In order to create a template, please type a string below, using the following guidelines: ");
        System.out.println("- use firstName when template should include Guest's first name");
        System.out.println("- use lastName when template should include Guest's last name");
        System.out.println("- use roomNumber when template should include the guest's room number");
        System.out.println("- use company when template should include company name");
        System.out.println("- use city when template should include company's city location");
        System.out.println("- use greeting when template should include Greeting Message");

        System.out.println(">>");
        String userInput = scanner.nextLine();
        String[] userMod = userInput.split("\\W+");
        ArrayList<String> tokens = returnTokens(userMod);
        String modifiedString = textify(userInput,tokens);

        JSONObject object = new JSONObject();
        object.put("text",modifiedString);
        object.put("tokens",tokens);

        return object;
    }


    public void save(String filename, JSONObject object){
        try {
            JSONArray data = (JSONArray) jsonParser.parse(new FileReader(filename));
            long size = (long) data.size();
            object.put("Index", size + 1);
            data.add(object);

            FileWriter writer = new FileWriter("./data/messageTemplate.json");
            writer.write(data.toJSONString());
            writer.close();


        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
    }

    public ArrayList<String> returnTokens(String[] userSplit){
        ArrayList<String> toReturn = new ArrayList<>();
        for (String string: userSplit) {
            if (this.tokens.contains(string)){
                toReturn.add(string);
            }
        }
        return toReturn;
    }

    private String textify(String input, ArrayList<String> tokens) {
        String modifiedString = input;
        for (int i = 0; i < tokens.size(); i++) {
            String delimiter = "{" + i + "}";
            modifiedString = modifiedString.replaceAll(tokens.get(i), delimiter);
        }
    return modifiedString;
    }

    private JSONArray convertJSONArray(ArrayList<String> tokens){
        JSONArray jsonArray = new JSONArray();
        for (String string: tokens) {
            jsonArray.add(string);
        }

        return jsonArray;
    }
}
