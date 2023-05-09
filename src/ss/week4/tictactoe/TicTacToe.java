package ss.week4.tictactoe;

import ss.utils.TextIO;

public class TicTacToe {
    public static void main(String[] args) throws InvalidPlayersException {
        if (args.length == 2) {
            Player[] players = {
                    new HumanPlayer(args[0], Mark.XX),
                    new HumanPlayer(args[1], Mark.OO)};

            Game game = new Game(players[0], players[1]);
            game.start();
        } else {
            System.out.println("1-st player name: [O]");
            String player1 = TextIO.getlnString();

            System.out.println("2-nd player name: [X]");
            String player2 = TextIO.getlnString();

            Player[] players = {
                    new HumanPlayer(player1, Mark.OO),
                    new HumanPlayer(player2, Mark.XX)};

            Game game = new Game(players[0], players[1]);
            game.start();
        }
    }


    static class InvalidPlayersException extends Exception {
        public InvalidPlayersException(String message) {
            super(message);
        }
    }
}
