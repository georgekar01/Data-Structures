
import java.util.Comparator;

public class MaxPQ implements PQInterface {
	private Processor[] heap;
	private int size;
	private Comparator<Processor> comparator;
	
	private static final int DEFAULT_CAPACITY = 4;
	private static final int AUTOGROW_SIZE = 4;
	
	
	public MaxPQ(Comparator<Processor> comparator) {
		this.heap = (Processor[]) new Processor[DEFAULT_CAPACITY + 1];
        this.size = 0;
        this.comparator = comparator;
	}
	
	
	@Override
	public boolean isEmpty() {
		if(heap[1]==null) {  
			return true;
		}
	
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void insert(Processor x) {
		if (size == heap.length - 1) {
            resize();
		}
        heap[++size] = x;

        swim(size);
	}

	@Override
	public Processor max() {
		if(isEmpty()==false) {
        	return heap[1];
        }else{
        	return null;
        }
	}

	@Override
	public Processor getMax() {
		if (size == 0)
            return null;

        Processor root = heap[1];

        heap[1] = heap[size];
        size--;

        sink(1);

        return root;
	}
	
	private void swim(int i) {    //support method
        
        if (i == 1)
            return;

        int parent = i / 2;

        while (i != 1 && comparator.compare(heap[i], heap[parent]) < 0) {
            swap(i, parent);
            i = parent;
            parent = i / 2;
        }
	}
	
	private void sink(int i) {    //support method
        
        int left = 2 * i;
        int right = left + 1;

        if (left > size)
            return;

        while (left <= size) {
           
            int max = left;
            if (right <= size) {
                if (comparator.compare(heap[left], heap[right]) > 0)
                    max = right;
            }

            if (comparator.compare(heap[i], heap[max]) <= 0)
                return;
            else {
                swap(i, max);
                i = max;
                left = i * 2;
                right = left + 1;
            }
        }
    }
	
	private void swap(int i, int j) {    //support method
        Processor temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
	
	private void resize() {    //support method
        Processor[] newHeap = (Processor[]) new Processor[heap.length + AUTOGROW_SIZE];

        for (int i = 0; i <= size; i++) {
            newHeap[i] = heap[i];
        }

        heap = newHeap;
    }
	
	public Processor remove(int id) {     //support method
    	Processor tempCity = null;
    	
    	for (int i=1; i<heap.length; i++) {
    		if(heap[i].getID()==id) {
    			swap(i,1);
    			tempCity = getMax();
    			//swim(i);
    			return tempCity;
    		}
    	}
    	return null;
    	
    }
	
	public void printHeap() {
    	for(int i=1; i<=this.size(); i++) {
    		System.out.println(heap[i].getID());  
    	}
    }

}
