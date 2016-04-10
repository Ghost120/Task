/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testserversocet;

import java.io.*;
import java.net.*;
import java.util.ArrayList;


/**
 *
 * @author Kasyanenko Konstantin
 */
public class TestServerSocet {

    public static void main(String argv[]) throws Exception {
        int m=34;
        ArrayList<String> al= new ArrayList<>();
        al.add("60:21:C0:2A:E0:33;start;+23;45;-16777216");
        al.add("60:21:C0:2A:E0:33;move;45;56;-16777216");
        al.add("60:21:C0:2A:E0:33;move;78;98;-16777216");
        al.add("60:21:C0:2A:E0:33;move;123;156;-16777216");
        al.add("60:21:C0:2A:E0:33;move;140;170;-16777216");
        al.add("60:21:C0:2A:E0:33;move;180;170;-16777216");
        al.add("60:21:C0:2A:E0:33;move;345;170;-16777216");
        al.add("60:21:C0:2A:E0:33;move;400;170;-16777216");
        al.add("60:21:C0:2A:E0:33;move;500;450;-16777216");
        al.add("60:21:C0:2A:E0:33;move;600;400;-16777216");
        
        al.add("60:21:C0:2A:E0:33;start;28;55;-16777216");
        al.add("60:21:C0:2A:E0:33;move;45;67;-16777216");
        al.add("60:21:C0:2A:E0:33;move;89;90;-16777216");
        al.add("60:21:C0:2A:E0:33;move;130;150;-16777216");
        al.add("60:21:C0:2A:E0:33;move;200;456;-16777216");
        al.add("60:21:C0:2A:E0:33;move;300;345;-16777216");
        al.add("60:21:C0:2A:E0:33;move;444;300;-16777216");
        al.add("60:21:C0:2A:E0:33;move;130;555;-16777216");
        al.add("60:21:C0:2A:E0:33;move;140;170;-16777216");
        al.add("60:21:C0:2A:E0:33;move;180;170;-16777216");
        al.add("60:21:C0:2A:E0:33;move;345;170;-16777216");
        al.add("60:21:C0:2A:E0:33;move;400;170;-16777216");
        al.add("60:21:C0:2A:E0:33;move;500;450;-16777216");
        al.add("60:21:C0:2A:E0:33;move;600;400;-16777216");
        
        ServerSocket welcomeSocket = new ServerSocket(29288);
        System.out.println("Server start");
        
        while (true) {
            
            Socket connectionSocket = welcomeSocket.accept();
            DataOutputStream outToClient= new DataOutputStream(connectionSocket.getOutputStream());
            
            for(String s: al){
                outToClient.writeBytes(s+"\n");  
                Thread.sleep(500);
                
            }
            
            
        }

    }

 
}
