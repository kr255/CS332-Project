package cs332;
import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// base java class for a single process 
class Process {

	 private String id;
	 private int arrival_time;
	 private int process_time;
	
	 
	 public Process() 
	 {
	 }
	 
	 public Process(String id, int timerequire, int process_time)
	   {
	      this.id = id;
	      this.arrival_time = timerequire;
	      this.process_time = process_time;
	   }	 
	 


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getarrival_time() {
		return arrival_time;
	}

	public void setarrival_time(int arrival_time) {
		this.arrival_time = arrival_time;
	}

	public int getprocess_time() {
		return process_time;
	}

	public void setprocess_time(int process_time) {
		this.process_time = process_time;
	}
	 
}
	
	class FirstComeFirstServer implements Runnable{

	int processtime[] = new int[3];
	String pid[] = new String[3];
	int arrivalTime[] = new int[3];
	private static ArrayList<Processback> array;

	//this constructor initilizes the variables described above.
	public FirstComeFirstServer(ArrayList<Processback> arraylist)
	   {
		   this.array = arraylist;
	   }
	
	@Override
	//main run method of the Runnaable interface
	public void run() 
	{
		
		for(int i=0;i<array.size();i++)
			{
	
				for(int j=0;j<array.get(i).getprocess_time();j++)
				{
					System.out.println("Process ID " + array.get(i).getId() );
				}
	
			}

	}
		
}
public class Main 
{

	public static void main(String[] args) throws FileNotFoundException
	{
		String path = args[0];
		//System.out.println("PATH: " + path);
		File file = new File(path); // creates new File with the name of the file I created
	    ArrayList<Processback> arraylist = new ArrayList<Processback>();
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
	          Processback process = new Processback();
	          id = vars[0];
	          arrival_time = Integer.parseInt(vars[1]);
	          process_time = Integer.parseInt(vars[2]);
	          process.setId(id);
	          process.setprocess_time(process_time);
	          process.setarrival_time(arrival_time);

	          
	          arraylist.add(process);
	          
       }
	   Thread t1 = new Thread(new FirstComeFirstServer(arraylist));
	   Thread t2 = new Thread(new ShortestRemainingTime(arraylist));
	   Thread t3 = new Thread(new round_robin(arraylist));
	   t2.start();
	   //t2.start();
	}
}

