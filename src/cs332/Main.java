package cs332;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main 
{

	public static void main(String[] args) throws FileNotFoundException
	{
		File file = new File("C:\\Users\\kamranpc\\Documents\\CS 332\\Project\\Process.csv"); // creates new File with the name of the file I created
	    ArrayList<Process> arraylist = new ArrayList<Process>();
	    Queue queue = new LinkedList();
	    Scanner scan = new Scanner(file);
	    while(scan.hasNextLine()) //read until there are no more lines in the file
	       {
	          String eachline; //variable to hold every line
	          eachline = scan.nextLine(); //variable holds every line
	          //System.out.println(eachline + " " + "\n");
	          String[] vars = eachline.split(","); //seperate everything by commas
	          String id;
	          int arrival_time, process_time;
	          Process process = new Process();
	          id = vars[0];
	          arrival_time = Integer.parseInt(vars[1]);
	          process_time = Integer.parseInt(vars[2]);
	          process.setId(id);
	          process.setprocess_time(process_time);
	          process.setarrival_time(arrival_time);
//	          Process process = new Process(
//	        		  				vars[0], 
//	        		  				Integer.parseInt(vars[1]), 
//	        		  				Integer.parseInt(vars[2]));
	          
	          arraylist.add(process);
	          
       }
	   Thread t1 = new Thread(new round_robin(arraylist));
	   Thread t2 = new Thread(new FirstComeFirstServer(arraylist));
	   t1.start();
	   //t2.start();
	}
}

