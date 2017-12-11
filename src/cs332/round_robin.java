package cs332;

import java.util.ArrayList;

public class round_robin implements Runnable {

	 private static int completion_time[] = new int[3];
	 private static ArrayList<Process> array;
	 private static int process_time[] = new int[3]; 
	 private static int wait_time[] = new int[3];
	 private int remaining_process_time[] = new int[3];
	 private static int turnaround_time[] = new int[3];
	 private static int[] arrival_time = new int[3];
	 private int quantum = 2;
	   // constructor 
	   public round_robin(ArrayList<Process> arraylist)
	   {
	       round_robin.array = arraylist;
			for(int i=0;i<array.size();i++)
			{
			    int temp= array.get(i).getprocess_time();
			    process_time[i] = temp;
			    remaining_process_time[i] = process_time[i];
			    wait_time[i] = 0;
			   
				arrival_time[i] = array.get(i).getarrival_time();
	
			}
	   }
	   
		public int[] getCompletion_time() {
				return completion_time;
			}

		public static void setCompletion_time() 
		{
			//this.completion_time = completion_time;
			for(int i=0;i<array.size();i++)
			{
				completion_time[i] = turnaround_time[i] + arrival_time[i];
			}
		}

		public int[] getWaiting_time() {
				//return waiting_time;
				return wait_time;
			}

		private void setWaiting_time() {
				//this.waiting_time = getTurn_around_time() - getprocess_time();
				int currenttime = 0;
				while(true)
				{
					boolean finished = true;
					for(int i=0;i<array.size();i++)
					{
						//System.out.println("remaining process time in the fuc " + remaining_process_time[i] + "");
						if(remaining_process_time[i] > 0)
						{
							finished = false;
							if(remaining_process_time[i] > quantum)
							{
								currenttime+=quantum;
								remaining_process_time[i]-=quantum;
							}
							else
							{
								currenttime = currenttime + remaining_process_time[i];
								wait_time[i] = currenttime - (process_time[i] - arrival_time[i]);
								remaining_process_time[i] = 0;
							}
						}

					}
					if(finished == true)
					{
						break;
					}
				}
			}

		public int[] getTurnaround_time() {
				return turnaround_time;
			}

		public static void setTurn_around_time() {
			//this.turn_around_time = getCompletion_time() - getarrival_time();
			for(int i=0; i<array.size();i++)
			{
				turnaround_time[i] = process_time[i] + wait_time[i];
			}
		}
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		//System.out.println("printing?????");
		setTurn_around_time();
		setWaiting_time();
		setCompletion_time();

		 
		for(int i=0;i<array.size();i++)
		{
			 System.out.println("------------------------ \n");
			 //System.out.println("completion time " + completion_time[i]);
			 System.out.println("Process ID " + array.get(i).getId());
			 System.out.println("process time is " + " " + process_time[i]);
			 System.out.println("turnaround time is " + " " + getTurnaround_time()[i]);
		     System.out.println("waiting time is " + " " + getWaiting_time()[i]);
		     System.out.println("completion time is " + " " + getCompletion_time()[i] + "\n");
		     System.out.println("------------------------ \n");
		}
		
	}

}
