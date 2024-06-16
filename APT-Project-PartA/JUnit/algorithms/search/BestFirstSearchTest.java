package algorithms.search;

import algorithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    private   BestFirstSearch bestbfs = new BestFirstSearch();
    private    EmptyMazeGenerator emptymaze = new EmptyMazeGenerator();
    private MyMazeGenerator mymaze = new MyMazeGenerator();
    private SimpleMazeGenerator simplemaze = new SimpleMazeGenerator();

    @Test
    void getName() {
        assertEquals("Best First Search" , bestbfs.getName());
    }

    @Test
    void solve(){
// TESTS for the empty maze generator

        assertEquals(1,checkRoads(emptymaze,5,5));
        assertEquals(0,checkRoads(emptymaze,-5,5));
        assertEquals(0,checkRoads(emptymaze,5,-5));
        assertEquals(1,checkRoads(emptymaze,555,900));
        assertEquals(0,checkRoads(emptymaze,1,1));
        assertEquals(0,checkRoads(simplemaze,4,4));
        assertEquals(0,checkRoads(simplemaze,3,3));
        assertEquals(1,checkRoads(simplemaze,1,3));
        assertEquals(0,checkRoads(simplemaze,55,25));
        assertEquals(0,checkRoads(simplemaze,600,900));

    }
    // HELP FUNCTION for the tests that check the basics of all the kinds of the maze
    private int checkRoads(AMazeGenerator mazeGenerator, int row, int col){

        Maze checkmaze = mazeGenerator.generate(row,col);
        SearchableMaze searchableMaze = new SearchableMaze(checkmaze);
        Solution checksol = bestbfs.solve(searchableMaze);

        ArrayList<AState> sol = checksol.getSolutionPath();
        int j = 0;
        // to check if the first node in the solpath is the start state that the solve algo give
        if (sol.get(0) != searchableMaze.getStartState())
            return 0;
        // to check if the last node in the solpath is the goal(last) state that the solve algo' give
        if (sol.get(sol.size()-1) != searchableMaze.getGoalState())
            return 0;

        while(j < sol.size()){
            MazeState checkstate = (MazeState) sol.get(j);

            if (checkmaze.getMaze()[checkstate.getRowIndex()][checkstate.getColIndex()] == 1)
                return  0;
            j++;

        }
        return 1;
    }
}