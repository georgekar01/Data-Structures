
public class Sort {
	
	public static Task[] heapSort(Task[] taskArray) {  
		int tasks = taskArray.length;
		
		if(tasks==0) {
			return null;
		}
		
		for(int i=(tasks/2)-1; i>=0; i--) {
			heapify(taskArray,tasks,i);
		}
		
		for(int i=tasks-1; i>=0; i--) {
			swap(taskArray,0,i);
			heapify(taskArray,i,0);
		}
		return taskArray;  
	}
	
	public static void heapify(Task[] taskArray, int tasks, int i) {
		int rootNode = i;
		int left = 2*i+1;
		int right = 2*i+2;
		
		if(left<tasks && taskArray[left].compareTo(taskArray[rootNode])<0) {
			rootNode = left;
		}
		
		if(right<tasks && taskArray[right].compareTo(taskArray[rootNode])<0) {
			rootNode = right;
		}
		
		if(rootNode!=i) {
			swap(taskArray,i,rootNode);
			heapify(taskArray, tasks, rootNode);
		}
	}

	private static void swap(Task[] taskArray, int i, int rootNode) {
		Task temp = taskArray[i];
		taskArray[i] = taskArray[rootNode];
		taskArray[rootNode] = temp;
	}

	
	/*
	public static void main(String[] args) {
	
		Task task1 = new Task(12,25);
		Task task2 = new Task(1,30);
		Task task3 = new Task(32,20);
		Task task4 = new Task(128,35);
		Task task5 = new Task(4,50);

		
		
		Task[] taskArray= {task1,task2,task3,task4,task5};
		
		heapSort(taskArray);
		
		for(int i=0; i<5; i++ ) {
			System.out.println(taskArray[i].getTime());
		}
		
	}
	*/
	
}
