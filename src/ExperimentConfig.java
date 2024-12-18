public class ExperimentConfig {
    private final int boardSize;
    // Method to be used in the experiment
    // Possible values:
    // 'a' - BreadthFirstSearch
    // 'b' - DepthFirstSearch
    // 'c' - DfsWithHeuristicH1B
    // 'd' - DfsWithHeuristicH2
    private final char method;
    private final int timeLimit;

    public ExperimentConfig(int boardSize, char method, int timeLimit) {
        this.boardSize = boardSize;
        this.method = method;
        this.timeLimit = timeLimit;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public char getMethod() {
        return method;
    }

    public int getTimeLimit() {
        return timeLimit;
    }
}
