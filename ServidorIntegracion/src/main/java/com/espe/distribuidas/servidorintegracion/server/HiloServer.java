/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.servidorintegracion.server;


import com.espe.distribuidas.protocolocajero.pc.Mensaje;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class HiloServer extends Thread{
    
    private static Integer idGlobal = 0;
    
    private DataOutputStream output;
    private DataInputStream input;
    private Socket socket;
    
    public HiloServer(Socket socket){
        this.socket = socket;
    }
    
    @Override
    public void run(){
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            
            System.out.println("Conexion Establecida: " + this.idGlobal);
        } catch (IOException ex) {
            Logger.getLogger(HiloServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(true){
            try {
                System.out.println("Esperando datos...");
                String trama = this.input.readUTF();
                System.out.println("Datos recibidos...");
                System.out.println("Trama --> " + trama);
                
                if(trama.equals("FIN")){
                    break;
                }
                
                String idMensaje = trama.substring(39,49);
                System.out.println("idMensaje: " + idMensaje);
                
                switch(idMensaje){
                    case Mensaje.AUTENTIC_USER:
                        if(Mensaje.validaHash(trama)){
                            System.out.println("Operacion de autenticacion");
                        }
                    break;
                    
                    case Mensaje.CONSULTAR_CUENTA:
                        if(Mensaje.validaHash(trama)){
                            System.out.println("Operacion consultar cuentas");
                        }
                    break;
                        
                    
                    case Mensaje.DEPOSITO:
                        if(Mensaje.validaHash(trama)){
                            System.out.println("Operacion desposito");
                        }
                    break;
                    
                    case Mensaje.RETIRO:
                        if(Mensaje.validaHash(trama)){
                            System.out.println("Operacion retiro");
                        }
                    break;
                    
                    default:
                        System.out.println("Operacion no encontrada");
                    break;
                }
                
            } catch (Exception e) {
            }
        }
    }
    
}
