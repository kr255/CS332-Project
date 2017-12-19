package cs332;

import java.util.ArrayList;

public class round_robin implements Runnable {

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
	public round_robin(ArrayList<Processback> arraylist)
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
