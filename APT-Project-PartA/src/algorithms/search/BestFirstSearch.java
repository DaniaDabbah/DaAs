package algorithms.search;

import algorithms.search.AState;
import algorithms.search.BreadthFirstSearch;

import java.util.PriorityQueue;
import java.util.Comparator;


public class BestFirstSearch extends BreadthFirstSearch {
    // we overrided the queue in the BFS
    public BestFirstSearch() {
        super(new PriorityQueue<AState>(comp));
    }
    private static Comparator<AState> comp = new Comparator<AState>() {
        @Override
        public int compare(AState o1, AState o2) {
            if (o1.getCost() > o2.getCost())
                return 1;
            else if(o1.getCost() < o2.getCost())
                return -1;
            else
                return 0;

        }
    };
    @Override
    public String getName(){
        return "Best First Search";
    }
}
