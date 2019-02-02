import java.io.*;
import java.net.*;
import java.util.*;

public class thread extends Thread {

   Scanner is;
   PrintStream ps;
   Message mes = null;
   Scanner scan;
   private String myId;

   public thread(Scanner is , PrintStream ps,String id) { 
   
   this.is = is;
   this.ps = ps;
   this.myId = id;
   scan = new Scanner(System.in);
   }
   
   public void run()
   {
   
      while (true)    
      { 
         
         mes = new Message ("00","xxxxx",myId,"888" ,scan.nextLine());
         //System.out.println( "you :   " + mes.getMessageContent() ) ;
         Client.sent.add(mes);  //add message to sent array , wait for ack to save it as delivered
         
         ///debuging statement
         for (Message m: Client.sent) 
         {
            System.out.println(m.getFormatedMessage());
         }
         ps.println(mes.getFormatedMessage());
         
         //added this for saving sent messages to file
         try{
         Client.saveMessages();
         }catch(FileNotFoundException exp) {
         System.out.println(exp);
         }catch(UnsupportedEncodingException exp1){
         System.out.println(exp1);
         }/////
      }
   

   }

}