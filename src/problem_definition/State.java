package problem_definition;

import java.util.Arrays;

import static util.array_helper.deepCopy;

public class State {
    /*
     * true: a knight occupies that square
     * false: square is empty
     * first dimension represents row and second dimension represents column -> board[row][column]
     */
    private final boolean[][] board;
    private final KnightLocation locationOfLastPlacedKnight;


    public State(boolean[][] board, KnightLocation locationOfLastPlacedKnight) {
        this.board = board;
        this.locationOfLastPlacedKnight = locationOfLastPlacedKnight;
    }


    public State addKnight(KnightLocation location) {
        boolean[][] newBoard = deepCopy(board);
        int row = location.row();
        int column = location.column();
        newBoard[row][column] = true;
        return new State(newBoard, location);
    }


    public boolean isSolution() {
        // TODO: Check if the current state is a solution (all knights have been placed on the board)
        return false;
    }


    public boolean[][] getBoard() {
        return board;
    }

    public KnightLocation getLocationOfLastPlacedKnight() {
        return locationOfLastPlacedKnight;
    }
}
