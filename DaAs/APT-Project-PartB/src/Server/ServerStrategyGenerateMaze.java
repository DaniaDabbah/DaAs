package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try (ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
             ObjectOutputStream toClient = new ObjectOutputStream(outToClient)) {

            // Read input from client
            Object data = fromClient.readObject();

            // Prepare to compress maze data
            ByteArrayOutputStream bytesToClient = new ByteArrayOutputStream();
            MyCompressorOutputStream compressor = new MyCompressorOutputStream(bytesToClient);

            // Generate maze based on input data
            IMazeGenerator mazeGenerator = Configurations.getGenerating();
            Maze mazeToClient;

            if (data instanceof int[]) {
                int[] dimensions = (int[]) data;
                if (dimensions.length == 2) {
                    mazeToClient = mazeGenerator.generate(dimensions[0], dimensions[1]);
                } else {
                    mazeToClient = mazeGenerator.generate(10, 10); // Default size
                }
            } else {
                mazeToClient = mazeGenerator.generate(10, 10); // Default size
            }

            // Convert maze to byte array and compress it
            byte[] byteMaze = mazeToClient.toByteArray();
            compressor.write(byteMaze);

            // Send compressed maze to client
            toClient.writeObject(bytesToClient.toByteArray());
            toClient.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
