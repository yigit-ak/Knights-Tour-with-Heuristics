package problem_definition;

import java.util.Optional;

import static util.ArrayHelper.deepCopy;

/**
 * Represents the state of the board in the Knight's Tour problem.
 *
 * @param board                      A 2D boolean array where:
 *                                   true indicates a knight occupies that square,
 *                                   false indicates the square is empty.
 *                                   The first dimension represents rows and the second dimension represents columns (board[row][column]).
 * @param locationOfLastPlacedKnight The location of the last placed knight on the board.
 * @param parent                     The parent state from which this state was derived.
 */
public record State(boolean[][] board, Location locationOfLastPlacedKnight, Optional<State> parent) {
    // to create an initial state
    public State(boolean[][] board, Location locationOfKnightInitially) {
        this(board, locationOfKnightInitially, Optional.empty());
    }

    public State addKnightAt(Location location) throws OutOfMemoryError {
        try {
            boolean[][] newBoard = deepCopy(board);
            newBoard[location.row()][location.column()] = true;
            return new State(newBoard, location, Optional.of(this));
        } catch (OutOfMemoryError e) {
            System.gc();
            throw new OutOfMemoryError();
        }
    }

    public boolean isSolution() {
        for (boolean[] row : board) {
            for (boolean isOccupiedByKnight : row) {
                if (!isOccupiedByKnight)
                    return false;
            }
        }
        return true;
    }
}
