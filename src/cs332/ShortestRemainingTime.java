package cs332;

import java.util.ArrayList;


public class ShortestRemainingTime implements Runnable
{

	int process_time[] = new int[3];
	String pid[] = new String[3];
	int arrival_time[] = new int[3];
	int remaining_process_time[] = new int[3];
	int remaining = 0, time =0, smallest;

	private static ArrayList<Processback> array;

	//this constructor initilizes the variables described above.
	public ShortestRemainingTime(ArrayList<Processback> arraylist)
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
