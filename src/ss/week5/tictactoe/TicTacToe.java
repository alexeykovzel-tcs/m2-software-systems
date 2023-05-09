package ss.week5.tictactoe;

import ss.utils.TextIO;
import ss.week5.tictactoe.player.ComputerPlayer;
import ss.week5.tictactoe.player.HumanPlayer;
import ss.week5.tictactoe.player.Player;
import ss.week5.tictactoe.strategy.NaiveStrategy;
import ss.week5.tictactoe.strategy.SmartStrategy;
import ss.week5.tictactoe.strategy.Strategy;

public class TicTacToe {
    public static void main(String[] args) {
        if (args.length == 0) {
            String[] playerNames = getPlayerNames();
            startDoublePlayerGame(playerNames[0], playerNames[1]);

        } else if (args.length == 2) {
            if (args[0].equals("wim")) {
                switch (args[1]) {
                    case "-N":
                        startAIPlayerGame(new NaiveStrategy());
                        break;
                    case "-S":
                        startAIPlayerGame(new SmartStrategy());
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid strategy: " + args[1]);
                }
            } else {
                startDoublePlayerGame(args[0], args[1]);
            }
        } else {
            throw new IllegalArgumentException("Illegal number of arguments");
        }
    }

    private static String[] getPlayerNames() {
        String[] playerNames = new String[2];

        System.out.println("1-st player name: [O]");
        playerNames[0] = TextIO.getlnString();

        System.out.println("2-nd player name: [X]");
        playerNames[1] = TextIO.getlnString();

        return playerNames;
    }

    private static void startDoublePlayerGame(String player1, String player2) {
        Player[] players = {
                new HumanPlayer(player1, Mark.OO),
                new HumanPlayer(player2, Mark.XX)};
        startGame(players);
    }

    private static void startAIPlayerGame(Strategy strategy) {
        Player[] players = {
                new ComputerPlayer(Mark.OO, strategy),
                new HumanPlayer("You", Mark.XX)};
        startGame(players);
    }

    private static void startGame(Player[] players) {
        Game game = new Game(players[0], players[1]);
        game.start();
    }
}
