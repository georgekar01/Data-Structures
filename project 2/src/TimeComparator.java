
import java.util.Comparator;

public class TimeComparator implements Comparator<Processor> {
	 public int compare(Processor p1, Processor p2) {
	        return p1.getActiveTime() - p2.getActiveTime();
	    }
}
