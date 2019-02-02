import java.io.*;
import java.net.*;
import java.util.*;

public class NewClientThread extends Thread {


   protected Socket socket;
   Scanner is;
   PrintStream ps;
   String me;
   Scanner scan;
   ServerClient client;


   public NewClientThread(ServerClient s) { 
   
      this.socket = s.getSocket();
      this.client = s;
      this.is = s.getInput();
      this.ps = s.getPrintStream();
      
      scan = new Scanner(System.in);
      
   }
   
   public void run() {
   Message mes = null;
   boolean loop = true;
   while (loop)    
   { 
      try{ //wait for client console to send message
      mes = new Message(is.nextLine());
      }catch(NoSuchElementException e)
      {  //exception when client is no longer online and can not recieve message from them, disconncted and break loop
      //********check thread safety here*************//
         System.out.println("Disconnected client");
         for(ServerClient c : Server.clients)
         {
            if(c.getId().equals(client.getId())) 
            {
               Server.clients.remove(c);
               break;
            }
         }
         try{
            client.getSocket().close();
         }catch(IOException ec) {
            System.out.println("error closing socket" +ec);
         }
         loop = false;
      }
      
      if(mes.getMessageType().equals("00"))
      { //send new text message to specified destination and wait for ack then remove from array of pending messages
         for(ServerClient c : Server.clients)
         {
            
            if(c.getId().equals(mes.getMessageDestination()) )
            {
               
               c.getPrintStream().println(mes.getFormatedMessage());
               break;
            }
         }
         //notify client that sent the message that you recieved it at server and you are trying to send it to the client
         Message ackRecAtSer = new Message("01","xxxxx","xxx","xxx" ,"xxxxx:");
         ps.println(ackRecAtSer.getFormatedMessage() );
         System.out.println("recieved mes from "+ client.getId()+ " sent back ack");
      }
      
      
   }
   //socket.close();
   }


}