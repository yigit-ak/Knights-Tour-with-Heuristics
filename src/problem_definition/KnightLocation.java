package problem_definition;

public record KnightLocation(
        int row,
        int column) {

    public boolean isValid(int boardSize) {
        return row >= 0 && row < boardSize && column >= 0 && column < boardSize;
    }
}
