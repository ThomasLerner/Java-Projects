import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Tom Lerner
 * 
 * Creates a custom iterator for a double linked list
 * 
 * @param <T>
 */
public class DoubleLinkedList<T> implements Iterable<T>{


	/** a reference to the first node of the double linked list */
	private DLNode<T> front;

	/** a reference to the last node of a double linked list */
	private DLNode<T> back;

	DLNode<T> nodePtr;

	protected DLNode<T> getNode() {
		return nodePtr;
	}

	public void setNode(DLNode<T> x) {
		nodePtr = x;
	}

	/** Create an empty double linked list. */
	public DoubleLinkedList() {
		front = back = null;
	}

	/** 
	 * Returns true if the list is empty.
	 * @return  true if the list has no nodes
	 */
	public boolean isEmpty() {
		return (getFront() == null);
	}

	/**
	 * Returns the reference to the first node of the linked list.
	 * @return the first node of the linked list
	 */
	protected DLNode<T> getFront() {
		return front;
	}

	/**
	 * Sets the first node of the linked list.
	 * @param node  the node that will be the head of the linked list.
	 */
	protected void setFront(DLNode<T> node) {
		front = node;
	}

	/**
	 * Returns the reference to the last node of the linked list.
	 * @return the last node of the linked list
	 */
	protected DLNode<T> getBack() {
		return back;
	}

	/**
	 * Sets the last node of the linked list.
	 * @param node the node that will be the last node of the linked list
	 */
	protected void setBack(DLNode<T> node) {
		back = node;
	}

	/*----------------------------------------*/
	/* METHODS TO BE ADDED DURING LAB SESSION */
	/*----------------------------------------*/

	/**
	 * Add an element to the head of the linked list.
	 * @param element  the element to add to the front of the linked list
	 */
	public void addToFront(T element) {
		if (!isEmpty()){
			setFront(new DLNode<T>(element,null,getFront()));
		}
		else {
			setFront(new DLNode<T>(element,null,null));
			setBack(getFront());
		}
	}

	/**
	 * Add an element to the tail of the linked list.
	 * @param element  the element to add to the tail of the linked list
	 */
	public void addToBack(T element) {
		if (getBack()!=null){
			setBack(new DLNode<T>(element,getBack(),null));
		}
		else {
			setFront(new DLNode<T>(element,null,null));
			setBack(getFront());
		}
	}

	/**
	 * Remove and return the element at the front of the linked list.
	 * @return the element that was at the front of the linked list
	 * @throws NoSuchElementException if attempting to remove from an empty list
	 */
	public T removeFromFront() throws NoSuchElementException{
		if (isEmpty()){
			throw new NoSuchElementException();
		}
		else {
			T removedElement = getFront().getElement();
			if (getFront().getNext() == null){
				setFront(null);
				setBack(null);
			}
			else {
				getFront().getNext().setPrevious(null);
				setFront(getFront().getNext());
			}
			return removedElement;

		}
	}

	/**
	 * Remove and return the element at the back of the linked list.
	 * @return the element that was at the back of the linked list
	 * @throws NoSuchElementException if attempting to remove from an empty list
	 */
	public T removeFromBack() throws NoSuchElementException{
		if (isEmpty()){
			throw new NoSuchElementException();
		}
		else {
			T removedElement = getBack().getElement();
			if (getBack().getPrevious()==null){
				setFront(null);
				setBack(null);
			}
			else {

				setBack(getBack().getPrevious());
				getBack().setNext(null);
			}
			return removedElement;
		}
	}


	/* 
	 * Creates a ListIterator with custom properties
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		setNode(null);
		return new ListIterator<T>(){

			boolean flag = false;

			/* 
			 * Adds a node with the specified element to the point that the "cursor" is at
			 * Alters the pointers of nearby nodes to accommodate the change
			 * 
			 * @see java.util.ListIterator#add(java.lang.Object)
			 */
			@Override
			public void add(T element) {
				DLNode<T> node = new DLNode<T>(element, null, null);
				if(hasPrevious() && hasNext()) {
					node = new DLNode<T>(element, getNode(), getNode().getNext());
					node.getPrevious().setNext(node);
					node.getNext().setPrevious(node);
				}
				else if(hasPrevious()) {
					addToBack(element);
					next();
				}
				else {
					addToFront(element);
					next();
				}
			}

			/*
			 * Checks if there is a node after this one
			 * 
			 * @see java.util.ListIterator#hasNext()
			 */
			@Override
			public boolean hasNext() {
				if(getNode() == null && getFront() != null) {
					return true;
				}
				else {
					return (getNode().getNext() != null);
				}
			}

			/*
			 * Checks if there is a node before this one
			 * 
			 * @see java.util.ListIterator#hasPrevious()
			 */
			public boolean hasPrevious() {
				if(getNode() != null) {
					return true;
				}
				else {
					return false;
				}
			}

			/* 
			 * Advances the pointer node by one link in the list
			 * If previous() was not called before this, advances cursor by one link
			 * 
			 * @see java.util.ListIterator#next()
			 */
			@Override
			public T next() {
				if(getNode() == null) {
					setNode(getFront());
					flag = true;
					return getNode().getElement();
				}
				else if(hasNext()) {
					flag = true;
					setNode(getNode().getNext());
					return getNode().getElement();
				}
				else {
					throw new NoSuchElementException();
				}
			}

			/* 
			 * Legacy. Ignore.
			 * 
			 * @see java.util.ListIterator#nextIndex()
			 */
			@Override
			public int nextIndex() {
				throw new UnsupportedOperationException();
			}

			/*
			 * Retreats the pointer node by one link in the list
			 * If next() was not called before this, retreats cursor by one link
			 * 
			 * @see java.util.ListIterator#previous()
			 */
			@Override
			public T previous() {
				if(hasPrevious()) {
					flag = true;
					DLNode<T> sub = getNode();
					setNode(getNode().getPrevious());
					return sub.getElement();
				} else if(getNode() == getFront()) {
					flag = true;
					setNode(null);
					return getFront().getElement();
				}
				else {
					throw new NoSuchElementException();
				}
			}

			/* 
			 * Legacy. Ignore.
			 * 
			 * @see java.util.ListIterator#previousIndex()
			 */
			@Override
			public int previousIndex() {
				throw new UnsupportedOperationException();
			}

			/* 
			 * Removes the node at the point of the cursor from the list
			 * Alters the pointers of nearby nodes to accommodate the change
			 * Fails if called twice without changing position of cursor
			 * 
			 * @see java.util.ListIterator#remove()
			 */
			@Override
			public void remove() {

				if(flag == false) {
					throw new IllegalStateException();
				}
				flag = false;
				if(getNode() == getFront()){
					removeFromFront();
				}
				else if(hasPrevious() && hasNext()) {
					getNode().getPrevious().setNext(getNode().getNext());
					getNode().getNext().setPrevious(getNode().getPrevious());
					previous();
				}
				else if(getNode() == getBack()) {
					removeFromBack();
				}
			}

			/* 
			 * Legacy. Ignore.
			 * 
			 * @see java.util.ListIterator#set(java.lang.Object)
			 */
			@Override
			public void set(T e) {
				throw new UnsupportedOperationException();  
			}
		};
	}
}
