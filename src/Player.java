import java.util.Scanner;

public class Player {
    //    public int players_num=GameSettings.Get_Players();
    private String playerName;
    private char symbol;

    public String Get_PlayerName() {
        return playerName;
    }

    private boolean Duplicated_Name(String name, Player[] existingPlayers) {
        for (Player player : existingPlayers) {
            //IDE suggestion to use enhanced for loop
            //Original form int i =0;i<existingPlayers.length;i++{
            //Player player = existing Players [i]
            if (player != null && name.equals(player.playerName)) {
                return true;
            }
        }
        return false;
    }

    private boolean Duplicated_Symbol(char symbol, Player[] existingPlayers) {
        for (Player player : existingPlayers) {
            //same as Duplicated name
            if (player != null && symbol == player.symbol) {
                return true;
            }
        }
        return false;
    }

    public void Get_Info(Player[] ArrayOf, int player_num) {
        //Summary: Code asks the user to input a name and char which is going to be used to identify them
        //the private boolean methods above are only to check if those names and chars aren't repeated
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write the name you want to use Player " + player_num);
        //getting players name
        String current_Name = scanner.next();
        while (Duplicated_Name(current_Name, ArrayOf)) {
            System.out.println("Name already in use, please enter a different or valid one.");
            current_Name = scanner.next();
        }
        this.playerName = current_Name;
        System.out.println("Please write the character you want to use in-game Player " + player_num);
        char current_char;
        current_char = scanner.next().charAt(0);
        while (Duplicated_Symbol(current_char, ArrayOf)) {
            System.out.println("Symbol already in use, please enter a different or valid one.");
            current_char = scanner.next().charAt(0);
        }
        //getting char input from user
        this.symbol = current_char;
        System.out.println("Welcome " + current_Name + " your symbol in-game is " + current_char);
    }

    public void Get_Input(Player current_player, Board board, char[][] board_as_array) {
        Scanner scanner = new Scanner(System.in);
        int coordinate_x, coordinate_y;
        PlayerTurn(current_player.playerName);
        System.out.println("Please write the coordinates in which you want to add your symbol (x first and then y, plug in individually)");
        coordinate_x = GameSettings.Verify_Int(scanner);
        coordinate_y = GameSettings.Verify_Int(scanner);
        //gets coordinate input from player and sends it through the Game Setting Verify Int
        while (!GameSettings.In_Range(coordinate_x, coordinate_y)) {
            //in the case the coordinate is not valid, the in range boolean would check if it is or not in range, for example
            //if we have a 3x3 board but the input is 5 then it would ask for the input again
            System.out.println("Please write a valid coordinate");
            coordinate_x = GameSettings.Verify_Int(scanner);
            coordinate_y = GameSettings.Verify_Int(scanner);
        }
        while (!Board.Element_Empty(board_as_array[coordinate_y - 1][coordinate_x - 1])) {
            //checks if the coordinate is in use or not
            System.out.println("Please write a coordinate that is not in use");
            coordinate_x = GameSettings.Verify_Int(scanner);
            coordinate_y = GameSettings.Verify_Int(scanner);
        }
        board_as_array[coordinate_y - 1][coordinate_x - 1] = current_player.symbol;
        //this calculation will put the array in the expected coordinate by the user
        board.PrintBoard();
        //and the get input will then print the board at the end of it
        //this could go out of this method but due to early testing issues it was better working here
    }


    public void PlayerTurn (String playerName){
        System.out.println("It is " + playerName + " turn.");
    }

}