package algorithms.mazeGenerators;

/**
 * Maze class, represents the Maze object.
 */
public class Maze {
    private int row;
    private int column;
    private Position startPosition;
    private Position goalPosition;
    private int[][] maze;

    public Maze(int row, int column) {
        if(row>1 && column>1) {
            this.row = row;
            this.column = column;

            this.maze = new int[row][column];
            this.maze[0][0] = this.maze[row - 1][column - 1] = 0;
        }
        else{
            this.row = 3;
            this.column = 3;
            this.maze = new int[3][3];

        }
        this.startPosition = new Position(0, 0);
        this.goalPosition = new Position(row - 1, column - 1);
    }

    /**
     * getter for the start position in the maze.
     * @return the start position
     */
    public Position getStartPosition() {
        return startPosition;
    }
    /**
     * getter for the goal position in the maze.
     * @return the goal position
     */
    public Position getGoalPosition() {
        return goalPosition;
    }
    /**
     * getter for the row index of the position
     * @return the row index
     */
    public int getRowIndex() {
        return row;
    }
    /**
     * getter for the column index of the position
     * @return the column index
     */
    public int getColumnIndex() {
        return column;
    }

    /**
     * getter for 2D array of the maze.
     * @return 2D array of the maze
     */
    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public void setStartPosition(int row, int column) {
        this.startPosition = new Position(row,column);
    }

    public void setGoalPosition(int row, int column) {
        this.goalPosition =new Position(row,column);;
    }

    /**
     * This method is responsible for printing the maze to the screen.
     * '0' represents a path, '1' represents a wall, 'S' and 'E' represents start and goal position respectively.
     */
    public void print(){
        for(int i=0;i<row;i++){
            System.out.println(" { ");
            for( int j=0;j<column;j++){
                //start position S
                if(this.startPosition.getRowIndex()==i&& this.startPosition.getColumnIndex()==j){
                    System.out.println("S");
                }
                //goal position E
                else if (this.goalPosition.getRowIndex()==i&& this.goalPosition.getColumnIndex()==j) {
                    System.out.println("E");
                }
                else {
                    System.out.println(maze[i][j]+ " ");
                }
            }
            System.out.println(" } ");

        }

    }
    public int legal_Pos(Position position){
        if ((position.getRowIndex()<0 || position.getRowIndex()>= this.getRowIndex() || position.getColumnIndex() < 0 || position.getColumnIndex()>= this.getColumnIndex())|| this.maze[position.getRowIndex()][position.getColumnIndex()] == 1)
            return 0;
        return 1;
    }
}
