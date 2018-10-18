import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;

public class guestMessage {
    private Guest guest;
    private String greetingVariable;
    private String guestName;
    private String companyName;
    private long roomNumber;
    private long startTimestamp;
    private long endTimestamp;

    public guestMessage(String guestName, String companyName){
        this.guestName = guestName;
        this.companyName = companyName;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Guest Name: ");
        String selectedGuest = scanner.nextLine();
        System.out.println("Enter Company Name: ");
        String selectedCompany = scanner.nextLine();
        System.out.println("Type 'Messages' to see pre-written Message Templates, or 'Other' to compose a new template.");
        String userInput = scanner.nextLine();

        guestMessage guestMessage = new guestMessage(selectedGuest,selectedCompany);
        GuestManager guestmanager = new GuestManager();
        CompanyManager companyManager = new CompanyManager();
        List guests = guestmanager.allGuests("/Users/kevinshin/Desktop/KevinShin - CodingSample/CodingProject/Guests.json");
        List companies = companyManager.allCompanies("/Users/kevinshin/Desktop/KevinShin - CodingSample/CodingProject/Companies.json");
        for (int i = 0; i < guests.size(); i++){
            Guest guest = (Guest) guests.get(i);
            if (guest.toString().equalsIgnoreCase(guestMessage.guestName)){
                guestMessage.setGuest(guest);
            }
        }
        for (int i = 0; i<companies.size();i++){

        }
    }

    private void setGuest(Guest guest){
        this.guest = guest;
    }

    private void setRoomNumber(long number){
        this.roomNumber = number;
    }

    private void setCompanyName(Company company){
        this.companyName = companyName;
    }
    }





