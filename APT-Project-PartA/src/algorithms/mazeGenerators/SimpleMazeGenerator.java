package algorithms.mazeGenerators;

import algorithms.mazeGenerators.AMazeGenerator;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int row, int column) {
        if(row <2 && column<2 ){
            throw new RuntimeException("wrong parameters!");
        }
        else{

            Random random =new Random();
            Maze simpleMaze=new Maze(row,column);
            int[][] maze= simpleMaze.getMaze();
            for(int i=0;i<row;i++){
                for (int j=0;j<column;j++){
                    if(random.nextBoolean()){
                        maze[i][j]=0;
                        simpleMaze.setMaze(maze);

                    }
                    else {
                        maze[i][j]=1;
                        simpleMaze.setMaze(maze);
                    }
                }
            }

            // startPosition and GoalPosition must be 0.
            maze[0][0] = 0;
            maze[row-1][column-1] = 0;

            return simpleMaze;
        }
    }
}
