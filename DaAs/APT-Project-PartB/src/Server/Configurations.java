package Server;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.*;
import java.io.*;
import java.util.Properties;


public class Configurations{

    private static OutputStream out = null;
    private static InputStream incon;
    private static Configurations con = null;
    public static Properties getProp(){return prop;}
    private static Properties prop = new Properties();


    private Configurations(){
        try {
            incon = new FileInputStream("./resources/config.properties");
            if(incon!=null) {
                prop.load(incon);
            }
            else {
                throw  new FileNotFoundException();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Configurations getconfig() {
        if(con==null){
            con=new Configurations();
        }
        return con;
    }


    public static int GetNumOfThreads(){
        return Integer.parseInt(prop.getProperty("threadPoolSize"));
    }
    public static ISearchingAlgorithm getSearchingAlg(){
        if(prop.getProperty("mazeSearchingAlgorithm").equals("DepthFirstSearch")){
            return new DepthFirstSearch();
        }
        else if (prop.getProperty("mazeSearchingAlgorithm").equals("BestFirstSearch")) {
            return new BestFirstSearch();
        }
        else {
            return new BreadthFirstSearch();
        }
    }


    public static IMazeGenerator getGenerating(){
        if(prop.getProperty("mazeGeneratingAlgorithm").equals("MyMazeGenerator"))
        {
            return new MyMazeGenerator();

        }
        else if (prop.getProperty("mazeGeneratingAlgorithm").equals("SimpleMazeGenerator")) {
            return new SimpleMazeGenerator();
        }
        else {
            return new EmptyMazeGenerator();
        }
    }

}
