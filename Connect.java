import java.net.*;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
public class Connect{
   public static void main(String[]args){
      ArrayList<String> a = new ArrayList<>();
      String toma = "";
      try{
         URL connectURL = new URL("http://beewareus.ddns.net/juan.php");
         BufferedReader in = new BufferedReader(
            new InputStreamReader(connectURL.openStream()));
      
         String inputLine;
         StringBuilder sb = new StringBuilder();
         while ((inputLine = in.readLine()) != null){
            toma = inputLine;
            sb.append(inputLine);
         }
         String[] strArray2 = sb.toString().split(Pattern.quote("split"));
        
         for(int i=0;i<strArray2.length;i++){
            a.add(strArray2[i]);
         }
         
         in.close();
      }
      catch(IOException e){
         System.out.println("Error: " + e);
      }
     
      toma = toma.replaceAll("\\[","");
      toma = toma.replaceAll("\\]","");
      toma = toma.replaceAll("\\{","");
      toma = toma.replaceAll("\"","");
     
      String[] s = toma.split("}");
      String build = "";
      for(String x: s){
         build = build + x;  
      }
      String[] g = build.split(",");
      
      ArrayList<Book> books = new ArrayList<>();
     int count = 0;
      for(String i: g){
         String[] line = i.split(":");
        
         if(line[0].equals("id")){
            Book book = new Book();
            book.setId(line[1]);
            books.add(book);
            
         } 
         else{
            books.get(count).setTxt(line[1]);   
            count++;
         }
         
      }
      for(Book x: books){
         System.out.println(x.getId() + " " + x.getTxt());
      }
   }
   
}

class Book{
   private String id;
   private String txt;
   
   public Book(){
   
   }
   
   public Book(String id, String txt){
      this.id = id;
      this.txt = txt;
   }
   
   public Book(String id){
      this.id = id;
   }
   
   public void setId(String id){
      this.id = id;
   }
   public String getId(){
      return id;
   }
   public void setTxt(String txt){
      this.txt =txt;
   }
  
   public String getTxt(){
      return txt;
   }
  
   @Override
   public String toString(){
      return id + ":" + txt;
   }
   public boolean contains(ArrayList<Book> list, String id){
      boolean flag = false;
      for(Book x: list){
         if(x.getId().equals(id)){
            flag = true;
         }
      }
      return flag;
   }
 
}