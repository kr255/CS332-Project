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
//first come first serve implementation 
class FirstComeFirstServer implements Runnable
	{

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
		//outer loop to loop over each process
		for(int i=0;i<array.size();i++)
			{
				//foreach process display the id for the processing time
				for(int j=0;j<array.get(i).getprocess_time();j++)
				{
					System.out.println("Process ID " + array.get(i).getId() );
				}
	
			}

	}
		
}
//round robin implementation 	
class round_robins implements Runnable {

		int process_time[] = new int[3];
		String pid[] = new String[3];
		int arrival_time[] = new int[3];
		int remaining_process_time[] = new int[3];
		int remaining = 0;
		int endtime;
		int quantum = 3;
		int init = 0;
		private static ArrayList<Processback> array;

		//this constructor initilizes the variables described above.
		public round_robins(ArrayList<Processback> arraylist)
		   {
			   this.array = arraylist;
			   for(int i=0;i<array.size();i++)
				{
				    int temp= array.get(i).getprocess_time(); //setting the temp with process time
				    process_time[i] = temp;
				    remaining_process_time[i] = process_time[i]; //setting the remanining process time the same as service 
					arrival_time[i] = array.get(i).getarrival_time(); //setting arrival time
					pid[i] = array.get(i).getId(); //setting Process Id
		
				}
		   }
		
		@Override
		//main run method of the Runnaable interface
		public void run() 
		{
			// initial count for the number of processes
			while(init <= array.size())
			{
				init++;
				// here  i am incrementing for each process
				for(int i=0;i<array.size();i++)
				{
					//if the remaining time of the first process is less than or equal to zero
					//then we continue down
					if(remaining_process_time[i] <= 0)
					{
						continue;
					}
					//if the remaining time of the i'th process is greater than the quantum we
					//go in an additional loop and print the id out quantum time, since thats how the algo
					//needs to process a process.
					//then the i'th remaining process time is subtracted with the quantum
					if(remaining_process_time[i]>quantum)
					{
						for(int j=0;j<quantum;j++)
						{
							System.out.println("Process ID " +  array.get(i).getId());
						}
						remaining_process_time[i] = remaining_process_time[i] - quantum;

					}
					//else if the process time is less than quantum another loop,
					//this time running until the process time displays the id
					//the problem here is that it doesnt queue correctly, I couldnt figure out how.
					else if(remaining_process_time[i] < quantum)
					{
						for(int k=0;k<remaining_process_time[i];k++)
						{
							System.out.println("Process ID " +  array.get(i).getId());
						}
						remaining_process_time[i] = remaining_process_time[i] - quantum;
						

					}
					//if none is true than the remaining process time is subtracted by itself.
					else
					{
						remaining_process_time[i] = remaining_process_time[i] - remaining_process_time[i];
					}
				}
			}
		}
}
//SRT implementation the s is so it doesnt trow an error
class ShortestRemainingTimes implements Runnable
{

	int process_time[] = new int[3];
	String pid[] = new String[3];
	int arrival_time[] = new int[3];
	int remaining_process_time[] = new int[3];
	int remaining = 0, time =0, smallest;

	private static ArrayList<Processback> array;

	//this constructor initilizes the variables described above.
	public ShortestRemainingTimes(ArrayList<Processback> arraylist)
	   {
		   this.array = arraylist;
		   for(int i=0;i<array.size();i++)
			{
			    int temp= array.get(i).getprocess_time();
			    process_time[i] = temp;
			    remaining_process_time[i] = process_time[i];
				arrival_time[i] = array.get(i).getarrival_time();
				pid[i] = array.get(i).getId();
	
			}
	   }
	
	@Override
	//main run method of the Runnaable interface
	public void run() 
	{
		// I am attempting to figure out the incrementation of time here for every process but i think it doesnt work
		for(time=0;remaining!=array.size();time++)
	    {
	        smallest=2; //setting the last index of the array to compare process times with 
	        for(int i=0;i<array.size();i++)
	        {
	        	//foreach i'th interval i am trying to see if the arrival time is less than equal to time, and if 
	        	//the remaining process time is less than the last one. Even tho i should not for the first iteration
	        	//if the conditions meet than turning smallest into the current remaining time. 
	            if(arrival_time[i]<= time && remaining_process_time[i]<remaining_process_time[smallest]
	            						 && remaining_process_time[i]>0)
	            {
	                smallest=i;
	                System.out.println("process ID "+ array.get(i).getId());
	            }
	            //on the other hand if the remaining process time is bigger than the smallest than doing the same as above
	            else if(arrival_time[i]<= time && remaining_process_time[i]>remaining_process_time[smallest] && remaining_process_time[i]>0)
				{
				   smallest=i;
				   System.out.println("process ID "+ array.get(i).getId());
				}

	        }
	        //after the first whole iteration, reducing the remaining time of the smallest variable 
	        remaining_process_time[smallest]--;
	        //checking here if the remaining time is zero than incrementing the initial check, 
	        // hopefully going to the next process. But it doesnt work, it prints three A's instead of 2
	        if(remaining_process_time[smallest]==0)
	        {
	            remaining++;
	        }
	
	    }

	}
}	

public class Main 
{

	public static void main(String[] args) throws FileNotFoundException
	{
		String path = args[0]; // path variable since it had to be a command line argument
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
	          Processback process = new Processback(); // creates a new process object (named that way because of class collision)
	          id = vars[0]; //setting up id
	          arrival_time = Integer.parseInt(vars[1]); //setting up arrival time
	          process_time = Integer.parseInt(vars[2]); //setting up process time
	          process.setId(id); //assigning id 	
	          process.setprocess_time(process_time);//assigning process time
	          process.setarrival_time(arrival_time);//assigning arrival time

	          
	          arraylist.add(process);
	          
       }
	    
	   //thread creation for each different algorithm to run simultaneously  
	   Thread t1 = new Thread(new FirstComeFirstServer(arraylist)); 
	   Thread t2 = new Thread(new ShortestRemainingTimes(arraylist));
	   Thread t3 = new Thread(new round_robins(arraylist));
	   t3.start();
	   //t2.start();
	}
}

