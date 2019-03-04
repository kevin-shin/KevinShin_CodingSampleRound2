import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.*;

public class expermient {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(">>");
        String userInput = scanner.nextLine();
        //String userInput = "Hello, and welcome to COMPANY, NAME. We hope you enjoy your stay! -COMPANY";
        String[] newInput = userInput.split("([.,!?:;'\"-]|\\s)+");
        String[] tokens = { "id", "firstName", "lastName",  "roomNumber",
                "startTimestamp", "endTimestamp", "company"};

        System.out.println(newInput.toString());
        ArrayList<String> tokensUsed = new ArrayList<>();
        for (String word: newInput){
            if (Arrays.asList(tokens).contains(word)){
                tokensUsed.add(word);
            }
        }

        String modifiedString = userInput;
        for (int i = 0; i < tokensUsed.size(); i++) {
            String delimiter = "{" + i + "}";
            modifiedString = modifiedString.replaceAll(tokensUsed.get(i),delimiter);
        }

        ArrayList<Object> tokens2 = new ArrayList<>();
        System.out.println("Now enter tokens: ");
        System.out.println("Room number: ");
        int number = scanner.nextInt();
        scanner.nextLine();
        tokens2.add(number);
        System.out.println("Company Name: ");
        String company = scanner.nextLine();
        tokens2.add(company);
        System.out.println("Name: ");
        String name = scanner.nextLine();
        tokens2.add(name);

        System.out.println(tokens2.toString());
        System.out.println(modifiedString);

        MessageFormat form = new MessageFormat(modifiedString);
        System.out.println(form.format(tokens));
    }
}

