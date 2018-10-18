import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;

public class guestMessage {
    private Guest guest;
    private Company company;
    private String greetingVariable;
    private long roomNumber;
    private long startTimestamp;
    private long endTimestamp;

    public guestMessage(Guest guest, Company company){
        this.guest = guest;
        this.company = company;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Company Name: ");
        String selectedCompany = scanner.nextLine();
        System.out.println("--Guest Information--");
        System.out.println("First Name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.println("Type 'Messages' to see preloaded templates, or 'Build' to create your own");
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("Messages")){

        }

        Guest guest = new Guest(firstName, lastName);
        Company company = new Company(selectedCompany);
        guestMessage guestMessage = new guestMessage(guest,company);
        GuestManager guestmanager = new GuestManager();
        CompanyManager companyManager = new CompanyManager();

        List guests = guestmanager.allGuests("/Users/kevinshin/Desktop/KevinShin - CodingSample/CodingProject/Guests.json");
        List companies = companyManager.allCompanies("/Users/kevinshin/Desktop/KevinShin - CodingSample/CodingProject/Companies.json");

        for (int i = 0; i < guests.size(); i++){
            Guest sampleGuest = (Guest) guests.get(i);
            if (guest.equals(sampleGuest)){
                guestMessage.setGuest(sampleGuest);
            }
        }
        for (int i = 0; i<companies.size();i++){
            Company sampleCompany = (Company) companies.get(i);
            if (company.equals(sampleCompany)){
                guestMessage.setCompany(sampleCompany);
            }
        }
        sampleMessages sampleMessages = new sampleMessages(guestMessage.guest, guestMessage.company);
        ArrayList ordering = sampleMessages.returnOrdering("/Users/kevinshin/Desktop/KevinShin - CodingSample/CodingProject/testMessage.json");

    }

    private void setGuest(Guest guest){
        this.guest = guest;
    }

    private void setRoomNumber(long number){
        this.roomNumber = number;
    }

    private void setCompany(Company company){
        this.company= company;
    }

    public String toString(){
        String description = "Guest Name: " + guest.getFirstName() + " " + guest.getLastName()
                + "\n" + "Company Name: "
                + "\n" + "Room Number: " + roomNumber;
        return description;
    }
    }





