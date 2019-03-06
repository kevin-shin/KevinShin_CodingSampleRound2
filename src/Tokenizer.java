import org.json.simple.JSONObject;
import java.util.ArrayList;

/**
 * A tokenizer will be the object responsible for allowing the MessageFormat to populate the template
 * with the appropriate guest/company information. Essentially, tokenize is a method which reads the
 * "tokens" object associated with each template and gathers the data corresponding to each token.
 *
 * The advantage of decomposing this function to a specific class is for the mutability of the desired data
 * can easily be accessed. That is to say, if the programmer wishes to modify this program to accommodate more
 * tokens or change the meaning of the tokens, they simply have to modify the tokenize function to adjust.
 *
 */


public class Tokenizer {
    Guest guest;
    Company company;

    public Tokenizer(Guest guest, Company company){
        this.guest = guest;
        this.company = company;
    }

    /**
     * Retrieves the tokens associated with each template, and returns an array filled with the data the tokens
     * correlate to.
     * @param message - JSONObject representing a message template
     * @return Object[] containing approrpriate company/guest data.
     */
    public Object[] tokenize(JSONObject message) {
        ArrayList<String> tokens = (ArrayList<String>) message.get("tokens");
        ArrayList<String> userInfo = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equalsIgnoreCase("firstName")){
                userInfo.add(this.guest.getFirstName());
            }
            if (tokens.get(i).equalsIgnoreCase("lastName")){
                userInfo.add(this.guest.getLastName());
            }
            if (tokens.get(i).equalsIgnoreCase("roomNumber")){
                userInfo.add(Long.toString(this.guest.getroomNumber()));
            }
            if (tokens.get(i).equalsIgnoreCase("company")){
                userInfo.add(this.company.getCompany());
            }
            if (tokens.get(i).equalsIgnoreCase("city")){
                userInfo.add(this.company.getCity());
            }
            if (tokens.get(i).equalsIgnoreCase("greeting")){
                greetingVariable greeting = new greetingVariable(this.company);
                userInfo.add(greeting.returnGreeting());
            }
        }
        return userInfo.toArray();
    }
}
