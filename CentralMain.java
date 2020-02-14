import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.text.DecimalFormat;
import java.io.PrintWriter;
import java.io.FileWriter;

public class CentralMain 
{
   //doubles are formatted to the hundreds place
   static DecimalFormat df = new DecimalFormat("#####.##");
   static File inputFile;
   static PrintWriter pw;
   static PrintWriter pwf;
   static Scanner sFile;
   static Scanner sKeyboard;
   static ArrayList<Entries> e;
   static Entries entry;
   
   public static void main (String[] args) throws Exception {  
	   inputFile = new File("list.txt");
	   pw = new PrintWriter(new FileWriter (inputFile, true));
	   sFile = new Scanner(inputFile);
	   sKeyboard = new Scanner(System.in);
	   e = new ArrayList<Entries>(); 
      
      String response = "x";
      //MENU is always returned to unless 'x' or 'X' is inputted
      while (!response.equals("X") || !response.equals("x")) {
      
      //Reads text file and creates objects from contents and arraylist of those objects
      while (sFile.hasNext()) {
         ReadObjects();     
         e.add(entry);
      
      } //end while
      
      System.out.println("----------------[MAIN MENU]----------------");
      System.out.println("\n[1]New entry");
      System.out.println("[2]Load entries");
      System.out.println("[3]Edit an entry");      
      System.out.println("[4]Delete an entry");
      System.out.println("[X]Exit Program");
      
      System.out.print("\nPlease enter a menu option number/letter: ");
      response = sKeyboard.next();
      
      //Different functions for different option number
      switch (response)  {
         case "1": Input();
         break;
         case "2": Load();
         break;
         case "3": Edit();
         break;
         case "4": Delete();
         break;
         default: System.out.println("That is not an option, Try again");
         break;
         case "x":
         return;
         case "X":
         return;
       } //end switch
       
      } //end while
   }//end main method

//method for user to input new data to create objects which stores the new objects as data in a text file   
   public static void Input() throws Exception {
      
      char sentinel = 'y';
      
      //User inputs data
      while (sentinel == 'Y' || sentinel == 'y') {

         System.out.print("\nEnter the location name (Do not include spaces):  ");
         String name = sKeyboard.next();   
               
         System.out.print("Enter a distance (miles): ");
         int distance = sKeyboard.nextInt();
         
         System.out.print("Enter an elevation gain (ft): ");
         int elevation = sKeyboard.nextInt();
         
         System.out.print("Enter a time (hours): ");
         int time = sKeyboard.nextInt();
         
         System.out.print("Enter a gender (F or M):");
         char gender = sKeyboard.next(".").charAt(0);
         
         System.out.print("Enter an age (years): ");
         int age = sKeyboard.nextInt();
         
         System.out.print("Enter a height (inches): ");
         int height = sKeyboard.nextInt();
         
         
         System.out.print("Enter a weight (lbs.): ");
         int weight = sKeyboard.nextInt();
         
         //inputted data added onto object constructors to create objects
         entry = new Entries(name, distance, elevation, time, gender, age, height, weight);
         e.add(entry); //each new object added onto ArrayList e
         
         //Writes constructors into text file
         pw.print(name + " " + distance + " " + elevation + " " + time + " " + gender + " " + age + " " + height + " " + weight + "\n");
         
         System.out.print("\nWould you like add another entry (Y or N)?");
         sentinel = sKeyboard.next(".").charAt(0);
         } //end while
         pw.close();   
     
      System.out.println("\n~~~New entries added~~~\n\n(To view them, please select [2]Load Entries from the main menu)\n");

   } //end method

//method have user choose to load all entries, or search for an entry using the name of the location of that entry    
   public static void Load() throws Exception {
	   
      ArrayList<String> SearchStr = new ArrayList<String>();
      ReadToString(SearchStr);
   
      System.out.println("\n[0]Load all entries\n[1]Search for an entry\n");
      System.out.print("Please enter the number of your desired action: ");
      int temp = sKeyboard.nextInt();
      switch (temp) {
      //if (temp == 0) {
   	case 0:for(Entries a: e) {
           		 PrintResults(a);
             }
   	break;
   	case 1:			 
      
         System.out.println("\n\n~~~Search for the name of your entry~~~\n\n[Please enter the name of the location of your desired entry (No spaces)] ");
         System.out.print("\nSearch: ");
         String search = sKeyboard.next();
     
         boolean searchTest = true;    
		
         for (int i = 0; i < SearchStr.size(); i++) {
   		 
   			//String test takes the string from the first character to the first space
            String test = SearchStr.get(i).substring(0, (SearchStr.get(i).indexOf(" ")));
            
   			//if string searched is equal to the entry location taken by String test, the print that entry
           	if (search.equals(test)) {
   			   PrintAnEntry(i);
               searchTest = false; 		   	
   			} 
         } //end for
   		
         //lets user know that their search was unsuccessul
   		 if (searchTest) System.out.println("\n~~~Sorry, the item you searched for is not found~~~\n");
   	break;
   	default: System.out.println("\n~~~That is not an option. Try again~~~\n");
   	return;
      } //end switch      
   } //end method
   
//method to edit a parameter of Object Entries, also edits and updates the corresponding data in text file
   public static void Edit() throws Exception {
         
         //char sentinel = 'y';
         
         //print only name and time of each entry
         for (Entries a: e) {
         	System.out.println("________________________________________________________"); 
            System.out.println("\n			Entry #" + (e.indexOf(a) + 1) + ":");
            System.out.println("Location: " + a.getName() + "\n" + "Distance: " + a.getDistance() + "\n" + "Elevation: " + a.getElevation() + "\n" + "Duration: " + a.getTime() + " hours" + "\n" + "Gender: " + a.getGender() + "\n" + "Age: " + a.getAge() + " years" + "\n" + "Height: " + a.getHeight() + " in." + "\n" + "Weight: " + a.getWeight() + " lbs." + "\n");            
            System.out.println("________________________________________________________");
         }
         
          //prompts user to select entry to edit 
         System.out.println("\n~~~Which entry would you like to edit?~~~");
         System.out.print("Please enter the number of the desired entry (Indicated by 'Entry #X') : ");
         int selection = sKeyboard.nextInt();
         //while (sentinel == 'Y' || sentinel == 'y') {
       	  
         //User selects object constructor (Using .split() and String array line[]) to edit
         System.out.println("\n~~~What do you want to update?~~~\n");
         for (int i = 0; i < e.size(); i++) {
        	 if (i == (selection - 1) ) {
        		 Entries x = e.get(i);
        		 System.out.println("[0]Location: " + x.getName() + "\n" + "[1]Distance: " + x.getDistance() + " miles\n" + "[2]Elevation: " + x.getElevation() + " ft.\n" + "[3]Duration: " + x.getTime() + " hours" + "\n" + "[4]Gender: " + x.getGender() + "\n" + "[5]Age: " + x.getAge() + " years" + "\n" + "[6]Height: " + x.getHeight() + " in." + "\n" + "[7]Weight: " + x.getWeight() + " lbs." + "\n");
        	 }
         }
         //PrintAnEntry(selection - 1);
         //System.out.println("\n[0]Name [1]Distance [2]Elevation [3]Time [4]Gender [5]Age [6]Height [7]Weight");
         System.out.print("\nPlease enter the number of the attribute you wish to update (Indicated by [X]): ");
         int index = sKeyboard.nextInt();
         
         //Prommpts user to input edited info
         System.out.println("\n~~~What would you like to replace it with?~~~");
         
         //Prompt varies according to the data type that the user requests
         if (index == 0) {
            System.out.print("Please enter a new location name (Without any spaces): ");
         } else if (index == 4) {
            System.out.print("Please enter new information (M of F?): ");
         } else {
            System.out.print("Please enter a new value: ");
         }
         
         //Prompts user to edit the data
         String edit = sKeyboard.next();
         ArrayList<String> list = new ArrayList<String>();
         ReadToString(list);

         for (int i = 0; i < list.size() + 1; i++) {
            if (i == selection) {
               String[] line = list.get(i - 1).split(" ");
               
               for (int j = 0; j < line.length; j++) {
                  if (j == index) {
            
                     //replaces the selected parameter and replaces with user entry
                     line [j] = line[j].replace(line[j], edit);
               
                     //Creates new object of updated parameters
                     Entries edited = new Entries(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), line[4].charAt(0), Integer.parseInt(line[5]), Integer.parseInt(line[6]), Integer.parseInt(line[7]));
               
                     //replaces edited entry into object ArrayList
                     e.set((selection - 1), edited);

                  }//end if2
                } //end for2
                
            }//end if1
         } //end for1
                     
         //printwriter is false so that the text file can be overwritten and 'updated'                  
         pwf = new PrintWriter(new FileWriter(inputFile, false));
         for (Entries a: e) {
            pwf.write(a.getName() + " " + a.getDistance() + " " + a.getElevation() + " " + a.getTime() + " " + a.getGender() + " " + a.getAge() + " " + a.getHeight() + " " + a.getWeight() + "\n");
         }
         
         pwf.close();
         System.out.println("\n~~~Entry edited~~~\n");
      } //end method
 
//method to delete an entry from ArrayList e, also updates the and overwrites the file with updated ArrayList   
   public static void Delete() throws Exception {

	  
      System.out.println("\nDelete one entry or delete all entries?\n");
      System.out.print("[1]Delete one entry\n[2]Delete all entries\n\nPlease enter the number of your desired action: ");
      int response = sKeyboard.nextInt();
      
      switch (response) {
      case 1: 
    	//prints entries by location and time            
          for (Entries a: e) {   
             System.out.println("\nEntry #" + (e.indexOf(a) + 1) + ":");
             System.out.println("Location: " + a.getName() + "\nDuration: " + a.getTime() + " hours");      
          }
    	  
    	  //prompts user to select entry
          System.out.print("\n~~~Which entry would you like to delete?~~~\n\nPlease enter the number of your desired entry: ");
          int index = sKeyboard.nextInt();
          
          //Delete selected entry from Entry ArrayList
          for (int i = 0; i < e.size(); i++) {
             if (i == (index - 1)) {
                e.remove(i);
             } //end if  
          } //end for
          System.out.println("\n~~~Entry deleted~~~\n");
      break;
      case 2: 
    	  System.out.print("Are you sure you want to clear all entries? \nEnter 'Yes' or 'No' : ");
    	  String verify = sKeyboard.next();
    	  
    	  if (verify.equals("yes") || verify.equals("Yes")) {
    		  e.clear();
    		  System.out.println("Your list is cleared.");
    	  } else {
    		  System.out.println("\n~~~You're safe! Nothing is deleted~~~\n");
    	  }
      	  
      break;
      default:
      return;
      
      } //end switch
      
      pwf = new PrintWriter(new FileWriter(inputFile, false));
      //writes updated ArrayList of entries onto the file using the overwriting PrintWriter
          for (Entries al: e) {
             pwf.write(al.getName() + " " + al.getDistance() + " " + al.getElevation() + " " + al.getTime() + " " + al.getGender() + " " + al.getAge() + " " + al.getHeight() + " " + al.getWeight() + "\n");
          }
          pwf.close();
      
      
   } //end method*/

//choose an entry to print using index of ArrayList e
   private static void PrintAnEntry (int temp) {
      for (int i = 0; i < e.size(); i++) {   
         if (i == temp) {
               PrintResults(e.get(temp));
         } //end if
      } //end for
   } //end private method
   
//print the entry including the calculations of BMR, Activity Multiplier, total cal burned, and macros ratio
   private static void PrintResults(Entries a) {
         ArrayList<Double> bmr = new ArrayList<Double>();
         ArrayList<Double> am = new ArrayList<Double>();
         ArrayList<Double> total = new ArrayList<Double>();
            System.out.println("________________________________________________________");
            System.out.println("\n			Entry #" + (e.indexOf(a) + 1) + ":");
            System.out.println("Location: " + a.getName() + "\n" + "Distance: " + a.getDistance() + " miles\n" + "Elevation: " + a.getElevation() + " ft.\n" + "Duration: " + a.getTime() + " hours\n" + "Gender: " + a.getGender() + "\n" + "Age: " + a.getAge() + " years\n" + "Height: " + a.getHeight() + " in.\n" + "Weight: " + a.getWeight() + " lbs.\n");
            bmr = findBMR(a); 
            am = findAM(a, bmr);
            total = findTotal(bmr, am);
            //int days = findTM(a);
            findRatio(total);
            System.out.println("________________________________________________________"); //entry divider
            
   } //end private method
   
//private method to read contents of text file into arraylist of string
   private static ArrayList<String> ReadToString (ArrayList<String> al) throws Exception {
      
      sFile = new Scanner(inputFile);
      
      //Reads the file and creates a String arraylist of the entries
      while (sFile.hasNextLine()) {
         String read = sFile.nextLine();
         al.add(read); 
      } //end while
      
      return al; 
   } //end private method
   
//read the file and inputs contents into ArrayList e   
   private static void ReadObjects() {
      
         String name = sFile.next();   
         int distance = sFile.nextInt();
         int elevation = sFile.nextInt();
         int time = sFile.nextInt();
         char gender = sFile.next(".").charAt(0);
         int age = sFile.nextInt();
         int height = sFile.nextInt();
         int weight = sFile.nextInt();
         
         //inputted data added onto object constructors to create objects
         entry = new Entries(name, distance, elevation, time, gender, age, height, weight);
       
   } //end private method
   
//calculates an entry's basal metabolic rate using its gender, age, height, and weight
   private static ArrayList<Double> findBMR (Entries x) { //char g, int a, int h, int w) {
      ArrayList<Double> bmrList = new ArrayList<Double>();
      double bmr = 0.0;

      char g = x.getGender();
      int a = x.getAge(); //years
      int h = x.getHeight(); //inches 
      int w = x.getWeight(); //pounds 

      //Seperate formulas by gender for calculating Basal Metabolic Rate
      if (g == 'M' || g == 'm') {
         bmr = (66 + (6.23 * w) + (12.7 * h) - (6.8 * a));
      } else if (g == 'F' || g == 'f') {
         bmr = (655 + (4.35 * w) + (4.7 * h) - (4.7 * a));       
      } else {
         System.out.println("\nThat is not a gender. Try again");
      }
         
      System.out.printf("BMR: %.2f cal/day\n", bmr);
      bmrList.add(bmr);
         
      return bmrList;
   }  //end method
   
//calculates additional calories burned through backpacking
   private static ArrayList<Double> findAM (Entries x, ArrayList<Double> bmr) {
      ArrayList<Double> amList = new ArrayList<Double>();
      double am = 0.0;

     //for (Entries p: temp) {
      int d = x.getDistance();
      int el = x.getElevation(); //years
      double num = Math.sqrt((d*2*el));
      //System.out.println(num);
      
      //Estimating tool for calculating activity multiplier using numerical value from distance and elevation gain
      if (num < 50) {
    	  am = 1.2;
      } else if (num <= 50) {
    	  am = 1.375;
      } else if (num <= 100 ) {
    	  am = 1.55;
      } else if (num <= 150) {
    	  am = 1.725;
      } else {
    	  am = 1.9;
      } //end if
      
      
      System.out.printf("%.3f activity multiplier\n", am);
      amList.add(am);
         
      //} //end for
      return amList;
   
   } //end method
   
   /*private static int findTM (Entries x) {
	   int t = x.getTime(); //inches 
	   int dayCount = 1;
	   
	   for (int i = 1; i <= t; i++) {
		   if (i % 24 == 0) {
			   dayCount++;
		   }
	   }
	   
	   return dayCount;
	      
   }*/
private static ArrayList<Double> findTotal (ArrayList<Double> a, ArrayList<Double> b) {
      
      ArrayList<Double> result = new ArrayList<Double>();
      double product = 0.0;
      
      for (int i = 0; i < a.size(); i++) {
    	 product = a.get(i) * b.get(i);
         result.add(product);
      }
      
      System.out.printf("TOTAL CALORIES BURNED: %.2f cal/day\n\n", product);
      return result;
      
   } //end private method

//applies the macronutrient ratio to the total calorie intake
   private static ArrayList<Double> findRatio (ArrayList<Double> total) {
	   
	  System.out.println("~~~Macronutritients Ratio~~~");
      ArrayList<Double> rat = new ArrayList<Double>();

      for (int i = 0; i < total.size(); i++) {
         double value = total.get(i);
         double protien = value * 0.30;
         double carbs = value * 0.40;
         double fats = value * 0.30;
         
         rat.add(protien);
         rat.add(carbs);
         rat.add(fats);
         
         System.out.println("\nProtiens (30%): " + df.format(protien) + " cal/day");
         System.out.println("Carbs (40%): " + df.format(carbs) + " cal/day");
         System.out.println("Fats (30%): " + df.format(fats) + " cal/day\n");
    
      } //end for
      
      return rat;
   } //end private method
   
} //end class         