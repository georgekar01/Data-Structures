import java.io.PrintStream;
import java.util.NoSuchElementException;

public class IntQueueImpl implements IntQueue {            
	private Node<Integer> first;  
	private Node<Integer> last;        
	private int size;
	
	public IntQueueImpl() {
		first = null;
		last = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return first == null;
	}

	public void put(int item) {
		Node<Integer> newNode = new Node<Integer>(item);
		
		if(isEmpty()) {
			first = newNode;
			last = newNode;
			size++;
			return;
		}
		last.next = newNode;
		last = newNode;
		size++;
	}
	
	public int get() throws NoSuchElementException{   
		
		if(isEmpty()) {
			throw new NoSuchElementException("Empty queue");
		}
		Node<Integer> node = first;
		first = first.next;
		size--;
		return node.data;
	}

	public int peek() throws NoSuchElementException{
		if(isEmpty()) {
			throw new NoSuchElementException("Empty queue");
		}
		return first.data;
	}

	public void printQueue(PrintStream stream) {
		if(isEmpty()) {
			stream.println("The queue is empty");
		}
		
		Node<Integer> temp = first;
		
		while(temp!=null) {
			stream.printf("%s ", temp.data);
			temp = temp.next;
		}
		
		stream.println();
	}

	public int size() {
		return size;
	}
}
