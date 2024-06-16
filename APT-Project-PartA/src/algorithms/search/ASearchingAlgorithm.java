package algorithms.search;

import java.util.Stack;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    private int countvisited;

    public ASearchingAlgorithm() {
        this.countvisited = 0;
    }

    public int getCountvisited() {
        return countvisited;
    }

    public void setCountvisited(int countvisited) {
        this.countvisited = countvisited;
    }
    public abstract  Solution solve(ISearchable domain);

    public int getNumberOfNodesEvaluated(){return this.countvisited;}

    public abstract  String getName();
    // build and returns the solution path
    public Solution solutionPath(ISearchable domain){
        AState curr = domain.getGoalState();
        Stack<AState> nodesOfPath = new Stack<>();
        while (curr != null){
            nodesOfPath.push(curr);
            curr = curr.getLastmove();
        }
        Solution thePath = new Solution();
        while (!nodesOfPath.isEmpty()){
            thePath.addState(nodesOfPath.pop());

        }
        return thePath;
    }
}
