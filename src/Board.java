public class Board {
    public static int number_players;
    private int size;
    //the size refers to the board dimensions
    private char [][] board;
    public static int round_limit;
    public void Update_data(int players) {
        //board data updates the value of players
        number_players=players;
        //defines the size with the amount of players
        this.size=players+1;
        round_limit=size*size;
        //defines the board
        this.board=new char[size][size];
    }
    public void Lines (){
        //this method required a lot of trial and error
        //what it does is basically printing a line that separates each row
        int times = number_players-1;
        System.out.print(" ___________________");
        //this one is defined as the starting line, is the only one printing for a case of 2 players
        for (int i = 1; i<times; i++){
            //this loop will run depending on the total ammount of players
            System.out.print("_______");
            //it works perfectly
        }
        System.out.println(" ");
    }

    public void PrintBoard() {
        Lines();
        //prints a top line. Just a design choice, not really significant
        for (int i = 0; i < size; i++) {
            //each iteration of i prins every row
            for (int j = 0; j < size; j++) {
                //each iteration in J prints one single square
                System.out.print(" | "+board[i][j] + " | ");
            }
            System.out.println();
            Lines();
            //leaves space for next row
        }
    }
    public static boolean Element_Empty(char arrayCoordinate) {
        //determines wheter or not the element is in use
        return arrayCoordinate == '\0';
    }
    public char[][] getBoard() {
        return this.board;
    }

}

