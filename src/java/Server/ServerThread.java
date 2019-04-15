package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket = null;
    private GameProtocol gp;

    public ServerThread(Socket socket, GameProtocol gp) {
        super("ServerThread");
        this.socket = socket;
        this.gp = gp;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader in = new BufferedReader(isr);

            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {
                outputLine = gp.processInput(inputLine);
                System.out.println(outputLine);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
