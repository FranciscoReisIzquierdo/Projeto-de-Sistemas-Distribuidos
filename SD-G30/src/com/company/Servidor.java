package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Servidor {
    public static ServerSocket socket;
    private BaseDados dados;
    public static AtomicInteger threads= new AtomicInteger();

    public Servidor() throws IOException {
        socket= new ServerSocket(12345);
        this.dados= deSerialize();
    }



    public void run() throws InterruptedException {
        try {
            while (true) {
                Socket s = this.socket.accept();
                Servidor.threads.incrementAndGet();
                Thread worker = new Thread(new ServerWorker(s, this.dados));
                worker.start();
            }
        } catch (SocketException e) {
            System.out.println("Servidor a encerrar, não se aceitam mais conexões.\nA aguardar que clientes conectados concluam as suas operações.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(Servidor.threads.get()!= 0){
            TimeUnit.SECONDS.sleep(3);
        }
        serialize(this.dados);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Servidor s= new Servidor();
        s.run();
    }

    public static void serialize(BaseDados base){
        try {
            FileOutputStream fileOut = new FileOutputStream("baseDados.bd");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(base);
            objectOut.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static BaseDados deSerialize(){
        try {
            FileInputStream fileIn = new FileInputStream("baseDados.bd");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            objectIn.close();
            return (BaseDados) obj;
        }
        catch(Exception e){
            return new BaseDados();
        }
    }



}
