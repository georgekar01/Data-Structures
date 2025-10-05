
public class Main {

	public static void main(String[] args) {
		
		System.out.println("Testing String Stack");
		
		StringStack stringStack = new StringStackImpl();
		

		stringStack.push("Giorgos");
		System.out.println("");
		stringStack.printStack(System.out);
		System.out.print(stringStack.size());
		stringStack.push("Aggelos");
		System.out.println("");
		stringStack.printStack(System.out);
		System.out.print(stringStack.size());
		stringStack.push("Sotiris");
		System.out.println("");
		stringStack.printStack(System.out);
		System.out.print(stringStack.size());
		stringStack.push("Eirini");
		System.out.println("");
		stringStack.printStack(System.out);
		System.out.print(stringStack.size());
		stringStack.push("Panos");
		System.out.println("");
		stringStack.printStack(System.out);
		System.out.print(stringStack.size());
		System.out.println("");
		System.out.println("");
		
		
		stringStack.pop();
		stringStack.printStack(System.out);
		System.out.print(stringStack.size());
		System.out.println("");
		stringStack.pop();
		stringStack.printStack(System.out);
		System.out.print(stringStack.size());
		System.out.println("");
		stringStack.pop();
		stringStack.printStack(System.out);
		System.out.print(stringStack.size());
		System.out.println("");
		stringStack.pop();
		stringStack.printStack(System.out);
		System.out.print(stringStack.size());
		System.out.println("");
		stringStack.pop();
		stringStack.printStack(System.out);
		System.out.print(stringStack.size());
		System.out.println("");
		System.out.println("");
		
		
		
		
		
		
		System.out.println("-------------------------------");
		
		
		
		
		
		System.out.println("Testing Integer Queue");
		
		IntQueueImpl intQueue = new IntQueueImpl();
		
		intQueue.put(111);
		System.out.println("");
		intQueue.printQueue(System.out);
		System.out.print(intQueue.size());
		intQueue.put(222);
		System.out.println("");
		intQueue.printQueue(System.out);
		System.out.print(intQueue.size());
		intQueue.put(333);
		System.out.println("");
		intQueue.printQueue(System.out);
		System.out.print(intQueue.size());
		intQueue.put(444);
		System.out.println("");
		intQueue.printQueue(System.out);
		System.out.print(intQueue.size());
		intQueue.put(555);
		System.out.println("");
		intQueue.printQueue(System.out);
		System.out.print(intQueue.size());
		System.out.println("");
		System.out.println("********************");
		
		
		intQueue.get();
		intQueue.printQueue(System.out);
		System.out.print(+intQueue.size());
		System.out.println("");
		intQueue.get();
		intQueue.printQueue(System.out);
		System.out.print(intQueue.size());
		System.out.println("");
		intQueue.get();
		intQueue.printQueue(System.out);
		System.out.print(intQueue.size());
		System.out.println("");
		intQueue.get();
		intQueue.printQueue(System.out);
		System.out.print(intQueue.size());
		System.out.println("");
		intQueue.get();
		intQueue.printQueue(System.out);
		System.out.print(intQueue.size());
		System.out.println("");
		System.out.println("");
		
		
		
	}
	
	
}
