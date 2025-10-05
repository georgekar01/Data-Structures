
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
	
	
	public Node<T> getFirst() {
		return first;
	}
	
	public Node<T> getLast(){
		return last;
	}
	
	
	public boolean find(T item) {
		
		Node<T> check = first;
		
		while(check!=null) {
			if(check.data==item) {  
				return true;
			}
			check = check.next;
		}
		
		return false;
		
	}
	
	
	
	public boolean remove(T item) {
		
		Node<T> check = first;
		
		int index = 0;
		for(int i=0; i<size; i++) {
			if(check.data==item) {   
				break;
			}
			check = check.next;
			index++;
		}
		
		Node<T> cur = first;
		
		if(index==0) {
			first = first.next;
			return true;
		}
		
		for(int i=0; i<index-1; i++) { 
			cur = cur.next;
		}
		
		cur.next = cur.next.next;
		size--;  
		return true;		
		
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
