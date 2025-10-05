
import java.io.PrintStream;
import java.util.NoSuchElementException;

public class QueueImpl<T> implements QueueInterface<T> {
	private Node<T> first;  
	private Node<T> last;        
	private int size;
	
	public QueueImpl() {
		first = null;
		last = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return first == null;
	}

	public void put(T item) {
		Node<T> newNode = new Node<T>(item);
		
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
	
	public T get() throws NoSuchElementException{   
		
		if(isEmpty()) {
			throw new NoSuchElementException("Empty queue");
		}
		Node<T> node = first;
		first = first.next;
		size--;
		return node.data;
	}

	public T peek() throws NoSuchElementException{
		if(isEmpty()) {
			throw new NoSuchElementException("Empty queue");
		}
		return first.data;
	}

	public void printQueue(PrintStream stream) {
		if(isEmpty()) {
			stream.println("The queue is empty");
		}
		
		Node<T> temp = first;
		
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
