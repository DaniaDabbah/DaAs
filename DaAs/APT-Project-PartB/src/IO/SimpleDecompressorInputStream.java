package IO;
import java.io.IOException;
import java.io.InputStream;



public class SimpleDecompressorInputStream extends InputStream {

    private InputStream in;


    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }


    @Override
    public int read() throws IOException {
        return 0;
    }



    @Override
    public int read(byte[] b) throws IOException {
        byte[] inputData = in.readAllBytes();
        int counter;
        int index = 12;
        byte currVal = 0;

        for (int i = 0; i < 12; i++)
            b[i] = inputData[i];



        for (int i = 12; i < inputData.length; i++) {
            counter = inputData[i];

            if (i % 2 == 0)
                currVal = 0;
            else
                currVal = 1;


            while (counter != 0) {
                b[index++] = currVal;
                counter--;
            }
        }

        return 0;
    }
}
