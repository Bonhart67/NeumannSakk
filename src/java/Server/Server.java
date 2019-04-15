package Server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(4444)) {
            GameProtocol gp = new GameProtocol();

            ServerThread player = new ServerThread(serverSocket.accept(), gp);
            player.start();

        } catch (IOException e) {
            System.out.println("Could not listen on port: 4444");
            System.exit(-1);
        }
    }
}
