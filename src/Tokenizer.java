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

        String[] tokens = convertJSONArray((JSONArray) message.get("tokens"));
        ArrayList<String> userInfo = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equalsIgnoreCase("firstName")){
                userInfo.add(this.guest.getFirstName());
            }
            if (tokens[i].equalsIgnoreCase("lastName")){
                userInfo.add(this.guest.getLastName());
            }
            if (tokens[i].equalsIgnoreCase("roomNumber")){
                userInfo.add(Long.toString(this.guest.getroomNumber()));
            }
            if (tokens[i].equalsIgnoreCase("company")){
                userInfo.add(this.company.getCompany());
            }
            if (tokens[i].equalsIgnoreCase("city")){
                userInfo.add(this.company.getCity());
            }
        }
        return userInfo.toArray();
    }

    private String[] convertJSONArray(JSONArray array){
        String[] newArray = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            newArray[i] = (String) array.get(i);
        }
        return newArray;
    }
}
