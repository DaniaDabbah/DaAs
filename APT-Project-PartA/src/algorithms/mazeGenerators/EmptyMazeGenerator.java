package algorithms.mazeGenerators;

import algorithms.mazeGenerators.AMazeGenerator;

public class EmptyMazeGenerator extends AMazeGenerator {
    /**
     * abstract method for generate Empty maze.
     * @param row: num of rows in the maze
     * @param column:num of columns in the maze
     * @return :empty maze
     */
    @Override
    public Maze generate(int row, int column) {
        Maze emptyMaze= new Maze(row, column);
        return emptyMaze;

    }
}
