
public class Task implements Comparable<Task> {
	private int id;
	private int time;
	
	public Task(int id, int time) {
		this.id = id;
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getID() {
		return id;
	}

	@Override
	public int compareTo(Task task) {
		if(this.getTime()>task.getTime()) {
			return 1;
		}else if(this.getTime()<task.getTime()) {
			return -1;
		}
		if(this.id<task.id) {
			return 1;
		}else {
			return -1;
		}
	}
	
	
	
}
