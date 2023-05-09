package ss.week5.tictactoe.strategy;

import ss.week5.tictactoe.Board;
import ss.week5.tictactoe.Mark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class NaiveStrategy implements Strategy {
    @Override
    public String getName() {
        return "native-computer";
    }

    @Override
    public int determineMove(Board board, Mark mark) {
        List<Integer> indexes = getIndexesOfEmptyFields(board);
        return indexes.get((int) (Math.random() * indexes.size()));
    }

    private List<Integer> getIndexesOfEmptyFields(Board board) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < Board.DIM * Board.DIM; i++) {
            if (board.fields[i] == Mark.EMPTY) indexes.add(i);
        }
        return indexes;
    }
}
