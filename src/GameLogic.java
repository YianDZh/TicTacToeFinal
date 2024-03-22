import java.util.Scanner;

public class GameLogic {
    public int WinningCondition_Size;

    public void Get_WinningCondition(int player_count) {
        //determines the size of the keys that are gonna end the game
        //this is just an user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write the amount of numbers that are considered for the winning condition");
        int number = GameSettings.Verify_Int(scanner);
        while (number < 3 || number > player_count + 1) {
            System.out.println("Please write a valid number of at least 3 and limit of players plus one");
            number = GameSettings.Verify_Int(scanner);
        }
        this.WinningCondition_Size = number;
    }

public int Conditions(char[][] board, int rounds_played, int round_limit) {
        //conditions is just an upper method that determines if the game is ending or not
        if (Check_Rows(board)||Check_Columns(board)||Check_Diagonal(board)){
            //typical winning condition
            return 2;
        }
        else if (rounds_played==round_limit){
            //tie
            return 3;
        }
        //9 is just my favorite number, this basically allows the game to continue
        return 9;
        //I worked with an integer method since a boolean was giving me trouble in the return methods when I was working with earlier versions of the code
    }
    public void Announce(int condition, char[][] board, String winner) {
        //this takes the numbers from the previous method, and just throws an announcement depending on the outcome
        switch (condition) {
            case 2:
                System.out.println("The winner is... " + winner + "!");
                break;
            case 3:
                System.out.println("It is a Tie!");
                break;
            case 9:
                return;
            default:
                break;
        }
    }



    public boolean Check_Rows(char[][] board) {
        for (char[] chars : board) {
            //iterates through the board rows and defines and sends that row into CheckRow
            if (Check_Row(chars)) {
                return true;
            }
        }
        return false;
    }

    public boolean Check_Columns(char[][] board) {
        for (int i = 0; i < board[0].length; i++) {

            char[] column = new char[board.length];
            for (int j = 0; j < board.length; j++) {
                column[j] = board[j][i];
            }
            if (Check_Row(column)) {
                return true;
            }
        }
        return false;
    }

    public boolean Check_Row(char[] row) {
        for (int i = 0; i < row.length - WinningCondition_Size + 1; i++) {
            //so to summarize we are copying again the defined row or array into a key
            char[] key = new char[WinningCondition_Size];
            System.arraycopy(row, i, key, 0, WinningCondition_Size);
            //suggestion of the IDE, was just using += with the array at i and the IDE suggested this change
            if (Key_Checker(key)) {

                return true;
            }
        }
        return false;
    }
    public boolean Check_Diagonal(char[][] board) {
        for (int i = 0; i <= board.length - WinningCondition_Size; i++) {
            for (int j = 0; j <= board.length - WinningCondition_Size; j++) {
                //the reason why we are substracting the size of the winning condition from the board is to limit the number of possibilities
                //this highly prevents us from going out of bounds
                char[] key1 = new char[WinningCondition_Size];
                char[] key2 = new char[WinningCondition_Size];
                char[] key3 = new char[WinningCondition_Size];
                //restarts the keys for winning every time it goes to j
                for (int k = 0; k < WinningCondition_Size; k++) {
                    //what the k loop does is to fill the keys of the game
                    key1[k] = board[i + k][j + k];
                    // Top-left to bottom-right
                    key2[k] = board[i + k][board.length - 1 - j - k];
                    // Top-right to bottom-left
//                    key3[k] = board[i + k][j];
                    // any point in the board
                }
                if (Key_Checker(key1) || Key_Checker(key2) || Key_Checker(key3)) {
                    //run it will break the whole loop returning true if any element on the key is true
                    return true;
                }
            }
        }
        return false;
    }


    public boolean Key_Checker(char[] key) {
        //a key is just an array filled of chars
        //if all of the elements are the same then the winner would be announced thanks to this method
        char firstChar = key[0];
        if (firstChar == ' ' || firstChar == '\0') {
            //if we have nulls or empty slots then the winning condition won't work
            return false;
        }
        for (char currentChar : key) {
            if (currentChar != firstChar) {
                //checks if all elements are the same
                return false;
            }
        }
        //if none of the previous conditions were met then its a winning case
        return true;
    }
}