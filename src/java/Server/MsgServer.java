package Server;

import java.io.*;
import java.net.*;
import java.util.*;

import static java.lang.System.out;

public class MsgServer {
    Vector<String> users = new Vector<String>();
    Vector<HandleClient> clients = new Vector<HandleClient>();

    public void process() throws Exception {
        ServerSocket server = new ServerSocket(8888, 10);
        out.println("Szerver elindult!");
        while (true) {
            Socket client = server.accept();
            HandleClient c = new HandleClient(client);
            clients.add(c);
            out.println("Kliens csatlakozott! Név: " + c.name);
        }
    }

    public static void main(String... args) throws Exception {
        new MsgServer().process();
    }

    public void broadcast(String user, String message) {
        // elküldi az üzenetet mindenkinek, aki nem a küldő a neve
        out.println("Üzenet jött " + user + " -tól/-től: " + message);
        for (HandleClient c : clients)
            if (!c.getUserName().equals(user))
                c.sendMessage(user, message);
    }

    class HandleClient extends Thread {
        String name = "";
        BufferedReader input;
        PrintWriter output;

        public HandleClient(Socket client) throws Exception {
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new PrintWriter(client.getOutputStream(), true);
            name = input.readLine();
            users.add(name);
            start();
        }

        public void sendMessage(String uname, String msg) {
            output.println(uname + ":" + msg);
        }

        public String getUserName() {
            return name;
        }

        public void run() {
            String line;
            try {
                while (true) {
                    line = input.readLine();
                    if (line.equals("end")) {
                        clients.remove(this);
                        users.remove(name);
                        break;
                    }
                    broadcast(name, line);
                }
            }
            catch (Exception ex) {
                out.println(ex.getMessage());
            }
        }
    }
}
