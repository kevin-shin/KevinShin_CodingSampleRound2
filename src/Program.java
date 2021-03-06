import org.json.simple.JSONObject;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    GuestManager guestManager;
    CompanyManager companyManager;
    sampleMessages sample;
    ArrayList<Guest> guests;
    ArrayList<Company> companies;
    Guest guest;
    Company company;
    Tokenizer tokenizer;

    public void Program() {
    }

    public void run() {

        //---------INIT---------
        Scanner scanner = new Scanner(System.in);
        this.guestManager = new GuestManager();
        this.companyManager = new CompanyManager();

        this.guests = guestManager.allGuests("./data/Guests.json");
        this.companies = companyManager.allCompanies("./data/Companies.json");

        System.out.println("\n" + "*--------COMPANY INFORMATION--------*");

        //prompts user to enter company name; loops until company is matched with JSON data
        boolean acceptedCompany = false;
        do {
            System.out.println("Enter Company Name: ");
            String selectedCompany = scanner.nextLine();
            Company tempCompany = new Company(selectedCompany);
            boolean companyNoRecord = true;
            for (int i = 0; i < companies.size(); i++) {
                Company loadedCompany = companies.get(i);
                if (loadedCompany.equals(tempCompany)) {
                    this.company = loadedCompany;
                    companyNoRecord = false;
                    acceptedCompany = true;
                }
            }
            if (companyNoRecord) {
                System.out.println("Company not found in records. Please try again.");
            }
        } while (!acceptedCompany);

        //Similar logic to company
        System.out.println("*--------GUEST INFORMATION--------*");
        boolean acceptedGuest = false;
        do {
            System.out.println("First Name: ");
            String firstName = scanner.nextLine();
            System.out.println("Last Name: ");
            String lastName = scanner.nextLine();
            Guest tempGuest = new Guest(firstName, lastName);
            boolean guestNoRecord = true;

            for (int i = 0; i < guests.size(); i++) {
                Guest loadedGuest = guests.get(i);
                if (loadedGuest.equals(tempGuest)) {
                    this.guest = loadedGuest;
                    guestNoRecord = false;
                    acceptedGuest = true;
                }
            }
            if (guestNoRecord) {
                System.out.println("Guest not found in records. Please try again.");
            }
        } while (!acceptedGuest);


        //Initialize tokenizer and sampleMessages generator
        this.tokenizer = new Tokenizer(this.guest, this.company);
        this.sample = new sampleMessages(tokenizer);
        boolean properInput = false;

        do {
            System.out.println("Here are the available message templates: ");
            ArrayList<String> messageDrafts = sample.buildMessage("./data/messageTemplate.json");
            System.out.println("\n"+"Please select a message by specifying its index, or type '0' to create your own");
            System.out.println(">>> ");
            int answer = scanner.nextInt();
            //User has specified that they want to create their own template
            if (answer == 0) {
                buildMessage buildMessage = new buildMessage();
                JSONObject object = buildMessage.writeNew();

                Object[] tokens = tokenizer.tokenize(object);
                String template = (String) object.get("text");
                template = template.replaceAll("'", "''"); //MessageFormat requires double apostrophe

                MessageFormat form = new MessageFormat(template);
                String message = form.format(tokens);

                System.out.println("Here is a sample message: ");
                System.out.println(message);

                System.out.println("Do you want to save this template? Yes/No");
                scanner.nextLine();
                String userAnswer = scanner.nextLine();
                if (userAnswer.equalsIgnoreCase("Yes")) {
                    buildMessage.save("./data/messageTemplate.json", object);
                    System.out.println("Template saved to JSON File." + "\n");
                    System.out.println("Here is the final message: ");
                    System.out.println(message);
                    properInput = true;
                }

                //return message picked
            } else {
                if (answer > messageDrafts.size() || answer < 0) {
                    System.out.println("Error in indexing. Choose a valid option, or type '0' to create a new message template.");
                } else {
                    System.out.println("Here is the final message: ");
                    System.out.println(messageDrafts.get(answer - 1));
                    properInput = true;
                }
            }
        } while (!properInput);

    }
}
