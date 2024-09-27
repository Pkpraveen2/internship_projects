import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    private static final String[] OPTIONS = {"rock", "paper", "scissors"};
    private static final Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userChoice;
        String computerChoice;
        String playAgain;

        do {
            System.out.print("Enter your choice (rock, paper, scissors): ");
            userChoice = scanner.nextLine().toLowerCase();

            // Validate user choice
            if (!isValidChoice(userChoice)) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            computerChoice = OPTIONS[random.nextInt(OPTIONS.length)];
            System.out.println("Computer chose: " + computerChoice);
            determineWinner(userChoice, computerChoice);

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.nextLine().toLowerCase();
        } while (playAgain.equals("yes"));

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    private static boolean isValidChoice(String choice) {
        for (String option : OPTIONS) {
            if (option.equals(choice)) {
                return true;
            }
        }
        return false;
    }

    private static void determineWinner(String user, String computer) {
        if (user.equals(computer)) {
            System.out.println("It's a tie!");
        } else if ((user.equals("rock") && computer.equals("scissors")) ||
                   (user.equals("paper") && computer.equals("rock")) ||
                   (user.equals("scissors") && computer.equals("paper"))) {
            System.out.println("You win!");
        } else {
            System.out.println("You lose!");
        }
    }
}
