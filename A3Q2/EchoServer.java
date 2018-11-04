/**************************************************************
*
* Group Members: Maxime Cadrin & Sebastian Savic
* Assignment 2
* EchoClient Connects to EchoServer. The program sends a string
* EchoServer reads the string adds a prefix string and returns
* the original string with the prefix.
*
*
***************************************************************/
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoServer {

    public static void main(String[] args) throws IOException{

        int count = 0;
        long[] id = new long[10];

        try(ServerSocket server = new ServerSocket(9999)){
            System.out.println("Waiting for connection");
            while(true){
                count++;
                Socket client = server.accept();//wait for a connection by the client using accept();
                Runnable connection = new MultiThread(client, count);
                Thread thread = new Thread(connection);
                thread.start();
                id[count-1] = thread.getId();
                if(Thread.activeCount() != count+1){
                    count = Thread.activeCount();
                    System.out.println(count+" is now connected");
                }
                System.out.println("Clients connected: "+count);
            }
        }
    }
}

class MultiThread implements Runnable{

    private Socket s;
    private long id;
    private int count;
    InputStream in;
    OutputStream out;
    private PrintWriter pw;

    MultiThread(Socket s, int count){
        this.s = s;
        this.count = count;
    }

    public long getId(){
        id = Thread.currentThread().getId();
        return id;
    }

    @Override
    public void run(){
        try{
            in = s.getInputStream();
            out = s.getOutputStream();
        }catch(IOException e){
            System.err.println(e);
        }
        try(Scanner scan = new Scanner(in)){
            pw = new PrintWriter(out, true);
            pw.println("Connected");
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                System.out.println("Client "+count+": "+line);
                pw.println("The Mountain answers: "+line);
            }
        }finally{
            System.out.println("Client "+this.count+" left the session.");
        }
    }
}
