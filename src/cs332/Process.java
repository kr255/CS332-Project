package cs332;

public class Process {

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
