package problem_definition;

import java.util.List;
import java.util.stream.Stream;

public record Location(
        int row,
        int column) {

    private boolean isValid(boolean[][] board) {
        int boardSize = board.length;
        return isWithinBoard(boardSize) && isEmpty(board);
    }

    private boolean isWithinBoard(int boardSize) {
        return row >= 0 && row < boardSize && column >= 0 && column < boardSize;
    }

    private boolean isEmpty(boolean[][] board) {
        return !board[row][column];
    }

    public List<Location> getLocationsForNextMove(boolean[][] board) {
        Location[] nextPossibleLocations = {
                new Location(row + 2, column - 1), new Location(row - 1, column + 2),
                new Location(row + 2, column + 1), new Location(row + 1, column + 2),
                new Location(row - 2, column - 1), new Location(row - 1, column - 2),
                new Location(row - 2, column + 1), new Location(row + 1, column - 2)
        };
        return Stream.of(nextPossibleLocations).filter(location -> location.isValid(board)).toList();
    }
}
