
public class Processor implements Comparable<Processor>{
	private int id;
	QueueInterface<Task> processedTasks;
	private int elapsedTime;
	
	public Processor(int id){
		this.id = id;
		elapsedTime = 0;
		
		processedTasks = new QueueImpl<Task>();
	}
	
	public void addCompletedTask(Task task) {
		//processedTasks.add(task);
		processedTasks.put(task);
		elapsedTime += task.getTime();
	}
	
	public int getID() {
		return id;
	}
	
	public int getActiveTime() {
		return elapsedTime;
	}
	
	public String toString() {
		String stringBuild = "";
		int lim = processedTasks.size(); 
		for(int i=0; i<lim; i++) {
			stringBuild += processedTasks.get().getTime()+" ";
		}
		return stringBuild;
	}
	
	public int compareTo(Processor processor) {
		if(this.getActiveTime()>processor.getActiveTime()) {
			return 1;
		}else if(this.getActiveTime()<processor.getActiveTime()) {
			return 2;
		}
		if(this.id<processor.id) {
			return 1;
		}else {
			return 2;
		}
	}
	
	
}
