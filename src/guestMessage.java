import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;

public class guestMessage {
    private String guestName;
    private String company;
    private long roomNumber;
    private long time;
    private String greeting;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JSONParser jsonParser = new JSONParser();

        System.out.println("Enter Guest Name: ");
        String selectedGuest = scanner.next();
        System.out.println("Enter Company Name: ");
        String selectedCompany = scanner.next();
        System.out.println("Type 'Messages' to see pre-written Message Templates, or 'Other' to compose a new template.");
        String userInput = scanner.next();

        if (userInput.equalsIgnoreCase("Messages")) {

            try {
                JSONArray messages = (JSONArray) jsonParser.parse(new FileReader("/Users/kevinshin/Desktop/KevinShin - CodingSample/CodingProject/messageTemplate.json"));
                for (Object obj : messages) {
                    String example = "Jane Doe";
                    JSONObject message = (JSONObject) obj;


                }

            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            } catch (ParseException exception) {
                exception.printStackTrace();
            }


            }
        }
    }





