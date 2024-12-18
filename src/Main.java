import experiment.ExperimentInstance;
import problem_definition.Location;
import problem_definition.State;
import search_algorithms.*;

import java.lang.reflect.Constructor;

public class Main {
    public static void main(String[] args) throws Exception {

        final Location START_LOCATION = new Location(0, 0);
        int[] boardSizes = {8, 16, 32, 41, 52};
        Class<?>[] searchAlgorithmClasses = {
                //BreadthFirstSearch.class,
                //DepthFirstSearch.class
                DfsWithHeuristicH1B.class,
                DfsWithHeuristicH2.class
        };

        for (Class<?> searchAlgorithmClass : searchAlgorithmClasses) {
            for (int boardSize : boardSizes) {
                boolean[][] board = new boolean[boardSize][boardSize];
                State initialState = new State(board, START_LOCATION);

                Constructor<?> constructor = searchAlgorithmClass.getDeclaredConstructor(State.class);
                SearchAlgorithm searchAlgorithm = (SearchAlgorithm) constructor.newInstance(initialState);

                ExperimentInstance experimentInstance = new ExperimentInstance(searchAlgorithm);
                experimentInstance.runSearch();
            }
        }

    }
}
