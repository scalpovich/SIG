/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GTAgw;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
//import org.json.JSONObject;

import org.quickserver.net.server.ClientHandler;
import org.quickserver.net.server.QuickServer;

/**
 *
 * @author aldi
 */
class SvrSocket {
    static TSenderSocket ts;
    static TReceiverSocket tr;
    
    static Queue qSend;
    static Queue qRec;
    private static SvrSocket _me;
    private static SAFChecker safCheck;

    public static SvrSocket instance() {
        if(_me!=null) {
            return _me;
        } else {
            return new SvrSocket();
        }
    }

    public SvrSocket() {
        _me = this;
    }
    
    //InputStream in = null;
    //OutputStream out = null;
    Socket sock = null;
    //private static String _host = null;
    //private static int _port = 0;
    private String _host = "192.168.56.101";
    private int _port = 7000;
    private boolean status;
    private QuickServer _qs;
    private EchoSender echoSender;
    private static ConnectorChecker connCheck;
    
    static void start() {
        qSend = new LinkedList();
        qRec = new LinkedList();
        
        connCheck = new ConnectorChecker();
        Thread tcc = new Thread(connCheck);
        tcc.start();

        
    }

//    static void add(String paket) {
//        synchronized(qSend) {
//            qSend.add(paket);
//        }
//        //System.out.println("ts notify");
//        
//        synchronized(ts) {
//            ts.notify();
//        }
//    }

    void add(ISOMsg paket) {
        if(ts!=null) {
            synchronized(qSend) {
                qSend.add(paket);
            }
            
            synchronized(ts) {
                ts.notify();
            }
        } else {
            try {
                if(paket.getMTI().equals("0400")) {

/*                    ISO1987APackagerJ packager = new ISO1987APackagerJ();
                    packager.setPackager();
                    paket.setPackager(packager);           
*/                    
                    //Random randomGenerator = new Random();
                    
                    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
                    
                    byte dataToWrite[] = paket.pack();
                    //String safName = paket.getString(11)+paket.getString(12)+String.valueOf(randomGenerator.nextInt(100));
                    //String safName = paket.getString(11)+paket.getString(12);
                    //String safName = paket.getString(11)+"-"+timeStamp;
                    String safName = paket.getString(11);
                    
                    
                    FileOutputStream out = new FileOutputStream("safgw/"+safName);
                    out.write(dataToWrite);
                    out.close();                    
                    
                    writeLog("0400 RECEIVED SAVED to safgw/"+safName);
                    
                } else if(paket.getMTI().endsWith("00")) {
                    Iterator iterator = _qs.findAllClient();
                    
                    if(iterator.hasNext()) {

                        ClientHandler ch = (ClientHandler) iterator.next();
//                        Data cd = (Data) ch.getClientData();
                        int i = 0;
                        int nextAssignedHandler = 0;

                        if(i>=nextAssignedHandler) {
                            //ch.sendClientMsg(ISOUtil.dumpString(byteReceive));
                            //ch.sendClientBinary(byteLength);
                            //ch.sendClientBinary(byteReceive);

                            paket.setMTI(paket.getMTI().substring(0, 2)+"10");
                            paket.set(39, "89");

                            byte byteReceive[] = paket.pack();

                            byte[] byteLength = new byte[2];
                            byteLength[0]=(byte) (byteReceive.length >> 8 & 255);
                            byteLength[1]=(byte) (byteReceive.length & 255);

                            byte[] bytetoSend=ISOUtil.concat(byteLength,byteReceive);
                            ch.sendClientBinary(bytetoSend);

//                            cd.addUpMessageCount();

                            writeLog("[SEND TO SPAWN-"+nextAssignedHandler+": "+ISOUtil.dumpString(byteReceive));

                            if(iterator.hasNext()) {
                                nextAssignedHandler++;
                            } else {
                                nextAssignedHandler = 0;
                            }
                        } else {
                            i++;
                        }                          
                        
                    }
                    
                   
                }
            } catch (ISOException ex) {
                Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);
            }
        }        
        
        /*
        synchronized(qSend) {
            qSend.add(paket);
        }
        //System.out.println("ts notify");
        
        if(ts!=null) {
            synchronized(ts) {
                ts.notify();
            }
        }
        */
    }
    
    
    boolean connect() {
        if(_host!=null && _port>0) {
            try {
                SocketAddress hostPort = new InetSocketAddress(_host, _port);
                
                //sock = new Socket(_host,_port);
                sock = new Socket();
                sock.connect(hostPort, 5000);
                
                sock.setKeepAlive(true);
                sock.setSoTimeout(2000);
                sock.setSoLinger(true, 2000);

                //out = sock.getOutputStream();            
                //ts = new TSenderSocket(out);
                ts = new TSenderSocket(sock.getOutputStream());
                Thread tts = new Thread(ts);
                tts.start();

                //in = sock.getInputStream();
                //tr = new TReceiverSocket(in);
                tr = new TReceiverSocket(sock.getInputStream());
                Thread ttr = new Thread(tr);
                ttr.start();
                
                synchronized(qSend) {
                    writeLog("[CLEAR ALL QUEUE]");
                    qSend.clear();
                }
                
                signon();
            /*        
                for (final File fileEntry : new File("safgw/").listFiles()) {
                    if(!fileEntry.getName().startsWith(".") && !fileEntry.getName().equals("done")) {
                        String safName = fileEntry.getName();
                        writeLog("Processing "+safName);
                        byte[] isiFile = IOUtil.readFile("safgw/"+safName);
                        ISOMsg _paket = new ISOMsg();

                        ISO1987APackagerJ packager = new ISO1987APackagerJ();
                        packager.setPackager();
                        _paket.setPackager(packager);           

                        try {
                            _paket.unpack(isiFile);
                            //qSend.add(_paket);
                            add(_paket);

                            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
                            File newfile;

                            if(safName.split("-").length>3) {
                                //udah 3 kali kirim, masukkin ke done
                                newfile = new File("safgw/done/" + safName);
                            } else {
                                newfile = new File("safgw/" + safName + "-" + timeStamp);
                            }

                            fileEntry.renameTo(newfile);

                            //fileEntry.delete();

                            //writeLog("Deletting "+fileEntry.getName());
                            writeLog("Rename to "+newfile.getAbsolutePath()+newfile.getName());

                            //System.out.println(fileEntry.getName());
                        } catch (ISOException ex) {
                            Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);
                        }
                    }
                }
            */    
                echoSender = new EchoSender(ts, tr);
                Thread tes = new Thread(echoSender);
                tes.start();
                
                status = true;
            } catch (Exception ex) {
                Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);
                //writeLog("Gagal koneksi ke 987");
            }            
            return status;
            
        } else {
            return false;
        }


    }

    void setHost(String host, int port) {
        _host = host;
        _port = port;
    }

    boolean isConnected() {
        if(sock!=null)
            return sock.isConnected();
        else
            return false;
        //return status;
    }

    void disconnect() {
        if(echoSender!=null) {
            try {
                echoSender.stop();
            } catch(Exception e) {
                
            }
        }
        
        if(ts!=null) {
            try {
                ts.stop();                    
                synchronized(ts) {
                    ts.notify();
                }
            } catch(Exception ex) {
                Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);                
            }
            
            ts = null;
        }

        if(tr!=null) {
            try {
                tr.stop();                    
                synchronized(tr) {
                    tr.notify();
                }
            } catch(Exception ex) {
                Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);                
            }
            
            tr = null;
        }

        try {
            if(sock!=null) {
                if(sock.isConnected())
                    sock.close();

                sock = null;
            }
        } catch (IOException ex) {
            Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);
        }
        
    }

    String getHost() {
        return _host;
    }

    int getPort() {
        return _port;
    }

    void setQS(QuickServer xServer) {
        _qs = xServer;
    }

    private static void writeLog(String log) {
        //System.out.println(log+"\n");
  //      GW.logger.info(log);
    }

    private QuickServer getQS() {
        return _qs;
    }

    void signon() {
   /*     
        SimpleDateFormat transmissionTime = new SimpleDateFormat("MMddhhmmss");
        ISO1987APackagerJ packager = new ISO1987APackagerJ();
        packager.setPackager();
        ISOMsg isoMsgSend = new ISOMsg();
        isoMsgSend.setPackager(packager);

        long now=System.currentTimeMillis();
        try {
            isoMsgSend.setMTI("0800");
            isoMsgSend.set(7,transmissionTime.format(now));
            String tempStan = ("" + now);
            isoMsgSend.set(11, tempStan.substring(7, 13));
            isoMsgSend.set(70,"001");

            if(isConnected()) {
                writeLog("Send SignON");
                add(isoMsgSend);
            }

        } catch (ISOException ex) {
            //Logger.getLogger(QSAdminCommandPlugin.class.getName()).log(Level.INFO, null, ex);
            //ret = "-ERR Disconnected";
        }
*/    }

    private static class TSenderSocket implements Runnable {
        private final LinkedList<ISOMsg> qLocalSend;
        private boolean isrun;
        private final OutputStream _out;
        
        private TSenderSocket(OutputStream out) {
            //System.out.println("TS cons");
            isrun = true;
            _out = out;
            qLocalSend = new LinkedList<ISOMsg>();
        }

        @Override
        public void run() {
            
            while(isrun) {
                if(qSend.size()<1) {
                    synchronized(this) {
                        try {
                            //System.out.println("wait");
                            this.wait();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);
                        }
                    }
                }

                synchronized(qSend) {
                    while(qSend.size()>0) {
                        ISOMsg e = (ISOMsg) qSend.poll();
                        qLocalSend.add(e);
                    }
                }
                
                while(qLocalSend.size()>0) {
                    ISOMsg k = (ISOMsg) qLocalSend.poll();
                    try {
                        //System.out.println("ts send "+k);
                        //_out.write(k.getBytes());
                        byte[] byteSend=k.pack();
                        byte[] byteLength = new byte[2];
                        byteLength[0]=(byte) (byteSend.length >> 8 & 255);
                        byteLength[1]=(byte) (byteSend.length & 255);
        
                        byteSend=ISOUtil.concat(byteLength,byteSend);
       
                        writeLog("[SEND TO 987] "+ISOUtil.dumpString(byteSend));
                        //out.write(byteSend);
                        //send(out,byteSend);

                        _out.write(byteSend);
                    } catch (IOException ex) {
                        Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);
                        isrun = false;
                        writeLog("PUTUS KONEKSI KE GW2GAPURA IOException");
                        //sock = null;
                        //echoSender.stop();
                        SvrSocket.instance().disconnect();
                        
                    } catch (ISOException ex) {
                        Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);
                        isrun = false;
                        writeLog("PUTUS KONEKSI KE GW2GAPURA ISOException");
                        //sock = null;
                        //echoSender.stop();
                        SvrSocket.instance().disconnect();
                        
                    }
                }
            }
        }

        private void stop() {
            isrun = false;
        }

        private void writeLog(String log) {
            //System.out.println(log+"\n");
//            GW.logger.info(log);
        }
    }
    
    private static class TReceiverSocket implements Runnable {
        private final LinkedList<String> qLocalReceive;
        private boolean isrun;
        private InputStream _in;
        //private DataInputStream _din;
        private byte[] byteLength;
//        private ISO1987APackagerJ packager;
        private int nextAssignedHandler = 0;
        private ISOMsg isoMsgRec;
        private String _mti;
        private boolean readHeaderState = true;
        private int len = 0;
        private byte[] byteReceive;
        //private int rh;
        
        private TReceiverSocket(InputStream in) {
            isrun = true;
            _in = in;
            //_din = new DataInputStream(in);
            qLocalReceive = new LinkedList<String>();
            byteLength=new byte[2];
            //packager = new ISO1987APackagerJ();
            
            isoMsgRec=new ISOMsg();
/*            packager = new ISO1987APackagerJ();
            packager.setPackager();
            isoMsgRec.setPackager(packager);
*/            //writeLog("Mulai lagi receivernya");
            
        }

        @Override
        public void run() {
            
            while(isrun) {
                try {
                    if(readHeaderState==true) {
                        //rh++;
                        //writeLog("rh "+rh);
                        if(_in.read(byteLength)>0) {
                            //rh++;
                            //writeLog("rh "+rh);
                            
                            //writeLog("[RECEIVE FROM 987 HEADER: "+ISOUtil.dumpString(byteLength));
                            readHeaderState = false;
                            len=(((byteLength[0] & 0xFF) << 8) + (byteLength[1] & 0xFF));
                            byteReceive = new byte[len];
                            //writeLog("[RECEIVE FROM 987 LEN: "+len);
                        } else {
                            //putus
                            writeLog("Koneksi ke 987 Putus");
                            SvrSocket.instance().disconnect();
                        }
                    } else {
                        if(_in.read(byteReceive)!=-1)
                        {
                            //isoMsgRec.unpack(ISOUtil.hex2byte(ISOUtil.hexString(byteReceive).substring(0,byteReceive.length*2-2)));
                            try {
                                
                                //rh = 0;
                                
                                writeLog("[RECEIVE FROM 987: "+ISOUtil.dumpString(byteReceive));
                                clear(isoMsgRec);
                                isoMsgRec.unpack(byteReceive);
                                //writeLog("[RECEIVE RC:"+isoMsgRec.getString(39)+"]"+ISOUtil.dumpString(byteReceive));

                                _mti = isoMsgRec.getMTI();
                                //if(_mti.equals("0200") || _mti.equals("0400") || _mti.equals("0800") || _mti.equals("0210")) {
                                    int i = 0;
                                    Iterator iterator = SvrSocket.instance().getQS().findAllClient();
                                    if(iterator.hasNext()) {
                                        
                                        if(_mti.equals("0800")) {
                                            ISOMsg isoMsgSend=isoMsgRec;
                                            isoMsgSend.setMTI("0810");
                                            isoMsgSend.set(39, "00");
                                            SvrSocket.instance().add(isoMsgSend);
                                        } else {
                                            ClientHandler ch = (ClientHandler) iterator.next();
//                                            Data cd = (Data) ch.getClientData();

                                            if(i>=nextAssignedHandler) {
                                                //ch.sendClientMsg(ISOUtil.dumpString(byteReceive));
                                                //ch.sendClientBinary(byteLength);
                                                //ch.sendClientBinary(byteReceive);
                                                
                                                byte[] bytetoSend=ISOUtil.concat(byteLength,byteReceive);
                                                ch.sendClientBinary(bytetoSend);
                                                
//                                                cd.addUpMessageCount();
                                                
                                                //kalau reversal dan ada stannya di safgw maka pindahin ke done
                                                if(isoMsgRec.getMTI().equals("0410")) {
                                                    removeSaf(isoMsgRec.getString(11));
                                                }

                                                writeLog("[SEND TO SPAWN-"+nextAssignedHandler+": "+ISOUtil.dumpString(byteReceive));

                                                if(iterator.hasNext()) {
                                                    nextAssignedHandler++;
                                                } else {
                                                    nextAssignedHandler = 0;
                                                }
                                            } else {
                                                i++;
                                            }                                            
                                        }
                                    } else {
                                        //ngga ada spawn konek
                                        //bales rc 92
                                        //bales rc 91 20150607
                                        //kalo bit2 echo atau trx
                                        _mti = isoMsgRec.getMTI();
                                        if(_mti.equals("0200") || _mti.equals("0400") || _mti.equals("0800")|| _mti.equals("0810")|| _mti.equals("0210")|| _mti.equals("0410")|| _mti.equals("0420")|| _mti.equals("0430")) {
                                            ISOMsg isoMsgSend=isoMsgRec;
                                            
                                            if(_mti.equals("0200"))
                                                isoMsgSend.setMTI("0210");
                                            else if(_mti.equals("0400"))
                                                isoMsgSend.setMTI("0410");
                                            else if(_mti.equals("0800"))
                                                isoMsgSend.setMTI("0810");
                                            
                                            isoMsgSend.set(39, "91");
                                            SvrSocket.instance().add(isoMsgSend);
                                        }
                                    }
                                //}
                                


                                                                
//                                synchronized(qLocalReceive) {
//                                    qLocalReceive.add(ISOUtil.dumpString(byteReceive));
//                                }
                            
                                readHeaderState = true;
                                byteLength=new byte[2];
                                    
                                    
                            } catch (ISOException ex) {
                                Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);
                            } catch (Exception ex) {
                                Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);
                            }
                            //System.out.println("[bit48: "+isoMsgRec.getString(48));
                            //System.out.println("[bit4: "+isoMsgRec.getString(4));
                            //System.out.println("[bit48: "+isoMsgRec.getString(48));
                            //System.out.println("[bit4: "+isoMsgRec.getString(4));
                        }
                        else {
                            //throw new IOException();
                            writeLog("Koneksi ke 987 Putus");
                            SvrSocket.instance().disconnect();
                        }                        
                        
                    }
                    
                    /*
                    while(in.read(byteLength)>0) {
                        int len=(((byteLength[0] & 0xFF) << 8) + (byteLength[1] & 0xFF));
                        byte[] byteReceive = new byte[len];

                        if(in.read(byteReceive)!=-1)
                        {
                            //isoMsgRec.unpack(ISOUtil.hex2byte(ISOUtil.hexString(byteReceive).substring(0,byteReceive.length*2-2)));
                            try {
                                writeLog("[RECEIVE FROM 987: "+ISOUtil.dumpString(byteReceive));
                                clear(isoMsgRec);
                                isoMsgRec.unpack(byteReceive);
                                //writeLog("[RECEIVE RC:"+isoMsgRec.getString(39)+"]"+ISOUtil.dumpString(byteReceive));

                                _mti = isoMsgRec.getMTI();
                                //if(_mti.equals("0200") || _mti.equals("0400") || _mti.equals("0800") || _mti.equals("0210")) {
                                    int i = 0;
                                    Iterator iterator = _qs.findAllClient();
                                    if(iterator.hasNext()) {
                                        
                                        if(_mti.equals("0800")) {
                                            ISOMsg isoMsgSend=isoMsgRec;
                                            isoMsgSend.setMTI("0810");
                                            isoMsgSend.set(39, "00");
                                            SvrSocketToGapura.add(isoMsgSend);
                                        } else {
                                            ClientHandler ch = (ClientHandler) iterator.next();
                                            Data cd = (Data) ch.getClientData();

                                            if(i>=nextAssignedHandler) {
                                                //ch.sendClientMsg(ISOUtil.dumpString(byteReceive));
                                                //ch.sendClientBinary(byteLength);
                                                //ch.sendClientBinary(byteReceive);
                                                
                                                byte[] bytetoSend=ISOUtil.concat(byteLength,byteReceive);
                                                ch.sendClientBinary(bytetoSend);
                                                
                                                cd.addUpMessageCount();

                                                writeLog("[SEND TO SPAWN-"+nextAssignedHandler+": "+ISOUtil.dumpString(byteReceive));

                                                if(iterator.hasNext()) {
                                                    nextAssignedHandler++;
                                                } else {
                                                    nextAssignedHandler = 0;
                                                }
                                            } else {
                                                i++;
                                            }                                            
                                        }
                                    } else {
                                        //ngga ada spawn konek
                                        //bales rc 92
                                        //kalo bit2 echo atau trx
                                        _mti = isoMsgRec.getMTI();
                                        if(_mti.equals("0200") || _mti.equals("0400") || _mti.equals("0800")) {
                                            ISOMsg isoMsgSend=isoMsgRec;
                                            
                                            if(_mti.equals("0200"))
                                                isoMsgSend.setMTI("0210");
                                            else if(_mti.equals("0400"))
                                                isoMsgSend.setMTI("0410");
                                            else if(_mti.equals("0800"))
                                                isoMsgSend.setMTI("0810");
                                            
                                            isoMsgSend.set(39, "92");
                                            SvrSocketToGapura.add(isoMsgSend);
                                        }
                                    }
                                //}
                                


                                                                
//                                synchronized(qLocalReceive) {
//                                    qLocalReceive.add(ISOUtil.dumpString(byteReceive));
//                                }
                            
                            } catch (ISOException ex) {
                                Logger.getLogger(SvrSocketToGapura.class.getName()).log(Level.INFO, null, ex);
                            } catch (Exception ex) {
                                Logger.getLogger(SvrSocketToGapura.class.getName()).log(Level.INFO, null, ex);
                            }
                            //System.out.println("[bit48: "+isoMsgRec.getString(48));
                            //System.out.println("[bit4: "+isoMsgRec.getString(4));
                        }
                        else throw new IOException();                        
                    }
                    */
                } catch (IOException ex) {
                    //isrun = false;
                    //Logger.getLogger(SvrSocketToGapura.class.getName()).log(Level.INFO, null, ex);
                    
                    //writeLog("PUTUS KONEKSI KE 987 IOException");
                    //sock = null;
                    
                    //SvrSocketToGapura.disconnect();
                    //SvrSocketToGapura.instance().disconnect();
                    
                }

                /*
                try {
                    this.wait(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SvrSocketToGapura.class.getName()).log(Level.INFO, null, ex);
                }
                */
            }
        }

        private void writeLog(String log) {
            //System.out.println(log+"\n");
                            
//            GW.logger.info(log);
            //LoggerX.write(log);
        //logger.info("QSCommand : "+command);

        }

        private void clear(ISOMsg msg) {
            for(int i=1;i<=128;i++)
            {
                msg.unset(i);
            }
        }

        private void stop() {
            isrun = false;
        }

        private boolean removeSaf(String STAN) {
            boolean result = false;
            
            writeLog("Removing SAF STAN : "+STAN);
            
            for (final File fileEntry : new File("safgw/").listFiles()) {
                if(fileEntry.getName().startsWith(STAN)) {
                    writeLog("Move SAF to done/"+fileEntry.getName());
                    File newfile = new File("safgw/done/" + fileEntry.getName());
                    
                    fileEntry.renameTo(newfile);
                    result = true;
                    break;
                }
            }
            
            return result;
        }
    }    

    private static class EchoSender implements Runnable {
        private final TSenderSocket _ts;
        private final TReceiverSocket _tr;
        private boolean isrun;
        
        private EchoSender(TSenderSocket ts, TReceiverSocket tr) {
            _ts = ts;
            _tr = tr;
            isrun = true;
        }

        @Override
        public void run() {
            while(isrun) {
                SimpleDateFormat transmissionTime = new SimpleDateFormat("MMddhhmmss");
                ISO1987APackagerJ packager = new ISO1987APackagerJ();
                packager.setPackager();
                ISOMsg isoMsgSend = new ISOMsg();
                isoMsgSend.setPackager(packager);

                long now=System.currentTimeMillis();
                try {
                    isoMsgSend.setMTI("0800");
                    isoMsgSend.set(7,transmissionTime.format(now));
                    String tempStan = ("" + now);
                    isoMsgSend.set(11, tempStan.substring(7, 13));
                    isoMsgSend.set(70,"301");
                    SvrSocket.instance().add(isoMsgSend);
                    
                } catch (ISOException ex) {
                    Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);
                }
                
                synchronized(this) {
                    try {
                        this.wait(30000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);
                    }
                }
                                
            }
        }

        private void stop() {
            isrun = false;
        }
    }

    private static class ConnectorChecker implements Runnable {

        public ConnectorChecker() {
        }

        @Override
        public void run() {
            boolean isrun = true;
            int retry = 0;
            
            while(isrun) {
                String ghost = SvrSocket.instance().getHost();
                int gport = SvrSocket.instance().getPort();
                
                writeLog("Cek koneksi 987 "+ghost+":"+gport);
                
                if(!SvrSocket.instance().isConnected()) {
                    writeLog("Konek 987 "+ghost+":"+gport);
                    
                    boolean r = SvrSocket.instance().connect();
                    if(!r) {
                        //gagal konek ke GW2GAPURA
                        writeLog("Gagal koneksi ke 987");

                        if(qSend.size()>0) {
                            while(qSend.size()>0) {
                                ISOMsg k = (ISOMsg) qSend.poll();
                                ISO1987APackagerJ packager = new ISO1987APackagerJ();
                                packager.setPackager();
                                k.setPackager(packager);           

                                
                                Iterator iterator = SvrSocket.instance().getQS().findAllClient();
                                if(iterator.hasNext()) {

                                    ClientHandler ch = (ClientHandler) iterator.next();
//                                    Data cd = (Data) ch.getClientData();
                                    int i = 0;
                                    int nextAssignedHandler = 0;
                                    
                                    
                                    if(i>=nextAssignedHandler) {
                                        try {
                                            String mti = k.getMTI();
                                            String mtiK = "";
                                            if(mti.equals("0800")) {
                                                mtiK = "0810";
                                            } else if(mti.equals("0200")) {
                                                mtiK = "0210";
                                            }
                                            
                                            k.setMTI(mtiK);
                                            k.set(39, "89");
                                            //byte[] byteLength=k.pack();

                                            //int len=(((byteLength[0] & 0xFF) << 8) + (byteLength[1] & 0xFF));
                                            //byte[] byteReceive = new byte[len];
                                            
                                            
                                            byte[] byteSend=k.pack();
                                            byte[] byteLength = new byte[2];
                                            byteLength[0]=(byte) (byteSend.length >> 8 & 255);
                                            byteLength[1]=(byte) (byteSend.length & 255);
                                            
                                            byte[] bytetoSend=ISOUtil.concat(byteLength,byteSend);
                                            //ch.sendClientMsg(ISOUtil.dumpString(byteReceive));
                                            //ch.sendClientBinary(byteLength);
                                            //ch.sendClientBinary(byteReceive);
                                            //ch.sendClientBinary(byteSend);
                                            //ch.sendClientBinary(bytetoSend);
                                            ch.sendClientBinary(bytetoSend, 0, bytetoSend.length);
//                                            cd.addUpMessageCount();

                                            //writeLog("[SEND TO SPAWN-"+nextAssignedHandler+": "+ISOUtil.dumpString(byteSend));
                                            writeLog("[SEND TO SPAWN-"+nextAssignedHandler+": "+ISOUtil.dumpString(bytetoSend));

                                            if(iterator.hasNext()) {
                                                nextAssignedHandler++;
                                            } else {
                                                nextAssignedHandler = 0;
                                            }
                                        } catch (IOException ex) {
                                            Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);
                                        } catch (ISOException ex) {
                                            Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);
                                        }
                                    } else {
                                        i++;
                                    }                                            
                                }
                            }
                        }

                        synchronized(this) {
                            try {
                                //writeLog("Cek koneksi ke 987 ke-"+(retry+1));
                                this.wait(1000);

                            } catch (InterruptedException ex) {
                                Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);
                            }
                        }                        
                    }
                } else {

                    synchronized(this) {
                        try {
                            writeLog("Masih konek, tunggu 5s");
                            this.wait(5000);
                        } catch(Exception e) {}
                    }                    
                    
                }


                
                
            }
        }
        
        private void writeLog(String log) {
            //System.out.println(log+"\n");
            try{
//                GW.logger.info(log);
            } catch(Exception e) {}
        }
        
    }

    private static class SAFChecker implements Runnable{

        public SAFChecker() {
        }

        @Override
        public void run() {
/*            boolean isrun = true;
            
            while(isrun) {
                //cek file saf yang udah timeout
                if(SvrSocket.instance().isConnected()) {

                    for (final File fileEntry : new File("safgw/").listFiles()) {
                        if(!fileEntry.getName().startsWith(".") && !fileEntry.getName().startsWith("done")) {
                            String safName = fileEntry.getName();
                            writeLog("Checking "+safName);

                            long diffSec = Constant.timeout+1;
                            //long diffSec = 0;

                            if(safName.split("-").length>1) {
                                String safTime = safName.substring(safName.length()-14);
                                int yy = Integer.valueOf(safTime.substring(0, 4));
                                int mm = Integer.valueOf(safTime.substring(4, 6));
                                int dd = Integer.valueOf(safTime.substring(6, 8));
                                int hh = Integer.valueOf(safTime.substring(8, 10));
                                int ii = Integer.valueOf(safTime.substring(10, 12));
                                int ss = Integer.valueOf(safTime.substring(12, 14));

                                Calendar cnow = Calendar.getInstance();
                                Calendar csaf = Calendar.getInstance();
                                csaf.set(yy, mm-1, dd, hh, ii, ss);
                              
                                diffSec = ((cnow.getTimeInMillis()-csaf.getTimeInMillis())/1000) % 60;
                                writeLog("Different "+diffSec);
                            }

                            if(diffSec>Constant.timeout) {
                                writeLog("Different lebih dari timeout "+Constant.timeout);
                                //saf timeout, kirim

                                byte[] isiFile;
                                try {
                                    isiFile = IOUtil.readFile("safgw/"+safName);
                                    ISOMsg _paket = new ISOMsg();

                                    ISO1987APackagerJ packager = new ISO1987APackagerJ();
                                    packager.setPackager();
                                    _paket.setPackager(packager);                                   

                                    _paket.unpack(isiFile);
                                    //qSend.add(_paket);
                                    SvrSocket.instance().add(_paket);

                                    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
                                    File newfile;

                                    if(safName.split("-").length>3) {
                                        //udah 3 kali kirim, masukkin ke done
                                        newfile = new File("safgw/done/" + safName);
                                    } else {
                                        newfile = new File("safgw/" + safName + "-" + timeStamp);
                                    }

                                    fileEntry.renameTo(newfile);

                                    //fileEntry.delete();

                                    //writeLog("Deletting "+fileEntry.getName());
                                    writeLog("Rename to "+newfile.getAbsolutePath()+newfile.getName());


                                } catch (IOException ex) {
                                    Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);
                                } catch (ISOException ex) {
                                    Logger.getLogger(SvrSocket.class.getName()).log(Level.INFO, null, ex);
                                }                            

                            }


                        }
                    }                    
                    
                }
                
                synchronized(this) {
                    try {
                        //writeLog("Masih konek, tunggu 1s");
                        this.wait(1000);
                    } catch(Exception e) {}
                }                    
            }
  */      }
    }
}
