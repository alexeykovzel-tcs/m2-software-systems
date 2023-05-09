package ss.week5.tictactoe.strategy;

import ss.week5.tictactoe.Board;
import ss.week5.tictactoe.Mark;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SmartStrategy implements Strategy {
    @Override
    public String getName() {
        return "smart-computer";
    }

    @Override
    public int determineMove(Board board, Mark mark) {
        // return the center index if its field is empty
        int centerIdx = board.fields.length / 2;
        if (board.isEmptyField(centerIdx)) return centerIdx;

        List<Integer> indexes = getIndexesOfEmptyFields(board);

        // determine if there is a winning index and return it
        Optional<Integer> winningIdx = indexes.stream().filter(i -> isWin(i, board, mark)).findFirst();
        if (winningIdx.isPresent()) return winningIdx.get();

        // determine if there is a loosing index and return it
        // otherwise return a random empty field
        Optional<Integer> losingIdx = indexes.stream().filter(i -> isLose(i, board, mark)).findFirst();
        return losingIdx.orElseGet(() -> indexes.get((int) (Math.random() * indexes.size())));
    }

    private List<Integer> getIndexesOfEmptyFields(Board board) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < Board.DIM * Board.DIM; i++) {
            if (board.fields[i] == Mark.EMPTY) indexes.add(i);
        }
        return indexes;
    }

    private boolean isWin(int i, Board board, Mark mark) {
        Board boardCopy = board.deepCopy();
        boardCopy.setField(i, mark);
        return boardCopy.isWinner(mark);
    }

    private boolean isLose(int i, Board board, Mark mark) {
        Board boardCopy = board.deepCopy();
        Mark inverseMark = (mark == Mark.XX) ? Mark.OO : Mark.XX;
        boardCopy.setField(i, inverseMark);
        return boardCopy.isWinner(inverseMark);
    }
}
