package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    /**
     * abstract method for generate maze.
     * @param row: num of rows in the maze
     * @param column:num of columns in the maze
     * @return : maze
     */
    public abstract Maze generate(int row, int column);

    /**
     *method for measuring the time duration of generating the maze in milliseconds.
     * @param row:num of rows in the maze
     * @param column:num of columns in the maze
     * @return: the time it took to create the maze
     */
    @Override
    public long measureAlgorithmTimeMillis(int row, int column) {
        long start=System.currentTimeMillis();
        generate(row, column);
        long end=System.currentTimeMillis();
        return (end-start);
    }
}