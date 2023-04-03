package Utilities;
import java.util.Random;

public class RandomEmailGenerator {
    String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    String[] DOMAINS = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com"};

    public String generateEmail() {
        Random rand = new Random();
        StringBuilder nameBuilder = new StringBuilder();
        int nameLength = rand.nextInt(10) + 5; // Generate a name with length between 5 and 14 characters
        for (int i = 0; i < nameLength; i++) {
            char randomChar = CHARACTERS.charAt(rand.nextInt(CHARACTERS.length()));
            nameBuilder.append(randomChar);
        }
        String name = nameBuilder.toString();
        String domain = DOMAINS[rand.nextInt(DOMAINS.length)];
        return name.toLowerCase() + "@" + domain;
    }
}
