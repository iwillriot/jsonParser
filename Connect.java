/*
AUTHOR: WILLRIOT
THIS PROGRAM WAS WRITTEN TO HANDLE JSON_ENCODES FROM PHP
*/
import java.net.*;
import java.io.*;
import java.util.*;
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
         //json_encode comes in as one string of text.
         while ((inputLine = in.readLine()) != null){
            toma = inputLine;
            if(toma.equals("0 results[]")){
               System.out.println("0 results");
               System.exit(1);
            }   
         }
         in.close();
      }
      catch(ArrayIndexOutOfBoundsException e){
         System.out.println("AOOB Error: " + e);
         System.exit(1);
      }
      catch(IOException e){
         System.out.println("Connection Error:" + e);
         System.exit(1);
      }
     
      toma = strip(toma);
      
      String[] s = toma.split("}");
      String build = "";
      
      for(String x: s){
         build = build + x;
      }
      String[] g = build.split(",");
      
      ArrayList<Book> books = new ArrayList<>();
      
      int count = 0;
      
      for(String i: g){
         //array to take in jsonArray
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
         System.out.println(x.toString());
      }
   }
   
   public static String strip(String s){
      s = s.replaceAll("\\[","");
      s = s.replaceAll("\\]","");
      s = s.replaceAll("\\{","");
      s = s.replaceAll("\"","");
      return s;
   }
}

class Book{
  //global variables
   private String id;
   private String txt;
   
   public Book(){
   //empty constructor
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
}