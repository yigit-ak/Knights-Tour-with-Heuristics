package problem_definition;

import java.util.Optional;

import static util.array_helper.deepCopy;

/**
 * @param board true: a knight occupies that square
 *              false: square is empty
 *              first dimension represents row and second dimension represents column -> board[row][column]
 */
public record State(boolean[][] board, Location locationOfLastPlacedKnight, Optional<State> parent) {
    // to create an initial state
    public State(boolean[][] board, Location locationOfKnightInitially) {
        this(board, locationOfKnightInitially, Optional.empty());
    }

    public State addKnightAt(Location location) {
        boolean[][] newBoard = deepCopy(board);

        int row = location.row();
        int column = location.column();
        newBoard[row][column] = true;

        return new State(newBoard, location, Optional.of(this));
    }

    public boolean isSolution() {
        for (boolean[] row : board) {
            for (boolean isOccupiedByKnight : row) {
                if (!isOccupiedByKnight) return false;
            }
        }
        return true;
    }
}
