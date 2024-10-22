package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Client partito");

        Socket server = new Socket("localhost", 3000);
        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        DataOutputStream out = new DataOutputStream(server.getOutputStream());
        Scanner myScan = new Scanner(System.in);

        System.out.println("Client collegato");

        String numeroInviato;
        String numeroRicevuto;
        int i = 0;

        do {
            System.out.println("Scrivi il numero da indovinare, oppuer 'esc' per uscire: ");
            numeroInviato = myScan.nextLine();
            out.writeBytes(numeroInviato + '\n');

            numeroRicevuto = in.readLine();

            switch (numeroRicevuto) {
                case "<":
                    System.out.println("numero troppo piccolo" + '\n');
                    i++;
                break;

                case ">":
                    System.out.println("numero troppo grande" + '\n');
                    i++;
                break;

                case "=":
                    System.out.println("numero indovinato in: " + i + " tentativi" + '\n');
                break;

                case "!":
                    System.out.println("numero più grande di 100" + '\n');
                break;
                    
                case "!!":
                    System.out.println("numero più piccolo di 0" + '\n');
                break;
            
                default:
                    System.out.println("choose not valid" + '\n');
                    break;
            }
             

        } while (!numeroRicevuto.equals("esc"));
        
        server.close();
        out.close();
        in.close();
        myScan.close();
        myScan.close();
    }
}