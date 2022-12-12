package day06;

import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ListServer {

    public static void main(String[] args) throws Exception {

        if (args.length == 1) {
            try {
                Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Port number entered not valid\n");
            }
            System.out.printf("Listening on port %d\n", Integer.parseInt(args[0]));

            // new server socket with port
            ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));

            while (true) {
                System.out.println("Waiting for connection...\n");
                Socket sc = server.accept(); // accepting the connection to socket

                System.out.printf("New connection on port %d\n", sc.getLocalPort());

                // new input stream
                String payload = IOUtils.read(sc);
                String[] values = payload.split(" ");
                Integer num = Integer.parseInt(values[0]);
                Integer limit = Integer.parseInt(values[1]);
                List<Integer> randNums = new LinkedList<Integer>();

                // generate random integers
                Random rand = new SecureRandom();
                for (Integer i = 0; i < num; i++) {
                    randNums.add(rand.nextInt(limit));
                }

                String response = randNums.stream()
                        .map(v -> v.toString())
                        .collect(Collectors.joining(":"));

                IOUtils.write(sc, response);

                sc.close();
            }
        } else {
            System.out.println("Invalid command entered. Please enter a port number after listserver");
        }

    }
}