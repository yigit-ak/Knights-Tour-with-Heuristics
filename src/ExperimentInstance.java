import search_algorithms.SearchAlgorithm;

public class ExperimentInstance {
    final SearchAlgorithm searchAlgorithm;

    public ExperimentInstance(SearchAlgorithm searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }

    public void runSearch() {
        long startTime = System.currentTimeMillis();
        searchAlgorithm.search();
    }
}
