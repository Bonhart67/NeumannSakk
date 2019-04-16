package Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import static java.lang.System.out;

public class MsgClient
{
    String uname;
    PrintWriter pw;
    BufferedReader br;
    Socket client;

    public MsgClient(String uname,String servername) throws Exception {
        this.uname = uname;
        client  = new Socket(servername,8888);
        br = new BufferedReader( new InputStreamReader( client.getInputStream()) ) ;
        pw = new PrintWriter(client.getOutputStream(),true);
        pw.println(uname);  // elküldi a felhasználónevet a szervernek
        new MessagesThread().start();  // thread a beérkező üzenetekhez
        new SentMessagesThread().start(); // thread az elküldött üzenetekhez
    }

    public static void main(String ... args) {

        Scanner scn = new Scanner(System.in);
        out.println("Milyen nevet szeretnél?");
        String name = scn.nextLine();
        String servername = "localhost";

        try {
            new MsgClient( name ,servername);
        } catch(Exception ex) {
            out.println( "Hiba --> " + ex.getMessage());
        }

    }

    // belső osztálya az elküldött üzeneteknek
    class SentMessagesThread extends Thread {
        public void run() {
            String message;
            try {
                Scanner scn = new Scanner(System.in);
                while(true) {
                    message = scn.nextLine();
                    pw.println(message);
                }
            } catch (Exception ex) {}

        }
    }

    // belső osztálya a beérkező üzeneteknek
    class MessagesThread extends Thread {
        public void run() {
            String line;
            try {
                while(true) {
                    line = br.readLine();
                    out.println(line + "\n");
                } // end of while
            } catch(Exception ex) {}
        }
    }
}
