import java.io.*;
import java.net.*;
import java.util.*;

public class ConnectClient extends Thread{
   Scanner inp;
   String id;
   Socket sok;
   ServerClient serverClient;
   
   public ConnectClient(Socket sok) throws UnknownHostException , IOException
   {
      this.inp = new Scanner(sok.getInputStream());
      this.sok = sok;
   }
   
   public boolean checkArray(String idx){
      boolean ans = false;
      for (ServerClient client0 : Server.clients)
      {
         if(idx.equals(client0.getId()))  {ans = true;break;}
      }
      return ans;
   }
   
   public void run()
   {
      id = inp.nextLine();                    //create a thread to take pass and id on side to allow othr users to connect
      System.out.println("Id recived, checking login for "  + id);
      if(checkArray(id))
      {
      //check if id is avaible and password is ccorrect if not close connection
         System.out.println("Incomplete, error with id  : already loged in or incorrect password");
         
         try{
            PrintStream p = new PrintStream(sok.getOutputStream());
            p.println("-------------------------------------------------------");
            p.println("Error connecting you, please check your Id and password");
            p.println("-------------------------------------------------------");
            p.println("                                                       ");
            sok.close();
         }catch(IOException e) {
            System.out.println("error closing socket" +e);
         }
      }
      else 
      {  //everythng is ok, connect the client and create a thrread to handle it
         serverClient = new ServerClient(sok,id);
         
         Server.clients.add(serverClient);
         
         new NewClientThread(serverClient).start();
         System.out.println("completed login...");
      }

   }


}