package algorithms.search;

import java.util.ArrayList;
public interface ISearchable {
    /*the first func returns the last node in the path
    the second returns the first node
    and the last one returns the nodes that legal to move to them
     */
    AState getGoalState();

    AState getStartState();

    ArrayList<AState> getAllPossibleStates(AState state);
}
