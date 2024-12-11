package problem_definition;

import java.util.List;
import java.util.stream.Stream;

public record Location(
        int row,
        int column) {

    private boolean isValid(boolean[][] board) {
        return isWithinBoard(board.length) && isEmpty(board);
    }

    private boolean isWithinBoard(int boardSize) {
        return row >= 0 && row < boardSize && column >= 0 && column < boardSize;
    }

    private boolean isEmpty(boolean[][] board) {
        return !board[row][column];
    }

    public List<Location> getLocationsForNextMove(boolean[][] board) {
        return Stream.of(generateNextPossibleLocations())
                .filter(location -> location.isValid(board))
                .toList();
    }

    private Location[] generateNextPossibleLocations() {
        return new Location[] {
                new Location(row + 2, column - 1), new Location(row - 1, column + 2),
                new Location(row + 2, column + 1), new Location(row + 1, column + 2),
                new Location(row - 2, column - 1), new Location(row - 1, column - 2),
                new Location(row - 2, column + 1), new Location(row + 1, column - 2)
        };
    }
}
