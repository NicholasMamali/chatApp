
public class Message{
   private String messageId;
   private String messageContent;
   private String messageSource;
   private String messageDestination;
   private String messageType;
   private int checkSum=123;
   private String message;
   private String fullMessage="";
   
   
  
   public Message(String messageType,String messageId,String messageSource,String messageDestination,String messageContent)
   {
      this.messageType=messageType;
      this.messageId=messageId;
      this.messageSource=messageSource;
      this.messageDestination=messageDestination;
      this.messageContent=messageContent;

   
   }
   
   public Message(String message)
   {
      this.messageType=message.substring(0,2);
      this.messageId=message.substring(2,7);
      this.messageSource=message.substring(7,10);
      this.messageDestination=message.substring(10,13);
      this.checkSum=123;//Integer.parseInt(message.substring(13,(message.indexOf("|"))));
      this.messageContent=message.substring(message.indexOf("|")+1,message.length());
      this.fullMessage=message; 
   }
   
   
   public String getFormatedMessage()
   {
      String formated="";
      //if message is not on this format "000111114442222003|I love youu Nick", incorparete it to this format
      if(fullMessage.equals(""))
      {
      
         formated=messageType+messageId+messageSource+messageDestination+""+checkSum+"|"+messageContent;
      }
      
      //otherwise just use the message provided in the second constructor
      else{
      
         formated=fullMessage;
      }
      
      return formated;
      
      
   
   }
   
 
   
   
   public void PrintMessage()
   {
      System.out.println(this.messageType);
      System.out.println(this.messageId);
      System.out.println(this.messageSource);
      System.out.println(this.messageDestination);
      System.out.println(this.checkSum);
      System.out.println(this.messageContent);
   
   }
   
   
   //getters
   
   public String getMessageType()
   {
      return messageType;
   }
   
   public String getMessageId()
   {
      return messageId;
   }
   
   public String getMessageSource()
   {
      return messageSource;
   }
   public String getMessageDestination()
   {
      return messageDestination;
   }
   
   public int getCheckSum()
   {
      return checkSum;
   }
   
   public String getMessageContent()
   {
      return messageContent;
   } 
   
   ///////////compute check sum//////////////////////////
   
   public int getCheckSump()
   {
   
      return 0;
   }
   
   
      
   



}