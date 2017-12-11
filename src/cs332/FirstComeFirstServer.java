package cs332;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.management.Query;

public class FirstComeFirstServer implements Runnable{

	int processtime[] = new int[3];
	String pid[] = new String[3];
	int arrivalTime[] = new int[3];
	private static ArrayList<Process> array;

	
	public FirstComeFirstServer(ArrayList<Process> arraylist)
	   {
		   this.array = arraylist;
	   }
	
	@Override
	public void run() {
		
		for(int i=0;i<array.size();i++)
		{
			//System.out.println("In FCFS \n");
//			System.out.println("------------------------ \n");
			System.out.println("Process ID " + array.get(i).getId() );
			System.out.println("Process Arrival Time " + array.get(i).getarrival_time());
			System.out.println("Process Time " + array.get(i).getprocess_time());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \n");


		}
		
	}
	
	

}
