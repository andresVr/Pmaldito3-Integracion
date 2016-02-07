/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.servidorintegracion.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class Servidor {
    
    public static void main(String[] args) {
        
        try {
            System.out.println("Servidor de integración arriva...");
            ServerSocket server = new ServerSocket(4228);
            
            while(true){
                System.out.println("Esperando una conexion...");
                Socket cliente = server.accept();
                
                new HiloServer(cliente).start();
                System.out.println("Se ha recivido una conexion!!");
            }
        } catch (IOException e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
}
