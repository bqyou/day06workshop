package day06;

import java.net.Socket;

public class ListClient {

    public static void main(String[] args) throws Exception {

        Integer num = Integer.parseInt(args[0]);
        Integer limit = Integer.parseInt(args[1]);
        Integer port = Integer.parseInt(args[3]);
        if (args[2].trim().toLowerCase().equals("localhost")) {

            // connect socket to ip and port
            Socket sc = new Socket(args[2].trim().toLowerCase(), port);
            System.out.printf("Connected to %s %d\n", args[2], port);

            IOUtils.write(sc, "%d %d".formatted(num, limit));

            String response = IOUtils.read(sc);

            System.out.println(response);

            sc.close();
        } else {
            System.out.println("Invalid input");
        }
    }

}
