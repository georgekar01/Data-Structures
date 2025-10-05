
public class Node<T> {
	T data;
	Node<T> next;
	
	Node(T object) {
		this(object,null);
	}
	
	Node(T object, Node<T> node){
		data = object;
		next = node;
	} 
	
	T getData() {
		return data;
	}
	
	Node<T> getNext() {
		return next;
	}
	
}
