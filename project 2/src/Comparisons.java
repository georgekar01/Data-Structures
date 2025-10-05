
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Comparisons {
	
	public static void createNwrite() throws IOException {
		
		File f = new File("C:\\Users\\georg\\eclipse-workspace\\ergasia2\\folder");
		try{
		    if(f.mkdir()) { 
		        System.out.println("Directory Created");
		    } else {
		        System.out.println("Directory is not created");
		    }
		} catch(Exception e){
		    e.printStackTrace();
		} 
		
		QueueInterface<Integer> queue = new QueueImpl<Integer>();
		queue.put(100);
		queue.put(250);
		queue.put(500);
		
		for(int k=1; k<=3; k++) {
			for(int i=0; i<10; i++) { 
				try {
				      File myObj = new File("C:\\Users\\georg\\eclipse-workspace\\ergasia2\\folder"+"\\"+"N"+k+"_"+(i+1)+".txt");
				      if (myObj.createNewFile()) {
				        System.out.println("File created: " + myObj.getName());
				      } else {
				        System.out.println("File already exists.");
				      }
				    } catch (IOException e) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				    }
				
				FileWriter myWriter = new FileWriter("C:\\Users\\georg\\eclipse-workspace\\ergasia2\\folder"+"\\"+"N"+k+"_"+(i+1)+".txt");
			      myWriter.write((int)Math.floor(Math.sqrt(queue.peek()))+"\n");
			      myWriter.write(queue.peek()+"\n");
			      int lim = queue.peek();
			      for(int j=0; j<lim; j++) {
			    	  myWriter.write(new Random().nextInt(500)+" "+new Random().nextInt(100)+"\n");
			      }
			      myWriter.close();
			}
			queue.get();
		}
	}
	public static int help(String[] args) throws IOException {    //same as Greedy's main() implementation 
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
	
	
	public static void main(String[] args) throws IOException {
		createNwrite();
		
		int greedyRead = 0;
		int decRead = 0;
		int sumMakespanGreedy = 0;
		int sumMakespanDec = 0;
		
		for(int i=0; i<30; i++) {
			//greedy test
			String[] arguments = {};
			if(i<10) {
				arguments = new String[] {"C:\\Users\\georg\\eclipse-workspace\\ergasia2\\folder"+"\\"+"N"+1+"_"+(i+1)+".txt"};
			}
			if(i>=10 && i<20) {
				arguments = new String[] {"C:\\Users\\georg\\eclipse-workspace\\ergasia2\\folder"+"\\"+"N"+2+"_"+(i-9)+".txt"};
			}
			if(i>=20) {
				arguments = new String[] {"C:\\Users\\georg\\eclipse-workspace\\ergasia2\\folder"+"\\"+"N"+3+"_"+(i-19)+".txt"};
			}
	        
		 	//sumMakespanGreedy += Greedy.main(arguments);
			sumMakespanGreedy += help(arguments);
			System.out.println("Greedy");
			greedyRead++;
			
			//-----------------------------------------------------------------------------------
			
			//greedy decreasing test
			Task[] tasks = Sort.heapSort(Greedy.createTaskArray(arguments[0])); //needs to be fixed
	
			String[] arguments_ = {};
			File forDec = null;
			String filePath = "";
			if(i<10) {
				filePath = "C:\\Users\\georg\\eclipse-workspace\\ergasia2"+"\\"+"N"+1+"__"+(i+1)+".txt"; 
				arguments_ = new String[] {filePath};
				forDec = new File(filePath);  
			}
			if(i>=10 && i<20) {
				filePath = "C:\\Users\\georg\\eclipse-workspace\\ergasia2"+"\\"+"N"+2+"__"+(i-9)+".txt";
				arguments_ = new String[] {filePath};
				forDec = new File(filePath); 
			}
			if(i>=20) {
				filePath = "C:\\Users\\georg\\eclipse-workspace\\ergasia2"+"\\"+"N"+3+"__"+(i-19)+".txt";
				arguments_ = new String[] {filePath};
				forDec = new File(filePath);
			}
			
			forDec.createNewFile();
			FileWriter writer = new FileWriter(filePath);
			String proc = Files.readAllLines(Paths.get(arguments[0])).get(0)+"\n";
			writer.write(proc);
			String task = Files.readAllLines(Paths.get(arguments[0])).get(1)+"\n";
			writer.write(task);
			for(int j=0; j<tasks.length; j++) {	
				writer.write(tasks[j].getID()+" "+tasks[j].getTime()+"\n");
			}
			
			writer.close();
			//sumMakespanDec += Greedy.main(arguments_);
			sumMakespanDec += help(arguments_);
			System.out.println("Greedy decreasing");
			decRead++;
		}
		
		System.out.println("\n-----------------------------------------------------------");
		System.out.println("Greedy avg makespan: "+sumMakespanGreedy/30+" (files read: "+greedyRead+")");
		System.out.println("Greedy decreasing avg makespan: "+sumMakespanDec/30+" (files read: "+decRead+")");
		System.out.println("-----------------------------------------------------------");
		
		if(sumMakespanGreedy>sumMakespanDec) {
			System.out.println("Greedy decreasing is more efficient");
		}else if(sumMakespanGreedy<sumMakespanDec) {
			System.out.println("Greedy is more efficient");
		}else {
			System.out.println("Similar efficiency");
		}
	
		System.out.println("-----------------------------------------------------------");
	}
	
}
