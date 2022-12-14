package day06;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedServer {

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

            /*
             * Implement runnable interface
             * Start a threadpool - executor service
             * ExecutorService variable = Executors.newFixedThreadPool(n);
             * Schedule the thread
             * a) instantiate class that implements runnable
             * b) submit runnable to thread pool
             */

            ExecutorService thrPool = Executors.newFixedThreadPool(2);

            while (true) {
                System.out.println("Waiting for connection...\n");
                Socket sc = server.accept(); // accepting the connection to socket

                HandleClient client = new HandleClient(sc);
                // submit runnable to thr pool
                thrPool.submit(client);

            }
        } else {
            System.out.println("Invalid command entered. Please enter a port number after listserver");
        }

    }
}