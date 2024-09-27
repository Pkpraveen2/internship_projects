import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{};:,.<>?";
    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter desired password length (minimum 8 characters): ");
        int length = scanner.nextInt();
        while (length < 8) {
            System.out.print("Length must be at least 8. Please enter again: ");
            length = scanner.nextInt();
        }
        boolean includeLowercase = getUserChoice(scanner, "Include lowercase letters? (y/n): ");
        boolean includeUppercase = getUserChoice(scanner, "Include uppercase letters? (y/n): ");
        boolean includeDigits = getUserChoice(scanner, "Include digits? (y/n): ");
        boolean includeSpecial = getUserChoice(scanner, "Include special characters? (y/n): ");
        String password = generatePassword(length, includeLowercase, includeUppercase, includeDigits, includeSpecial);
        System.out.println("Generated Password: " + password);
        
        scanner.close();
    }

    private static boolean getUserChoice(Scanner scanner, String prompt) {
        System.out.print(prompt);
        char choice = scanner.next().toLowerCase().charAt(0);
        return choice == 'y';
    }

    private static String generatePassword(int length, boolean includeLowercase, boolean includeUppercase,
                                           boolean includeDigits, boolean includeSpecial) {
        StringBuilder characterPool = new StringBuilder();
        
        if (includeLowercase) {
            characterPool.append(LOWERCASE);
        }
        if (includeUppercase) {
            characterPool.append(UPPERCASE);
        }
        if (includeDigits) {
            characterPool.append(DIGITS);
        }
        if (includeSpecial) {
            characterPool.append(SPECIAL_CHARACTERS);
        }
        if (characterPool.length() == 0) {
            throw new IllegalArgumentException("At least one character type must be selected.");
        }

        StringBuilder password = new StringBuilder(length);
        if (includeLowercase) {
            password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        }
        if (includeUppercase) {
            password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        }
        if (includeDigits) {
            password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }
        if (includeSpecial) {
            password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));
        }
        for (int i = password.length(); i < length; i++) {
            password.append(characterPool.charAt(random.nextInt(characterPool.length())));
        }
        return shufflePassword(password.toString());
    }

    private static String shufflePassword(String password) {
        char[] passwordArray = password.toCharArray();
        for (int i = 0; i < passwordArray.length; i++) {
            int randomIndex = random.nextInt(passwordArray.length);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[randomIndex];
            passwordArray[randomIndex] = temp;
        }
        return new String(passwordArray);
    }
}