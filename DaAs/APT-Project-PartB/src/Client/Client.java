package Client;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;



public class Client {

    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy strategy;


    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }


    public void communicateWithServer(){
        try{
            Socket server= new Socket(this.serverIP,this.serverPort);
            System.out.println("connected to server - IP = " + serverIP + ", Port = " + serverPort);
            this.strategy.clientStrategy(server.getInputStream(),server.getOutputStream());
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
