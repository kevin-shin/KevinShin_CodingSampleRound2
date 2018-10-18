import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;


public class CompanyManager {
    private List<Company> list;

    public CompanyManager(){
        this.list = new ArrayList<>();
    }

    public List<Company> allCompanies(String filename) {
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray data = (JSONArray) jsonParser.parse(new FileReader(filename));
            for (Object obj : data) {
                JSONObject companyObject = (JSONObject) obj;
                long id = (long) companyObject.get("id");
                String companyName = (String) companyObject.get("company");
                String city = (String) companyObject.get("city");
                String timezone = (String) companyObject.get("timezone");


                Company company = new Company(companyName, id, city, timezone);
                list.add(company);
            }

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        return list;
    }
}