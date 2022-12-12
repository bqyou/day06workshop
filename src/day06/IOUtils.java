package day06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class IOUtils {

    public static void write(Socket sc, String payload) throws IOException {

        OutputStream os = sc.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        dos.writeUTF(payload);
        dos.flush();
        System.out.printf(">>>>>> %s\n", payload);

        // dos.close();
        // bos.close();
        // os.close();
    }

    public static String read(Socket sc) throws IOException {

        InputStream is = sc.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        String payload = dis.readUTF();
        System.out.printf("<<<<< %s\n", payload);

        // dis.close();
        // bis.close();
        // is.close();

        return payload;
    }

}
