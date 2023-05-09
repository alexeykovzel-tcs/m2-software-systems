package ss.week5.tictactoe.strategy;

import ss.week5.tictactoe.Board;
import ss.week5.tictactoe.Mark;

public interface Strategy {
    String getName();

    int determineMove(Board board, Mark mark);
}
