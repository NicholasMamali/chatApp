import java.io.*;
import java.net.*;
import java.util.*;

public class ServerClient {

      private PrintStream ps;
      private Socket sok;
      private Scanner is;
      private String clientId;
      
      public ServerClient(Socket socket,String clientId)
      {
         try {
         this.is = new Scanner(socket.getInputStream());
         this.ps = new PrintStream(socket.getOutputStream());
         }catch(IOException e){
         return;
      }

         this.sok=socket;
         this.clientId=clientId;
      
      } 
      
      public PrintStream getPrintStream()
      {
         return ps;
      }
      
      public Socket getSocket()
      {
         return sok;
      
      }
      
      
      public String getId()
      {
         return clientId;
      }
      
      public Scanner getInput()
      {
         return is;
      
      }
      
      
      



}