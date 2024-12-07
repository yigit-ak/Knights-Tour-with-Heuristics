package problem_definition;

import static util.array_helper.deepCopy;

/**
 * @param board true: a knight occupies that square
 *              false: square is empty
 *              first dimension represents row and second dimension represents column -> board[row][column]
 */
public record State(boolean[][] board, Square locationOfLastPlacedKnight) {
    public State addKnight(Square location) {
        boolean[][] newBoard = deepCopy(board);

        int row = location.row();
        int column = location.column();
        newBoard[row][column] = true;

        return new State(newBoard, location);
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
