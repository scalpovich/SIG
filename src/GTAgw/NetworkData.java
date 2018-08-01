/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

/**
 *
 * @author fjuniadi
 */
public class NetworkData { 
    NetworkDataListener NDL;
    Socket client;
    boolean koneksi;
    private TerimaData terimadata;
    private KirimData kirimdata;
    NetworkData(String host,int port,NetworkDataListener NDL){
    try {
        this.NDL=NDL;
        client=new Socket(host,port); 
        DataInputStream in = new DataInputStream(client.getInputStream()); 
        DataOutputStream out = new DataOutputStream(client.getOutputStream()); 
        terimadata = new TerimaData(in);
        kirimdata = new KirimData(out);
        koneksi = true;
        } catch (UnknownHostException e) {
        e.printStackTrace(); } catch (IOException e) {
            e.printStackTrace();
        }
}
    
public class TerimaData implements Runnable{ Thread loop;
private DataInputStream in;
public TerimaData(DataInputStream in)
{
    this.in = in;
    loop = new Thread(this);
           loop.start();
}
    public void run() {
    Thread thisThread = Thread.currentThread(); 
    while(loop==thisThread)
        {
        try
            {
                       String data = in.readUTF();
                       NDL.menerimaData(data);
            }
        catch(IOException e) {
                       disconnect();
                  }
        }
    }
    public void destroy()
    {
        loop = null;
    }

}

public class KirimData implements Runnable{ Thread loop;
LinkedList tampungData; 
DataOutputStream out;
public KirimData(DataOutputStream out){
    this.out = out;
    tampungData = new LinkedList(); 
    loop = new Thread(this); 
    loop.start();
}
public void tambahData(String data){
    synchronized(tampungData){
        tampungData.add(data);
        tampungData.notify();
    }
}
    public void run(){
        String data;
        Thread thisThread = Thread.currentThread(); 
        while(loop==thisThread){
            synchronized(tampungData){
            if(tampungData.isEmpty() && loop!=null){
                try{
                    tampungData.wait(); }
                catch(InterruptedException e) { }
                }
            } 
            while(tampungData.size()>0){
                synchronized(tampungData){
                data =
                (String)tampungData.removeFirst();
                }
                try{ 
                    out.writeUTF(data);
                
                }
                catch(IOException e){
                disconnect();
                }
            } 
        }
    }
    public void destroy()
    {
        loop = null;
        synchronized(tampungData) {
        tampungData.notify();
        }
    }

}
public synchronized void disconnect(){ 
    if(koneksi){
        NDL.networkDisconnected("disconnected..");
        koneksi = false; kirimdata.destroy();
        terimadata.destroy();
        try {
            client.close();
        }
        catch(Exception e) {} client = null;
    }
    System.out.println("Client Disconnected");
}
public void kirimData(String data){
    kirimdata.tambahData(data);
}
}
