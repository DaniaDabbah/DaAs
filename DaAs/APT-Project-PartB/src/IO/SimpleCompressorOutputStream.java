package IO;
import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream{

    private OutputStream out;


    public SimpleCompressorOutputStream(OutputStream o) {
        this.out = o;
    }


    @Override
    public void write(int b) throws IOException { }


    @Override
    public void write(byte[] b) throws IOException {
        int counter = 0;
        int currVal = 0;
        boolean flag = false;
        int i = 0;

        //write  data about the maze.
        for (i = 0; i < 12; i++){
            out.write(b[i]);
        }

        for (; i < b.length; i++){
            counter = 0;

            while (i < b.length && b[i] == currVal && counter < 255){
                counter++;
                i++;
                flag = true;
            }

            out.write((byte)counter);
            if (i >= b.length)
                break;

            currVal = b[i];
            if (flag )
                i--;
            else if(counter == 0)
                i--;

            flag = false;
        }
    }

}

