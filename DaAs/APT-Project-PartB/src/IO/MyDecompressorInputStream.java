package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }


    @Override
    public int read() throws IOException {
        return 0;
    }

    @Override
    public int read(byte[] b) throws IOException {
        int ind = 0;
        String binaryCheck="";
        String binary = "";
        byte[] inbytes = in.readAllBytes();
        ArrayList<Byte> bytes = new ArrayList<>();

        // data members
        for (ind = 0; ind < 24; ind++) {
            b[ind] = inbytes[ind];
            bytes.add(b[ind]);
        }

        // Decompressing the maze itself
        byte temp = 0;

        for (; ind < inbytes.length; ind++) {
            binary = "";
            binary = Integer.toBinaryString(inbytes[ind]);
            binaryCheck = binary;
            int n = 8 - binary.length();
            if (binary.length() > 8){
                int len = binary.length()-8;
                binary = binary.substring(len);
                binaryCheck = binary;
            }
            if (binary.length() < 8){
                while (n > 0){
                    binary = "0" + binary;
                    n--;
                }
            }
            // && binary.length() + (bytes.size() - ind) * 8 < b.length
            for (int i = 0; i < binary.length(); i++) {
                temp = Byte.parseByte(binary.substring(i, i + 1));
                bytes.add(temp);


            }
        }

        if (b.length % 8 != 0) {
            for(int i = 0; i < 8 ; i++) {
                bytes.remove(bytes.size() - 1);
            }
            int num = b.length % 8;
            if (binaryCheck.length() < num) {
                while (num != 0) {
                    binaryCheck = "0" + binaryCheck;
                    num--;
                }
            }
            for (int i = 0; i<binaryCheck.length();i++){
                temp = Byte.parseByte(binaryCheck.substring(i,i+1));
                bytes.add(temp);
            }
        }
        for (int i = 0; i < b.length; i++){
            b[i] = bytes.get(i);
        }
        return 0;
    }
}