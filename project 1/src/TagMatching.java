import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class TagMatching {
	
	public static void main(String[] args) throws IOException {
		String fileName = args[0];													//for cmd     
		String txt = new Scanner(new File(fileName)).useDelimiter("\\Z").next();    //eisagvgh orismatos kai treximo apo cmd
		Pattern p = Pattern.compile("<([^\\s>]+)");
	    Matcher m = p.matcher(txt);
	    SupportQueue supportQueue = new SupportQueue();
	    StringStack stringStack = new StringStackImpl();
	    while(m.find()) {
	        String tag = m.group(1);
	        supportQueue.put(tag);    
	    }
	    
	    Node<String> cur = supportQueue.peekStart();
	    for(int i=0; i<supportQueue.size(); i++) {
	    	if(cur.data.charAt(0)!='/') {
	    		stringStack.push(cur.data);
	    	}else {
	    		stringStack.pop();
	    	}
	    	cur = cur.next;
	    }
	    
	    if(stringStack.size()==0) {
	    	System.out.println("Tags match");
	    }else {
	    	System.out.println("Tags dont match");
	    }
	    
	}
}
