import java.io.*;
import java.net.*;
import java.util.*;

public class Client { 
   
   
   
   private static String myId;
   public static ArrayList<Message> sent = new ArrayList<>();
   //public static 
   public static void main (String args[]) throws UnknownHostException , IOException {
   
      Message mes = null;
      loadMessages();
      //get id
      Scanner scan = new Scanner(System.in);
      System.out.println("Enter your Id");
      myId = scan.nextLine();
      //connect to server and get streams for checking id
      Socket sok =  new Socket ("127.0.0.1", 9999);
      Scanner is = new Scanner (sok.getInputStream());
      PrintStream ps = new PrintStream(sok.getOutputStream());
      ps.println(myId);
     //check if connected and id is correct (with password)
      
      thread t = new thread(is,ps,myId);
      t.start();
     
      boolean loop =  true;
      while(loop) 
      {
         try{
         mes = new Message(is.nextLine());
         }catch(NoSuchElementException ex)
         {
            loop = false;
         }
         //check what the message is :  new message or an ACK of sent messages/delivered by id at [0:2] :  00 is new text message, 01 is  message sent with its id , 02 is NACK with id,03 is delivered message 04 new png recieved
         if(mes.getMessageType().equals("00"))
         {
         //we have recieved a new text message print out the message and send back a posAck that you recieved the message
            System.out.println(mes.getMessageContent());
            //ps.println("01"+"mesId"+"source"+"dest"+"cheksum");
            
            //send ack back to server for message recieved -- 
            //so they notify client who sent the message to me that i recived it
            Message mesDel = new Message("01","xxxxx","xxx","xxx" ,"xxxxx:");
            ps.println( mesDel.getFormatedMessage() );
            System.out.println("recieved mes from [server side] "+ mes.getMessageSource()+ " sent back ack for delivered");

         }
         else if(mes.getMessageType().equals("01"))
         {
         //we have recived a pos ack for a message with id mes(2,7)
            ackMessages(mes.getMessageContent());
            
         }
         else if(mes.getMessageType().equals("02"))
         {
         //we have recived a neg ack for a message with id mes(2,7)
            
         }
         else if(mes.getMessageType().equals("03"))
         {
         //we have recived a neg ack for a message with id mes(2,7)
            
         }else{System.out.println(mes.getMessageContent());}
      }
   
      
   }
   
   static void ackMessages(String mesIds){
   
      //take in a string "xxxxx,yyyyy,sssss,rrrrr:" with message ids including acks from server for delivered mes
      System.out.println("recieved ack from server");
      String currentId;
      while(true)
      {
         //get id and ack
         currentId = mesIds.substring(0,5);
         for(Message m: sent) 
         {
            if(m.getMessageId().equals(currentId) )
            {
               sent.remove(m);
               System.out.println("removed mes >>> move it to delivered");
               break;
            }
         }

         
         if(mesIds.substring(5,6).equals(":"))
         {
            //we are at the last id of acks
            break;
         }
         mesIds = mesIds.substring(6);  
      }
   }
   
   static void loadMessages() throws IOException{
   
      Scanner in = null;
      FileReader file = null;
      try {
         file = new FileReader("sent.txt");
         in = new Scanner (file);
         }catch(FileNotFoundException ex) {
            System.out.println("Error loading sent text from file "+ex);
         }
         
         while(in.hasNext()){
            //System.out.println(in.nextLine());
            sent.add(new Message(in.nextLine()));
         }
      file.close();
      
   }
   static void saveMessages()  throws FileNotFoundException,UnsupportedEncodingException{
      PrintWriter writer = new PrintWriter("sent.txt", "UTF-8");
      
      for(Message m: sent) 
      {
         writer.println(m.getFormatedMessage());
      }
      writer.close();
   }
      


}