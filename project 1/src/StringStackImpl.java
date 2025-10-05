import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl implements StringStack {
	private Node<String> stackHead;
	private int size;

	
	public StringStackImpl() {
		stackHead = null;
		size = 0;
	}
	
	
	public boolean isEmpty() {
		return stackHead == null;
	};


	public void push(String item) {
		Node<String> newNode = new Node<String>(item);
		if(isEmpty()) {
			stackHead = newNode;
			stackHead.next = null;
			size++;
			return;
		}
		newNode.next = stackHead;
		stackHead = newNode;
		size++;
	}


	public String pop() throws NoSuchElementException{	
		if(isEmpty()) {
			throw new NoSuchElementException("Empty stack");
		}
		String node = stackHead.data;
		stackHead = stackHead.next;
		size--;
		return node;
	}

 
	public String peek() throws NoSuchElementException{
		if(stackHead==null) {
			throw new NoSuchElementException("Empty stack");
		}
		return stackHead.data;
	}


	public void printStack(PrintStream stream) {
		if(isEmpty()) {
			stream.println("The stack is empty");
		}
		Node<String> temp = stackHead;
		while(temp!=null) {
			stream.printf("%s ",temp.data);
			temp = temp.next;
		}
		
	}

	public int size() {
		return size;
	}
}
