package algorithms.search;
import java.util.*;
public class BreadthFirstSearch extends ASearchingAlgorithm{
    protected Queue<AState> openlist;


    public BreadthFirstSearch() {
        super();
        this.openlist = new LinkedList<>();

    }

    public BreadthFirstSearch(Queue<AState> openlist) {
        super();
        this.openlist = openlist;
    }

    @Override
    public Solution solve(ISearchable domain) {
        // in the tests we notice  that with out this clear the fail
        openlist.clear();
        // standard check
        if (domain == null)
            return null;
        // use fuctions that we do to know the start and end nodes
        AState start = domain.getStartState();
        AState end = domain.getGoalState();
        if (start == null || end == null)
            return null;
        HashMap<String,AState> map = new HashMap<>();
        // the open list in BFS is queue that contains the nodes we will probably move to them
        openlist.add(start);
        AState currentNode = null;
        while (!openlist.isEmpty()){
            // counter for the nodes in the path
            setCountvisited(getCountvisited()+1);
            currentNode = openlist.poll();
            // i add to the map the nodes that we  visited and we remove from the queue
            map.put(currentNode.toString(),currentNode);
            if(currentNode.equals(end)){
                end.setLastmove(currentNode.getLastmove());
                break;
            }
            //an array that contain all nodes that we can use in the build of solution path
            ArrayList<AState> neighbors = domain.getAllPossibleStates(currentNode);
            for (AState state : neighbors){
                /* in BFS we have one condition cause not like DFS , in BFS their a difference between the diagonal move and the regular move (up/down/ right/ left)

                 */
                if (!map.containsKey(state.toString())) {
                    openlist.add(state);
                    state.setLastmove(currentNode);
                }

            }
        }
        return solutionPath(domain);
    }

    @Override
    public String getName() {
        return "Breadth First Search";
    }
}