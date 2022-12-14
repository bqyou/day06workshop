package day06;

import java.io.IOException;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class HandleClient implements Runnable {

    private Socket sc;

    public HandleClient(Socket sc) {
        this.sc = sc;
    }

    @Override // runnable
    public void run() {
        System.out.printf("New connection on port %d\n", sc.getLocalPort());

        // new input stream
        try {
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
            Thread.sleep(2000);
            IOUtils.write(sc, response);
        } catch (IOException | InterruptedException e1) {
            e1.printStackTrace();
        } finally {
            try {
                sc.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
