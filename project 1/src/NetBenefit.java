import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class NetBenefit {
	
	public static void main(String[] args) throws IOException {
		String fileName = args[0];												   //for cmd	
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {    //eisagvgh orismatos kai treximo apo cmd
			int balance = 0;
			IntQueue stockAmmountQueue = new IntQueueImpl();
			IntQueue stockPriceQueue = new IntQueueImpl();
		    for(String line; (line = br.readLine()) != null; ) {
		    	String[] tokens = line.split(" ");
		    	if(line.charAt(0)=='b') {
		        	stockAmmountQueue.put(Integer.parseInt(tokens[1]));
		        	stockPriceQueue.put(Integer.parseInt(tokens[3]));
		        	
		        	balance = balance - Integer.parseInt(tokens[1])*Integer.parseInt(tokens[3]);
		        }else {
		         	int stocks2sell = Integer.parseInt(tokens[1]);
		        	while(stocks2sell!=0) {
			        	if(stocks2sell/stockAmmountQueue.peek()>=1) {				
			        		balance += Integer.parseInt(tokens[3])*stockAmmountQueue.peek();
			        		stockPriceQueue.get();
			        		stocks2sell = stocks2sell - stockAmmountQueue.get();  
			        	}else {
			        		int cur = stockAmmountQueue.get();
			        		stockAmmountQueue.put(cur-stocks2sell);		        		
			        		balance += Integer.parseInt(tokens[3])*stocks2sell;
			        		stocks2sell = 0;
			        	}
		        	}	
		        }
		    }
		    
		    if(balance>0) {
		    	System.out.println(balance+" $ profit");
		    }else if(balance<0){
		    	System.out.println(balance+" $ loss");
		    }else {
		    	System.out.println("Zero profit");
		    }
		}
		
	}
	
}
