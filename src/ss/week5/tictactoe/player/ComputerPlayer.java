package ss.week5.tictactoe.player;

import ss.week5.tictactoe.Board;
import ss.week5.tictactoe.Mark;
import ss.week5.tictactoe.strategy.NaiveStrategy;
import ss.week5.tictactoe.strategy.Strategy;

public class ComputerPlayer extends Player{
    private final Strategy strategy;

    public ComputerPlayer(Mark mark) {
        this(mark, new NaiveStrategy());
    }

    public ComputerPlayer(Mark mark, Strategy strategy) {
        super(String.format("%s-%s", strategy.getName(), mark), mark);
        this.strategy = strategy;
    }

    @Override
    public int determineMove(Board board) {
        return strategy.determineMove(board, this.getMark());
    }
}
