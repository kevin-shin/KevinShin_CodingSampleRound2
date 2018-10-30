import java.util.*;


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
        boolean nextMessage = true;
        do
            { System.out.println("Enter Company Name: ");
        String selectedCompany = scanner.nextLine();
        System.out.println("--Guest Information--");
        System.out.println("First Name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.println("Here are the available message templates: ");

        Guest guest = new Guest(firstName, lastName);
        Company company = new Company(selectedCompany);
        guestMessage guestMessage = new guestMessage(guest,company);
        GuestManager guestmanager = new GuestManager();
        CompanyManager companyManager = new CompanyManager();

        List guests = guestmanager.allGuests("../data/Guests.json");
        List companies = companyManager.allCompanies("../data/Companies.json");

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

        sampleMessages sample = new sampleMessages(guestMessage.guest, guestMessage.company);
        sample.buildMessage("../data/messageTemplate.json");

        System.out.println("\n" + "Please select a message by typing its index, or type '0' to create your own");
        int answer = scanner.nextInt();
        if (answer == 0) {
            System.out.println("In order to create a template, please type a string below, using the following guidelines: ");
            System.out.println("-use NAME when template should include guest name");
            System.out.println("-use COMPANY when template should include company name");
            System.out.println("-use ROOM when template should include room number");
            System.out.println("Please keep in mind that a greeting variable is attached to the front when composing " +
                    "your message.");

            buildMessage buildMessage = new buildMessage();
            buildMessage.writeNew("../data/Guests.json/messageTemplate.json");

            System.out.println("Template saved to new JSON File.");
        }
        else {
            System.out.println("Great! Here is the final message: ");
            System.out.println(sample.returnMessage(answer));
        }
                System.out.println("\n");
                System.out.println("Would you like to compose another message?");
                scanner.nextLine();
                String userAnswer = scanner.nextLine();
                if (userAnswer.equalsIgnoreCase("No")){
                    nextMessage = false;
            }
            }
        while (nextMessage);
    }

    private void setGuest(Guest guest){
        this.guest = guest;
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





