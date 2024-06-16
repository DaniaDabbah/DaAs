package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    private Maze maze;
    private MazeState startState;
    private MazeState goalState;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
        this.goalState = new MazeState(0,maze.getGoalPosition());
        this.startState = new MazeState(0,maze.getStartPosition());
    }

    @Override
    public AState getGoalState() {
        return this.goalState;
    }

    @Override
    public AState getStartState() {
        return this.startState;
    }

    // FUNC that check all the 8 possible nodes and if the node pass the check adds to list that the func returns
    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        if (state == null)
            return null;
        if (!(state instanceof MazeState))
            return null;
        ArrayList<AState> legalsStates = new ArrayList<>();
        MazeState mstate = (MazeState) state;

        Position up,down,left,right,upleft,upright,downleft,downright;
        up = new Position(mstate.getRowIndex()+1,mstate.getColIndex());
        down = new Position(mstate.getRowIndex()-1,mstate.getColIndex());
        left = new Position(mstate.getRowIndex(),mstate.getColIndex()-1);
        right = new Position(mstate.getRowIndex(),mstate.getColIndex()+1);

        upleft = new Position(mstate.getRowIndex()+1,mstate.getColIndex()-1);
        upright = new Position(mstate.getRowIndex()+1,mstate.getColIndex()+1);
        downleft = new Position(mstate.getRowIndex()-1,mstate.getColIndex()-1);
        downright = new Position(mstate.getRowIndex()-1,mstate.getColIndex()+1);

        if (maze.legal_Pos(up) == 1) {
            MazeState posstate = new  MazeState(10, up);
            posstate.setLastmove(state);
            legalsStates.add(posstate);
        }

        if (maze.legal_Pos(upright) == 1 && (maze.legal_Pos(up) == 1 || maze.legal_Pos(right) == 1)) {
            MazeState posstate = new MazeState(15, upright);
            posstate.setLastmove(state);
            legalsStates.add(posstate);
        }
        if (maze.legal_Pos(right) == 1){
            MazeState posstate = new  MazeState(10, right);
            posstate.setLastmove(state);
            legalsStates.add(posstate);
        }

        if (maze.legal_Pos(downright) == 1 && (maze.legal_Pos(down) == 1 || maze.legal_Pos(right) == 1)) {
            MazeState posstate = new MazeState(15, downright);
            posstate.setLastmove(state);
            legalsStates.add(posstate);
        }
        if (maze.legal_Pos(down) == 1){
            MazeState posstate = new  MazeState(10, down);
            posstate.setLastmove(state);
            legalsStates.add(posstate);
        }
        if (maze.legal_Pos(downleft) == 1 && (maze.legal_Pos(down) == 1 || maze.legal_Pos(left) == 1)) {
            MazeState posstate = new MazeState(15, downleft);
            posstate.setLastmove(state);
            legalsStates.add(posstate);
        }
        if (maze.legal_Pos(left) == 1){
            MazeState posstate = new  MazeState(10, left);
            posstate.setLastmove(state);
            legalsStates.add(posstate);
        }
        if (maze.legal_Pos(upleft) == 1 && (maze.legal_Pos(up) == 1 || maze.legal_Pos(left) == 1)) {
            MazeState posstate = new MazeState(15, upleft);
            posstate.setLastmove(state);
            legalsStates.add(posstate);
        }

        return legalsStates;
    }
}
