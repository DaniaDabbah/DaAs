package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {

    public DepthFirstSearch() {
        super();
    }

    @Override
    public Solution solve(ISearchable domain) {
        if (domain == null)
            return null;
        AState start = domain.getStartState();
        AState end = domain.getGoalState();
        if (start == null || end == null)
            return null;
        HashMap<String, AState> map = new HashMap<>();
        Stack<AState> openlist = new Stack<>();
        start.setVisited(true);
        openlist.push(start);
        map.put(start.toString(), start);
        AState currentNode = null;
        while (!openlist.isEmpty()) {
            setCountvisited(getCountvisited() + 1);
            currentNode = openlist.pop();
            map.put(currentNode.toString(), currentNode);
            if (currentNode.equals(end)) {
                end.setLastmove(currentNode.getLastmove());
                break;
            }
            ArrayList<AState> neigbors = domain.getAllPossibleStates(currentNode);
            for (AState state : neigbors){
                    /*if the list contains that state dont add
                   there 2 conditions that help DFS algorithm

                     */
                if (!openlist.contains(state) && !map.containsKey(state.toString())) {
                    openlist.push(state);
                    state.setLastmove(currentNode);
                }

            }
        }
        return solutionPath(domain);

    }

    @Override
    public String getName() {
        return "Depth First Search";
    }
}
