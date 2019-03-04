import java.util.ArrayList;
import java.util.Scanner;


public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean nextMessage = true;
        do {
            System.out.println("Enter Company Name: ");
            String selectedCompany = scanner.nextLine();
            System.out.println("--Guest Information--");
            System.out.println("First Name: ");
            String firstName = scanner.nextLine();
            System.out.println("Last Name: ");
            String lastName = scanner.nextLine();

            System.out.println("Here are the available message templates: ");

            GuestManager guestmanager = new GuestManager();
            CompanyManager companyManager = new CompanyManager();
            sampleMessages sample = new sampleMessages();

            ArrayList<Guest> guests = guestmanager.allGuests("./data/Guests.json");
            ArrayList<Company> companies = companyManager.allCompanies("./data/Companies.json");

            for (int i = 0; i < guests.size(); i++){
                Guest loadedGuest = guests.get(i);
                if (loadedGuest.getFirstName().equalsIgnoreCase(firstName) &&
                        loadedGuest.getLastName().equalsIgnoreCase(lastName)){
                    sample.setGuest(loadedGuest);
                }
            }
            for (int i = 0; i<companies.size();i++){
                Company loadedCompany = companies.get(i);
                if (loadedCompany.getCompany().equalsIgnoreCase(selectedCompany)){
                    sample.setCompany(loadedCompany);
                }
            }

            sample.buildMessage("./data/messageTemplate.json");

            System.out.println("\n" + "Please select a message by specifying its index, or type '0' to create your own");
            int answer = scanner.nextInt();
            if (answer == 0) {
                System.out.println("In order to create a template, please type a string below, using the following guidelines: ");
                System.out.println("-use NAME when template should include guest name");
                System.out.println("-use COMPANY when template should include company name");
                System.out.println("-use ROOM when template should include room number");

                buildMessage buildMessage = new buildMessage();
                buildMessage.writeNew("../data/messageTemplate.json");

                System.out.println("Template saved to new JSON File.");
            } else {
                System.out.println("Great! Here is the final message: ");
                System.out.println(sample.returnMessage(answer));
            }
            System.out.println("\n");
            System.out.println("Would you like to compose another message?");
            scanner.nextLine();
            String userAnswer = scanner.nextLine();
            if (userAnswer.equalsIgnoreCase("No")) {
                nextMessage = false;
            }
        }
        while (nextMessage);
    }
}