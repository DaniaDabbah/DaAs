package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.ISearchable;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.util.HashMap;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    private static int numOfSolvedMazes = 0;
    private HashMap<byte[], String> mazeSolutions;

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        ISearchingAlgorithm searchingAlgorithm = Configurations.getSearchingAlg();
        mazeSolutions = new HashMap<>();

        try (ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
             ObjectOutputStream toClient = new ObjectOutputStream(outToClient)) {

            toClient.flush();

            Solution solution;
            Maze maze = (Maze) fromClient.readObject();

            String tempDirectoryPath = System.getProperty("java.io.tmpdir");

            byte[] keyMaze = maze.toByteArray();

            if (!mazeSolutions.containsKey(keyMaze)) {
                numOfSolvedMazes++;
                String solutionFileName = "maze_solution_" + numOfSolvedMazes + ".txt";
                mazeSolutions.put(keyMaze, solutionFileName);

                ISearchable searchable = new SearchableMaze(maze);
                solution = searchingAlgorithm.solve(searchable);

                openSolutionFile(tempDirectoryPath, solutionFileName, solution);
            } else {
                solution = readSolutionFromFile(tempDirectoryPath, mazeSolutions.get(keyMaze));
            }

            toClient.writeObject(solution);
            toClient.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void openSolutionFile(String tempDirectory, String solutionFile, Solution solution) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File(tempDirectory, solutionFile)))){
            outputStream.writeObject(solution);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Solution readSolutionFromFile(String tempDirectory, String solutionFile) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File(tempDirectory, solutionFile)))) {
            return (Solution) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
