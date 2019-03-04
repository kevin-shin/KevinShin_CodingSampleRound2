import java.util.ArrayList;
import java.util.Scanner;


public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean nextMessage = true;
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