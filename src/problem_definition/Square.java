package problem_definition;

public record Square(
        int row,
        int column) {

    public boolean isValid(boolean[][] board) {
        int boardSize = board.length;
        return isWithinBoard(boardSize) && isEmpty(board);
    }

    private boolean isWithinBoard(int boardSize) {
        return row >= 0 && row < boardSize && column >= 0 && column < boardSize;
    }

    private boolean isEmpty(boolean[][] board) {
        return !board[row][column];
    }
}
