package algorithms.search;

public interface ISearchingAlgorithm {
    /* this func get kind of search and returns solution path by 2 data structures one of them
     is map that we use for know the visited nodes and the other for the body of the algorithm
     dfs we use stack and for the bfs we use queue
     */
    Solution solve(ISearchable domain);
    // returns the number of the nodes that visited in the path
    int getNumberOfNodesEvaluated();

    String getName();
}
