package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
    private Thread thread;
    private ExecutorService executorthread;
    private static Configurations config= Configurations.getconfig();


    public Server(int port, int listeningInterval, IServerStrategy serverStrategyGenerateMaze) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategyGenerateMaze;
        this.executorthread = Executors.newFixedThreadPool(Configurations.GetNumOfThreads());
    }


    public void start() {
        thread = new Thread(this::runServer);
        thread.start();
    }


    public void stop() {
        stop = true;
    }


    public void handleC(Socket ClientS){
        try {
            serverStrategy.applyStrategy(ClientS.getInputStream(),ClientS.getOutputStream());
            ClientS.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningInterval);
            while (!stop){
                try {
                    Socket socket = serverSocket.accept();
                    executorthread.execute(() -> {
                        handleC(socket);
                    });
                } catch (IOException e) {
                    // we need to ignore the exception massage , we will be handle the next time
                }
            }
            executorthread.shutdown();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
