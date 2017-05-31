import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Thomas Lerner
 * 
 * @param <Boolean>
 */
public class boolLinkedList implements Iterable<Boolean>, boolList{

	BoolNode nodePtr;
	
	int counter = 0;
	
	public void resetNode() {
		while(iterator().hasPrevious()) {
			iterator().previous();
		}
	}

	/* 
	 * Returns the size of the list
	 */
	@Override
	public int size() {
		return counter;
	}

	/* 
	 * Inserts the specified node into the list
	 */
	@Override
	public void insert(int i, boolean value) {
		resetNode();
		for(int x = 0; x < i && x < counter; x++) {
			if(iterator().hasNext()) {
				iterator().next();
			}
			else {
				BoolNode newNode = new BoolNode(value, nodePtr, null);
				newNode.getPrevious().setNext(newNode);
			}
		}
		iterator().add(value);
		counter++;
	}

	/* 
	 * Removes the specified node from the list
	 */
	@Override
	public void remove(int i) {
		resetNode();
		int temp = 0;
		for(int x = 0; x < i; x++) {
			if(iterator().hasNext()) {
				iterator().next();
				temp++;
			}
			else {
				break;
			}
		}
		if(temp == i - 1) {
			iterator().remove();
			counter--;
		}
	}

	/* 
	 * Returns the element at the specified node
	 */
	@Override
	public boolean lookup(int i) {
		resetNode();
		int temp = 0;
		for(int x = 0; x < i && x < counter; x++) {
			temp++;
			iterator().next();
		}
		if(temp == i - 1) {
			return nodePtr.getElement();
		}
		else {
			throw new NullPointerException();
		}
	}



	/* 
	 * Flips the values of all booleans in the list
	 */
	@Override
	public boolean negateAll() {
		resetNode();
		if(!iterator().hasNext()) {
			throw new NullPointerException();
		}
		for(int x = 0; x < counter; x++) {
			nodePtr.setElement(!nodePtr.getElement());
			iterator().next();
		}
	}
	

	/* 
	 * Creates a ListIterator with custom properties
	 * 
	 */
	@Override
	public ListIterator<Boolean> iterator() {
		return new ListIterator<Boolean>(){

			/* 
			 * Adds a node before the current node
			 * 
			 */
			@Override
			public void add(Boolean value) {
				BoolNode newNode = new BoolNode(value, nodePtr.getPrevious(), nodePtr);
				if(iterator().hasPrevious()) {
					newNode.getPrevious().setNext(newNode);
				}
				if(iterator().hasNext()) {
					newNode.getNext().setPrevious(newNode);
				}
			}

			/*
			 * Checks if there is a node after the current node
			 * 
			 */
			@Override
			public boolean hasNext() {
				if(nodePtr != null && nodePtr.getNext() != null) {
					return true;
				}
				else {
					return false;
				}
			}

			/*
			 * Checks if there is a node before the current node
			 * 
			 */
			@Override
			public boolean hasPrevious() {
				if(nodePtr != null && nodePtr.getPrevious() != null) {
					return true;
				}
				else {
					return false;
				}
			}

			/* 
			 * Advances the current node by one link in the list
			 * 
			 */
			@Override
			public Boolean next() {
				if(hasNext()) {
					nodePtr = nodePtr.getNext();
				}
				else {
					throw new NoSuchElementException();
				}
				return nodePtr.getElement();
			}

			/* 
			 * Legacy. Ignore.
			 * 
			 */
			@Override
			public int nextIndex() {
				throw new UnsupportedOperationException();
			}

			/*
			 * Retreats the pointer node by one link in the list
			 * If next() was not called before this, retreats cursor by one link
			 * 
			 */
			
			@Override
			public Boolean previous() {
				if(hasPrevious()) {
					nodePtr = nodePtr.getPrevious();
				}
				else {
					throw new NoSuchElementException();
				}
				return nodePtr.getElement();
			}

			/* 
			 * Legacy. Ignore.
			 * 
			 */
			@Override
			public int previousIndex() {
				throw new UnsupportedOperationException();
			}

			/* 
			 * Removes the current node if it exists
			 * 
			 */
			@Override
			public void remove() {
				if(hasNext() && hasPrevious()) {
					nodePtr.getNext().setPrevious(nodePtr.getPrevious());
					nodePtr.getPrevious().setNext(nodePtr.getNext());
				}
				else if(hasNext()) {
					nodePtr.getNext().setPrevious(null);
				}
				else {
					nodePtr.getPrevious().setNext(null);
				}
			}

			/* 
			 * Legacy. Ignore.
			 * 
			 */
			@Override
			public void set(Boolean e) {
				throw new UnsupportedOperationException();  
			}
		};
	}
}
