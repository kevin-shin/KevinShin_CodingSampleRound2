import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class buildMessage {

    private ArrayList<String> tokens;
    private Scanner scanner = new Scanner(System.in);
    private JSONParser jsonParser = new JSONParser();

    public buildMessage(){
        /* A token is a word reserved to signify a place in the message where guest/company data should replace the token.
           For example, "firstName" will be replaced by the user's first name when generating messages.
           For this reason, the ArrayList tokens is declared as a private instance variable which can be easily altered to reflect
           changes in JSON data structure. For the purpose of this project, we assume that these six pieces of information
           will be used.
         */
        this.tokens = new ArrayList<>(Arrays.asList("firstName","lastName","roomNumber","company","city","greeting"));
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

        /*
        The idea behind this is the following: the user will input some string, which contains tokens based on where in the
        message the token should be substituted for guest/company data. The userInput is split so that this data can be
        passed to returnTokens(), a function which will return an ArrayList of all the tokens found in the string. Thus,
        the string and the appropriate tokens are passed to textify(), which returns the userInput formatted into
        MessageFormat syntax.
         */
        String[] userMod = userInput.split("[^a-zA-Z']+");
        ArrayList<String> tokens = returnTokens(userMod);
        String modifiedString = textify(userInput,tokens);

        JSONObject object = new JSONObject();
        object.put("text",modifiedString);
        object.put("tokens",tokens);

        return object;
    }

    /**
     *
     * @param filename - name of JSON file to be used to save a given message template
     * @param object - JSONObject representing one message template.
     */
    public void save(String filename, JSONObject object){
        try {
            JSONArray data = (JSONArray) jsonParser.parse(new FileReader(filename));
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

    /**
     * @param userSplit - String array representing split text
     * @return - ArrayList of tokens found inside text
     */
    public ArrayList<String> returnTokens(String[] userSplit){
        ArrayList<String> toReturn = new ArrayList<>();
        for (String string: userSplit) {
            if (this.tokens.contains(string)){
                toReturn.add(string);
            }
        }
        return toReturn;
    }

    /**
     *
     * @param input - userInput
     * @param tokens - tokens found in userInput string
     * @return - String formatted to be accepted by MessageFormat methods
     */
    private String textify(String input, ArrayList<String> tokens) {
        String modifiedString = input;
        for (int i = 0; i < tokens.size(); i++) {
            String delimiter = "{" + i + "}";
            modifiedString = modifiedString.replaceAll(tokens.get(i), delimiter);
        }
    return modifiedString;
    }
}
