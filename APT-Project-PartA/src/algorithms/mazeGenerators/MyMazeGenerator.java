package algorithms.mazeGenerators;


//public class MyMazeGenerator {

import java.util.ArrayList;
import java.util.Random;

/**
 * this class responsible for generating a maze based on Randomized Prim's Algorithm.
 */
public class MyMazeGenerator extends AMazeGenerator{
    /**
     * Start with a grid full of walls.
     * @param maze
     * @return full of maze.
     */
    private Maze fullMaze(Maze maze){
        int [][]m=maze.getMaze();
        for(int i=0;i<maze.getRowIndex();i++){
            for (int j=0; j<maze.getColumnIndex();j++){
                m[i][j]=1;
            }
        }
        return maze;

    }

    /**
     *  Add the walls of the cell to the wall list.
     * @param maze:2D array.
     *  @param row:the row index
     * @param column:the column index
     */
    private void addWalls(int[][] maze, ArrayList<Position> walls,int row, int column){
        //left
        if(column-1>=0){
            if (maze[row][column-1]==1){
                walls.add(new Position(row, column-1));
            }
        }
        //right
        if(column+1<maze[row].length){
            if (maze[row][column+1]==1){
                walls.add(new Position(row, column+1));
            }
        }
        //up
        if(row-1>=0){
            if (maze[row-1][column]==1){
                walls.add(new Position(row-1, column));
            }
        }
        //down
        if(row+1<maze[column].length){
            if (maze[row+1][column]==1){
                walls.add(new Position(row+1, column));
            }
        }
    }
    private int neighboringWalls(int[][] maze,int row,int column){
        ArrayList<Position> neighbors=new ArrayList<>();
        //left
        if(column-1>=0){
            if (maze[row][column-1]==0){
                neighbors.add(new Position(row, column-1));
            }
        }
        //right
        if(column+1<maze[row].length){
            if (maze[row][column+1]==0){
                neighbors.add(new Position(row, column+1));
            }
        }
        //up
        if(row-1>=0){
            if (maze[row-1][column]==0){
                neighbors.add(new Position(row-1, column));
            }
        }
        //down
        if(row+1<maze[column].length){
            if (maze[row+1][column]==0){
                neighbors.add(new Position(row+1, column));
            }
        }
        return neighbors.size();

    }
    @Override
    public Maze generate(int row, int column) {

            //initialize
            Maze maze=fullMaze(new Maze(row,column));
            int[][] m= maze.getMaze();
            Random random=new Random();
            Position start= new Position(0,random.nextInt(column));
            Position goal= new Position(row-1, random.nextInt(column));
            ArrayList<Position> wallsList=new ArrayList<>();
            //Pick a cell, mark it as part of the maze. Add the walls of the cell to the wall list.
            m[start.getRowIndex()][start.getColumnIndex()]=0;
            maze.setStartPosition(start.getRowIndex(),start.getColumnIndex());
            this.addWalls(m,wallsList,start.getRowIndex(),start.getColumnIndex());
            //While there are walls in the list:
            while (!wallsList.isEmpty()){
                //Pick a random wall from the list. If only one of the cells that the wall divides is visited:
                Position randomWall=wallsList.get(random.nextInt(wallsList.size()));
                //If only one of the cells that the wall divides is visited
                int neighborNum=neighboringWalls(m,randomWall.getRowIndex(),randomWall.getColumnIndex());
                if(neighborNum==1){
                    //then:
                    //1.Make the wall a passage and mark the unvisited cell as part of the maze.
                    m[randomWall.getRowIndex()][randomWall.getColumnIndex()]=0;
                    //2.Add the neighboring walls of the cell to the wall list.
                    this.addWalls(m,wallsList,randomWall.getRowIndex(),randomWall.getColumnIndex());
                }
                //Remove the wall from the list.
                wallsList.remove(randomWall);
            }
            maze.setGoalPosition(goal.getRowIndex(),goal.getColumnIndex());
            m[goal.getRowIndex()][goal.getColumnIndex()]=0;


            return maze;



    }
}
