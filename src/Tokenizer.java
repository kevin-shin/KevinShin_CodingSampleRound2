import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Tokenizer {
    Guest guest;
    Company company;

    public Tokenizer(Guest guest, Company company){
        this.guest = guest;
        this.company = company;
    }

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
        }
        return userInfo.toArray();
    }
}
