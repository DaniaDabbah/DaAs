package IO;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


public class MyCompressorOutputStream extends OutputStream {

    private OutputStream out;


    public MyCompressorOutputStream(OutputStream o) {
        this.out = o;
    }


    @Override
    public void write(int b) throws IOException { }


    @Override
    public void write(byte[] b) throws IOException {
        int ind=0;
        ArrayList<Byte> bytes = new ArrayList<>();

        // data members
        for (ind = 0; ind < 24 ; ind++){
            bytes.add(b[ind]);
        }
        int i = 0;
        byte temp = 0;
        for (; ind < b.length; ind += 8){
            temp = 0;
            if (ind + 8 > b.length)
                break;
            else{
                int tempindex = ind;
                for (i = 7;i>= 0;i--){
                    temp = (byte) (temp | b[tempindex] << i);
                    tempindex++;
                }
                bytes.add(temp);
            }
        }

        if (ind < b.length){
            temp = 0;
            int num = b.length - ind;
            while(num != 0){
                temp = (byte) (temp | b[ind] << num);
                ind++;
                num--;
            }
            bytes.add(temp);
        }
        // converting the arraylist to byte[]
        byte[] b1 = new byte[bytes.size()];
        for (int j = 0; j < bytes.size(); j++){
            b1[j] = bytes.get(j);
        }
        out.write(b1);
        }


}
