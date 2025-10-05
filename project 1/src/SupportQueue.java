import java.io.PrintStream;
import java.util.NoSuchElementException;

public class SupportQueue {
	private Node<String> first;
	private Node<String> last;   
	private Node<String> start;    //Gia tag matching
	private int size;
	
	public SupportQueue() {
		first = null;
		last = null;
		start = null;     //Gia tag matching
		size = 0;
	}
	
	public boolean isEmpty() {
		return first == null;
	}

	public void put(String item) {
		Node<String> newNode = new Node<String>(item);
		
		if(isEmpty()) {
			first = newNode;
			last = newNode;
			start = newNode;     //Gia tag matching
			size++;
			return;
		}
		last.next = newNode;
		last = newNode;
		size++;
	}
	
	public Node<String> peekStart() throws NoSuchElementException{
		if(isEmpty()) {
			throw new NoSuchElementException("Empty queue");
		}
		return start;
	}
	
	
	public String get() throws NoSuchElementException{
		
		if(isEmpty()) {
			throw new NoSuchElementException("Empty queue");
		}
		Node<String> node = first;
		first = first.next;
		size--;
		return node.data;
	}

	public String peek() throws NoSuchElementException{
		if(isEmpty()) {
			throw new NoSuchElementException("Empty queue");
		}
		return first.data;
	}

	public void printQueue(PrintStream stream) {
		if(isEmpty()) {
			stream.println("The queue is empty");
		}
		
		Node<String> temp = first;
		
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
