/**
 * The node of a double linked list.
 *
 * @author Thomas Lerner
 *
 * @param 
 */
public class BoolNode {
	/**
	 * The element stored in the node.
	 */
	private boolean element;

	/**
	 * A pointer to the next node in the list.
	 */
	private BoolNode next;

	/**
	 * A pointer to the previous node of the list.
	 */
	private BoolNode previous;

	/**
	 * The constructor.  Creates a node and puts it into place in the double linked list.
	 * @param element  the element to be stored in the node
	 * @param previous the node that will be before this node in the list, or null if no node is before this one
	 * @param next  the node that will be after this node in the list, or null of no node will be after this node
	 */
	public BoolNode(boolean element, BoolNode previous, BoolNode next) {
		this.element = element;
		this.next = next;
		this.previous = previous;
		if (next != null) {
			next.setPrevious(this);
		}
		if (previous != null) {
			previous.setNext(this);
		}
	}

	/**
	 * Get the element stored in this node.
	 * @return the element stored in the node
	 */
	public boolean getElement() {
		return element;
	}
	
	public void setElement(boolean element) {
		this.element = element;
	}

	/**
	 * Gets the node that is before this node in the list.
	 * @return  a reference to the node that comes before this node in the list
	 */
	public BoolNode getPrevious() {
		return previous;
	}

	/**
	 * Gets the node that is after this node in the list.
	 * @return  a reference to the node that comes after this node in the list
	 */
	public BoolNode getNext() {
		return next;
	}

	/**
	 * Sets the reference to the node that will be after this node in the list.
	 * @param node  a reference to the node that will be after this node in the list
	 */
	public void setNext(BoolNode node) {
		next = node;
	}

	/**
	 * Sets the reference to the node that will be before this node in the list.
	 * @param node  a reference to the node that will be before this node in the list
	 */
	public void setPrevious(BoolNode node) {
		previous = node;
	}
}
