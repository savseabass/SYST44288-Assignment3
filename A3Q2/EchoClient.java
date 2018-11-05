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

import java.io.*;
import java.net.*;

public class EchoClient {

    public static void main(String[] args) {

        try{
            Socket echoClient = new Socket("127.0.0.1", 9999); //create new socket using loopback address and unused port
            BufferedReader in = new BufferedReader(new InputStreamReader(echoClient.getInputStream())); //create read buffer that reads from socket
            PrintWriter out = new PrintWriter(echoClient.getOutputStream(), true); //create write buffer that writes to socket
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));//create read buffer for user input
            String input;
            do{
                input = in.readLine();//take input from socket
                if(input != null)//print the input from socket if it isnt null
                    System.out.println(input);
		input = stdIn.readLine(); //store input from user into "input"
                out.println(input); //write the input from user to the socket 
            }while( !input.trim().equals("."));
        } catch (Exception e) {
            System.out.println("Host Unknown: " + "127.0.0.1");
        }
    }
}
