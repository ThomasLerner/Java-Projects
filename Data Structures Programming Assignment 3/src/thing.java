
/**
 * @author Thomas Lerner
 *
 */
import java.util.ArrayList;

public class thing<T> extends Comparable<T> {

	private ArrayList<T> heap = new ArrayList<T>();

	public void insert(T item){

		// Adds an item, then sifts it up through the heap
		heap.add(item);
		siftUp(heap.size() - 1);
	}

	public T removeMax(){
		T removedNode = heap.get(0);

		// Swaps first and last nodes
		T temp = heap.get(heap.size() - 1);
		heap.set(heap.size() - 1, heap.get(0));
		heap.set(0, temp);

		// Removes last node, then sifts first node down
		heap.remove(heap.size() -1);       
		siftDown(0);
		return removedNode;
	}

	public void siftUp(int i){

		// Defining parent
		int parent = ((i + 1) / 2 ) - 1;

		// Recursively swaps with parent until reaches root
		if(heap.get(i).compareTo(heap.get(parent)) > 0) {
			T temp = heap.get(parent);
			heap.set(parent, heap.get(i));
			heap.set(parent, temp);
			siftUp(parent);
		}
	}


	public void siftDown(int i){

		// Defining left and right children
		int left = ((i + 1) * 2) - 1;
		int right = (i + 1) * 2;

		// Determining the largest child
		int largest;
		if(heap.get(left).compareTo(heap.get(right)) >= 0) {
			largest = left;
		}
		else {
			largest = right;
		}

		// Recursively swaps with largest child until reaches leaf
		if (heap.get(largest).compareTo(heap.get(i)) > 0 ){
			T temp = heap.get(i);
			heap.set(i, heap.get(largest));
			heap.set(largest, temp);
			siftDown(largest);
		}
	}
}
