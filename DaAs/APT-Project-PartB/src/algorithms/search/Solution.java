package algorithms.search;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.Serializable;
import java.util.List;
import java.util.Stack;

public class Solution implements Serializable {
    private ArrayList<AState> solutionMaze;

    public Solution() {
        this.solutionMaze = new ArrayList<>();

    }

    public void addState(AState state){
        if (state == null)
            return;
        solutionMaze.add(state);

    }
    public ArrayList<AState> getSolutionPath() {
            return solutionMaze;
        }

}
