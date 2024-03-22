public class Testing {
    public static void main(String[] args) {
        Board game = new Board();
        int round_count = 0;
        GameLogic breaker = new GameLogic();
        int player_count = GameSettings.Get_Players();
        game.Update_data(player_count);
        Player[] Player_Group = GameSettings.Create_Players(player_count);
        breaker.Get_WinningCondition(player_count);
        //breaker is a GameLogic object
        System.out.println("The match starts now!");
        game.PrintBoard();
        boolean ultimate_condition = false;

        do {
            String winner = null;
            for (Player currentPlayer : Player_Group) {
                int determiner=breaker.Conditions(game.getBoard(), round_count, Board.round_limit);
                //loops through an Array of players,
                winner = currentPlayer.Get_PlayerName();
                //defines the playerName as the current Player name
                if (determiner==2||determiner==3) {
                    //if a win condition is found the ultimate condition variable changes
                    ultimate_condition = true;
                    break;
                    //and breaks out of the inner loop
                }
                currentPlayer.Get_Input(currentPlayer, game, game.getBoard());
                round_count++;
                breaker.Announce(breaker.Conditions(game.getBoard(), round_count, Board.round_limit), game.getBoard(), winner);
                //inside of the GameLogic if the winner is determined it will output the message of the winner

            }
            //asks the current player for input, it uses the array of players, the board, adds a round count and additionally should be able to break


        }
        while (!ultimate_condition);
            //while rounds are less than limit
     }
}
