import java.util.Scanner;
import java.util.InputMismatchException;


public class GameSettings {

    public static int Get_Players() {
        //prompts user to put the number of players
        //gets the input
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Input the number of players that are going to be playing (2-9)");
        //Prompting specifically to be from 2 to 9
        int players = GameSettings.Verify_Int(scan);
        if (players > 9 || players < 2) {
            do {
                //in case they put an invalid number is going to be prompted again
                System.out.println("Please Input a valid number between 2-9");
                players = GameSettings.Verify_Int(scan);
            }
            while (players > 9 || players < 2);

        }
        return players;
    }

    public static Player[] Create_Players(int player_count) {
        //generates players with their names and stores them in an array
        Player[] Player_Group = new Player[player_count];
        for (int i = 0; i < player_count; i++) {
            Player_Group[i] = new Player();
            Player_Group[i].Get_Info(Player_Group, i + 1);
            System.out.println();
        }
        return Player_Group;
    }

    public static boolean In_Range(int x, int y) {
        //determines if the user input is valid, specifically in range of the board, this fix still gives out of bounds in very specific situations
        return x >= 1 && x <= Board.number_players + 1 && y >= 1 && y <= Board.number_players + 1;
        //giant numbers are a problem for this method
    }
    public static int Verify_Int(Scanner scanner) {
        //it verifies if the user doesn't plug in an integer
        boolean isValidInput = false;
        int userInput=0;
        while (!isValidInput) {
            System.out.print("Please enter a number: ");
            try {
                userInput = scanner.nextInt();
                isValidInput = true; // Set to true if input is valid to exit the loop
            } catch (InputMismatchException e) {
                System.out.println("Error! Input not valid. Please enter an integer.");
                scanner.nextLine();
                //using nextLine, the program won't crash since next.int is going to crash it. However the issue with this format is that
                //if an integer is plugged in at the beggining of a sentence it will consider the integer ignoring the rest
                //for example 6 abs will be a 6
            }
        }
        return userInput;
        //by returning userInput we are just ensuring try and catch worked
    }
}


