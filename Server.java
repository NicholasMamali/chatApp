import java.io.*;
import java.net.*;
import java.util.*;

public class Server { 

   public static ArrayList<ServerClient>  clients=new ArrayList<>();
   
   public static void main (String args[]) throws UnknownHostException , InterruptedException, IOException {
   
   
   Socket sok = null;
   Scanner scan = new Scanner(System.in);
   ServerSocket ss = null;
   try {
      ss = new ServerSocket(9999);
   }
   catch(IOException e)
   {
      System.out.println("error connecting server socket"+  e);
   }
   ConnectClient c = null;
   
   
   while (true )  
   {
      
      try 
      {
         sok = ss.accept();
         System.out.println( "Connecting... wait: " );
      }catch(IOException e){
         System.out.println("Client connection error : >>" + e);
      }
      //create a new socket with thread to handle it for a new client
     c = new ConnectClient(sok);
     c.start();
     c.join();
     
     
     
     
     
     
     
     
     System.out.println( "-------------------------------------------------" );
     System.out.println( "---------------connected clients-----------------" );
          //////////
     for(ServerClient cli : clients)
      {
         System.out.print(cli.getId());
         System.out.print(" | ");
      }
      System.out.println();
      System.out.println( "------------------------------------------------" );
     ////////
   }
      
   }


}