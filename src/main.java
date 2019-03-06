import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean nextMessage = true;
        System.out.println(
                " --------------------------------------------------------------------------------------------" + "\n" +
                "|       Welcome to the Message Generator! This software allows the user to efficiently       |" + "\n" +
                "|       generate personalized messages based on data uploaded via a JSON file on guest       |" + "\n" +
                "|       and company information.                                                             |" + "\n" +
                " --------------------------------------------------------------------------------------------"
        );
        do {
            Program program = new Program();
            program.run();
            System.out.println("\n"+ "Would you like to compose another message?");
            String userAnswer = scanner.nextLine();
            if (userAnswer.equalsIgnoreCase("No")) {
                nextMessage = false;
            }
        }
        while (nextMessage);
    }
}