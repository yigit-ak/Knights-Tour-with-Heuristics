import search_algorithms.SearchAlgorithm;

public class SearchAlgorithmTest<T extends SearchAlgorithm> {
    public static final int TIMEOUT = 15; // in minutes

    private final int boardSize; // n*n square board
    private final T searchAlgorithm;

    public SearchAlgorithmTest(int boardSize) {
        this.boardSize = boardSize;
        this.searchAlgorithm = (T) new SearchAlgorithm(boardSize);
    }

    public void test() {
        // TODO
    }


    public void printTestResults() {
        // TODO
    }
}
