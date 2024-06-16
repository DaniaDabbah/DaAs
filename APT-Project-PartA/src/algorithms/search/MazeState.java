package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    private Position position;

    public MazeState(int cost, Position position) {
        super(cost);
        this.position = position;
    }
    public MazeState(int cost, int row,int col) {
        super(cost);
        this.position = new Position(row,col);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getRowIndex(){
        if (this.position!=null)
            return this.position.getRowIndex();
        return -1;
    }
    public int getColIndex(){
        if (this.position!=null)
            return this.position.getColumnIndex();
        return -1;
    }
    @Override
    public String toString() {
        return position.toString();
    }

    // equal between two states if their in the same positions
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MazeState) {
            MazeState mstate = (MazeState) obj;
            return (mstate.getRowIndex() == this.getRowIndex() && mstate.getColIndex() == this.getColIndex());

        }
        return false;
    }

    public int compareTo(MazeState other) {
        // Compare MazeState instances based on their cost
        return Integer.compare(this.getCost(), other.getCost());
    }
}
