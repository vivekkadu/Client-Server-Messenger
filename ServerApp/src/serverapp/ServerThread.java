
package serverapp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class ServerThread extends Thread{
    
    ServerForm sf; 
    
    ObjectInputStream oin;
    
    ObjectOutputStream out;
    
    ServerSocket serverSocket;
    
    Socket socket; 
    
    
    
    public ServerThread(ServerForm sf){
        
        this.sf=sf;
        
        
        try{
            
            serverSocket=new ServerSocket(Setting.port);
            JOptionPane.showMessageDialog(sf,"Server Started" );
            start();
        }catch(Exception e){
            
            
        }
    }
    
    //cretew a method send message
     
    public void sendMessage(String msg){
        
        try{
         out.writeObject(msg.toString());   
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
        public void run(){
             
            while(true){
            try{
                //accepting incoming connection to the server using thread
          
               socket=serverSocket.accept();
               //calling metho dto create input output objects
               openRender();
                
            }
            catch(Exception e){
               e.printStackTrace();
                
            }
        }
    }
        private void openRender(){
            
            try{
                oin= new ObjectInputStream(socket.getInputStream());
                out= new ObjectOutputStream(socket.getOutputStream());
                MsgRecThread mrt=new MsgRecThread(sf,oin,out);
            }
            catch(Exception e){
                
                e.printStackTrace();
            }
            
            
        }
        
        public class MsgRecThread extends Thread{
            
            ServerForm sf;
            ObjectInputStream oin;
            ObjectOutputStream out;
        
        public MsgRecThread(ServerForm sf, ObjectInputStream oin , ObjectOutputStream out){
            
            this.sf=sf;
            this.oin=oin;
            this.out=out;
            start();
        }
        
        public void run(){
            while(true){
            try{
                sf.jtaRec.append(oin.readObject().toString()+"\t\t\t>");
                
              DateFormat df = new SimpleDateFormat("dd/MM HH:mm:ss\n");
               Date dateobj = new Date();
               sf.jtaRec.append(df.format(dateobj));
                
            }catch(Exception e){
                
                
            }
            }
        }
}
}
