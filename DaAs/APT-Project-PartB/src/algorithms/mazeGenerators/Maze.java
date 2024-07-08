package algorithms.mazeGenerators;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Maze implements Serializable {

    private int rows;
    private int columns;
    private Position startPosition;
    private Position goalPosition;
    private int[][] maze;

    public Maze(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.startPosition = new Position(0, 0);
        this.goalPosition = new Position(rows - 1, columns - 1);
        this.maze = new int[rows][columns];
        initializeMaze();
    }


    public Maze(byte[] ByteArray){
        byte[] value = new byte[4];
        // calculating number of the rows
        value[0] = ByteArray[0];
        value[1] = ByteArray[1];
        value[2] = ByteArray[2];
        value[3] = ByteArray[3];
        int numOfRows = ByteBuffer.wrap(value).getInt();

        // calculating number of columns
        value[0] = ByteArray[4];
        value[1] = ByteArray[5];
        value[2] = ByteArray[6];
        value[3] = ByteArray[7];
        int numOfCols = ByteBuffer.wrap(value).getInt();

        // calculating Start Position row
        value[0] = ByteArray[8];
        value[1] = ByteArray[9];
        value[2] = ByteArray[10];
        value[3] = ByteArray[11];
        int startR = ByteBuffer.wrap(value).getInt();

        //calculating Start position column
        value[0] = ByteArray[12];
        value[1] = ByteArray[13];
        value[2] = ByteArray[14];
        value[3] = ByteArray[15];
        int startC = ByteBuffer.wrap(value).getInt();

        // calculating goal position row
        value[0] = ByteArray[16];
        value[1] = ByteArray[17];
        value[2] = ByteArray[18];
        value[3] = ByteArray[19];
        int goalR = ByteBuffer.wrap(value).getInt();

        //calculating goal position column
        value[0] = ByteArray[20];
        value[1] = ByteArray[21];
        value[2] = ByteArray[22];
        value[3] = ByteArray[23];
        int goalC = ByteBuffer.wrap(value).getInt();

        this.rows = numOfRows;
        this.columns = numOfCols;
        this.startPosition = new Position(startR,startC);
        this.goalPosition = new Position(goalR,goalC);
        this.maze = new int[numOfRows][numOfCols];

        int index = 24;
        for (int r = 0; r< numOfRows;r++){
            for (int c = 0; c< numOfCols;c++){
                this.maze[r][c] = ByteArray[index];
                index++;
            }
        }

    }

    // Initialize maze with walls (assuming 1 represents wall)
    private void initializeMaze() {
        for (int i = 0; i < rows; i++) {
            Arrays.fill(maze[i], 1); // fill each row with walls initially
        }
        maze[startPosition.getRowIndex()][startPosition.getColumnIndex()] = 0; // start position
        maze[goalPosition.getRowIndex()][goalPosition.getColumnIndex()] = 0; // goal position
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Position startPosition) {
            this.startPosition = startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }

    public void setGoalPosition(Position goalPosition) {
            this.goalPosition = goalPosition;

    }

    public int getRowIndex() {
        return rows;
    }

    public int getColumnIndex() {
        return columns;
    }

    public int[][] getMaze() {
        return maze;
    }

    public int getCellValue(int row, int col) {
        if (isValidCell(row, col)) {
            return maze[row][col];
        }
        return -1; // or throw an exception, depending on desired behavior
    }

    public void setCellValue(int row, int col, int value) {
        if (isValidCell(row, col)) {
            maze[row][col] = value;
        } else {
            System.out.println("Invalid cell coordinates.");
        }
    }

    public byte[] toByteArray(){
        int size = 24 + (this.rows * this.columns);
        byte[] byteArray = new byte[size];

        int rows = getRowIndex();
        int cols = getColumnIndex();
        int startR = getStartPosition().getRowIndex();
        int startC = getStartPosition().getColumnIndex();
        int goalR = getGoalPosition().getRowIndex();
        int goalC = getGoalPosition().getColumnIndex();

        byte[] byteR = ByteBuffer.allocate(4).putInt(rows).array();
        byte[] byteC = ByteBuffer.allocate(4).putInt(cols).array();
        byte[] byteStartR = ByteBuffer.allocate(4).putInt(startR).array();
        byte[] byteStartC = ByteBuffer.allocate(4).putInt(startC).array();
        byte[] byteGoalR = ByteBuffer.allocate(4).putInt(goalR).array();
        byte[] byteGoalC = ByteBuffer.allocate(4).putInt(goalC).array();

        putData24(byteArray,byteR,0);
        putData24(byteArray,byteC,4);
        putData24(byteArray,byteStartR,8);
        putData24(byteArray,byteStartC,12);
        putData24(byteArray,byteGoalR,16);
        putData24(byteArray,byteGoalC,20);

        int i = 24;
        for (int r = 0; r< rows;r++){
            for (int c = 0; c< cols;c++){
                byteArray[i] = (byte) this.maze[r][c] ;
                i++;
            }
        }
        return byteArray;
    }

    public void putData24(byte[] mainArray, byte[] putinto, int index){
        for(int i = 0; i< 4;i++){
            mainArray[index] = putinto[i];
            index++;
        }

    }


    public boolean isValidPosition(Position position) {
        return position != null && position.getRowIndex() >= 0 && position.getRowIndex() < rows &&
                position.getColumnIndex() >= 0 && position.getColumnIndex() < columns &&
                maze[position.getRowIndex()][position.getColumnIndex()] == 0; // check if it's not a wall
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < columns;
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == startPosition.getRowIndex() && j == startPosition.getColumnIndex())
                    System.out.print(" \033[0;31m" + "S" + "\033[0m");
                else if (i == goalPosition.getRowIndex() && j == goalPosition.getColumnIndex())
                    System.out.print(" \033[0;32m" + "E" + "\033[0m");
                else
                    System.out.print(" " + maze[i][j]);
                if (j == columns - 1)
                    System.out.println();
            }
        }
    }

    public void setMaze(int[][] maze) {
        if (maze.length == rows && maze[0].length == columns) {
            this.maze = maze;
        } else {
            System.out.println("Invalid maze dimensions.");
        }
    }

    public int legal_Pos(Position position){
        if ((position.getRowIndex()<0 || position.getRowIndex()>= this.getRowIndex() || position.getColumnIndex() < 0 || position.getColumnIndex()>= this.getColumnIndex())|| this.getMaze()[position.getRowIndex()][position.getColumnIndex()] == 1)
            return 0;
        return 1;
    }
}
