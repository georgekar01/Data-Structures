import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Greedy {
	
	public static Task[] createTaskArray(String path) throws IOException{   
		String fileName = path; 
		Scanner scanner = new Scanner(new File(fileName));
		int lineCount = (int) Files.lines(Paths.get(fileName)).count();
		Task[] taskArray = new Task[lineCount-2];        
		Task task;
		for(int i=0; i<lineCount; i++) {
			if(i<=1) {
				scanner.nextLine();
				continue;
			}
			
			String curLine = scanner.nextLine();
			task = new Task(Integer.parseInt(curLine.split(" ")[0]),Integer.parseInt(curLine.split(" ")[1]));
			taskArray[i-2] = task;
		}
		
		return taskArray;
	}
	
	/*
	public static int main(String[] args) throws IOException {
		String fileName = "test.txt";		//for cmd 
		//String fileName = args[0];
		Scanner s = new Scanner(new File(fileName));
		Scanner n = new Scanner(new File(fileName));
		
		PQInterface pq = new MaxPQ(new TimeComparator());
        String processors = Files.readAllLines(Paths.get(fileName)).get(0);  
        Processor processor;
        for(int i=0; i<Integer.parseInt(processors); i++) {
        	processor = new Processor(i+1);
        	pq.insert(processor);
        }
        
        String tasks = Files.readAllLines(Paths.get(fileName)).get(1);
        int countCheck = 0;
        while (s.hasNext()) {
        	countCheck++;
        	if(s.nextLine()==null) {
        		break;
        	}
        }
        if((countCheck-2)!=Integer.parseInt(tasks)) {
        	System.err.println("ERROR");
        	return 0;     
        }
        
        int flag = 0;
        int max = -1;
        Processor maxProcessor;
        int makespan = 0;
        while (n.hasNext()) {
        	String curLine = n.nextLine();
        	if(curLine!=null && flag<2) {
        		flag++;
        		continue;
        	}
        	
        	int id = Integer.parseInt(curLine.split(" ")[0]);
        	int timeNeeded = Integer.parseInt(curLine.split(" ")[1]);
        	Task task = new Task(id, timeNeeded);
        	Processor curProcessor = pq.getMax();
        	curProcessor.addCompletedTask(task);
        	
        	if(curProcessor.getActiveTime()>max) {
        		max = curProcessor.getActiveTime();
        		maxProcessor = curProcessor;
        	}
    
        	pq.insert(curProcessor);
        }
        
        System.out.println("-----------------------------------------------------------");
        
        makespan = max;
        
        int size = pq.size();
        for(int i=0; i<size; i++) {
        	Processor forOutput = pq.getMax();
        	System.out.println("id "+forOutput.getID()+", load="+forOutput.getActiveTime()+": "+forOutput.toString());
        } 
        System.out.println("Makespan = "+makespan);
        return makespan;
        
	}
	*/
	
	public static void main(String[] args) throws IOException {
		//String fileName = "test.txt";		//for cmd 
		String fileName = args[0];
		Scanner s = new Scanner(new File(fileName));
		Scanner n = new Scanner(new File(fileName));
		
		PQInterface pq = new MaxPQ(new TimeComparator());
        String processors = Files.readAllLines(Paths.get(fileName)).get(0);  
        Processor processor;
        for(int i=0; i<Integer.parseInt(processors); i++) {
        	processor = new Processor(i+1);
        	pq.insert(processor);
        }
        
        String tasks = Files.readAllLines(Paths.get(fileName)).get(1);
        int countCheck = 0;
        while (s.hasNext()) {
        	countCheck++;
        	if(s.nextLine()==null) {
        		break;
        	}
        }
        if((countCheck-2)!=Integer.parseInt(tasks)) {
        	System.err.println("ERROR");
        	return;     
        }
        
        int flag = 0;
        int max = -1;
        Processor maxProcessor;
        int makespan = 0;
        while (n.hasNext()) {
        	String curLine = n.nextLine();
        	if(curLine!=null && flag<2) {
        		flag++;
        		continue;
        	}
        	
        	int id = Integer.parseInt(curLine.split(" ")[0]);
        	int timeNeeded = Integer.parseInt(curLine.split(" ")[1]);
        	Task task = new Task(id, timeNeeded);
        	Processor curProcessor = pq.getMax();
        	curProcessor.addCompletedTask(task);
        	
        	if(curProcessor.getActiveTime()>max) {
        		max = curProcessor.getActiveTime();
        		maxProcessor = curProcessor;
        	}
        	
        	pq.insert(curProcessor);
        }
        
        System.out.println("-----------------------------------------------------------");
        
        makespan = max;
        
        int size = pq.size();
        for(int i=0; i<size; i++) {
        	Processor forOutput = pq.getMax();
        	System.out.println("id "+forOutput.getID()+", load="+forOutput.getActiveTime()+": "+forOutput.toString());
        } 
        System.out.println("Makespan = "+makespan);
        //return makespan;
        
        

	}
}
