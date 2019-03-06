import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * Class used to generate sampleMessages. The parses through the messageTemplate.json file and populates the
 * generic templates with the appropriate data through its tokenizer. The messages are stored in an ArrayList,
 * to be returned and used by Program. The system prints an indexing to the messages so the user is able to
 * specify which template they wish to use by entering the appropriate number.
 *
 */

public class sampleMessages {
    private Tokenizer tokenizer;

    public sampleMessages(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public ArrayList<String> buildMessage(String filename){
        ArrayList<String> messageDrafts = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        int i = 1;

        try {
            JSONArray messageTemplates = (JSONArray) jsonParser.parse(new FileReader(filename));
            for (Object obj : messageTemplates) {
                JSONObject object = (JSONObject) obj;
                Object[] tokens = tokenizer.tokenize(object);
                String messageTemplate = (String) object.get("text");
                messageTemplate = messageTemplate.replaceAll("'","''"); //MessageFormat requires double single quote
                MessageFormat form = new MessageFormat(messageTemplate);
                String message = form.format(tokens);
                messageDrafts.add(message);
                System.out.println(i +" - " + message);
                i++;
            }

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        return messageDrafts;
    }
}
