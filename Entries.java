import java.util.ArrayList;

public class Entries {
   
   public static int numEntries = 0;

   //public instance varibale
   private String name;
   private int distance; //will change this to double later2
   private int elevation;
   private int time; 
   private char gender;
   private int age;
   private int height;
   private int weight;
    
   
   //default
   public Entries() {
      name = "";
      //distance = 1.1;
      distance = elevation = time = 0;
      gender = 'a';
      age = height = weight = 0;
      numEntries++;
   }
   
   //overloaded
   public Entries(String word, int d, int e, int t, char x, int a, int h, int w) { 
      name = word;
      distance = d;
      elevation = e;
      time = t;
      gender = x;
      age = a;
      height = h;
      weight = w;
      numEntries++;
   }
   
   //getter and setter
   public void setName(String word) {
        name = word;
   } public String getName() {
         return name;
   }
   
   public void setDistance (int num) {
         distance = num;
   } public int getDistance() {
         return distance;
   }
         
   public void setElevation (int num) {
         elevation = num;
   } public int getElevation() {
         return elevation;
   }
         
   public void setTime (int num) {
         time = num;
   } public int getTime() {
         return time;
   }    
   
   public void setGender (char letter) {
      gender = letter;
   } public char getGender() {
      return gender;
   }
   
   public void setAge (int num) {
         age = num;
   } public int getAge() {
         return age;
   }
         
   public void setHeight (int num) {
         height = num;
   } public int getHeight() {
         return height;
   }
         
   public void setWeight (int num) {
         weight = num;
   } public int getWeight() {
         return weight;
   }

   /*public static String search(String thing) {
      for (i = 0; i < numEntries; i++) {
         
      }
   } //end search*/
   
}//end class