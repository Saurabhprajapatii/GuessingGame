import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);

        Random random = new Random();

        int secreteNumber = random.nextInt(100) + 1;

        int guess = 0;

        int attempts = 0;

        System.out.println("=== Number Guessing Game ===");
        System.out.println("Guess a number between 1 to 100 ");

        while(guess != secreteNumber){
            System.out.println("Enter your number");
            guess = sc.nextInt();
            attempts++;

            if (guess < secreteNumber){
                System.out.println("Too low..");
            }
            else if(guess > secreteNumber){
                System.out.println("Too high..");
            }
            else {
                System.out.println("Congratulations!");
                System.out.println("You guess the number.\n Attempts " + attempts);
            }
        }
        sc.close();
    }
}

