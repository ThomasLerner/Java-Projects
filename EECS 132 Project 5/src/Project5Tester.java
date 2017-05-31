import static org.junit.Assert.*;
import java.util.ListIterator;
import org.junit.Test;

/**
 * @author Tom Lerner
 * 
 * A class that tests the methods of the project classes
 * 
 */

public class Project5Tester {

	/**
	 * Tests the addToFront method of DoubleLinkedList.
	 */
	@Test
	public void testAddToFront() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		list.addToFront(3);
		list.addToFront(2);
		list.addToFront(1);
		DLNode<Integer> head = list.getFront();
		DLNode<Integer> tail = list.getBack();

		assertEquals("Testing first node of list", new Integer(1), head.getElement());
		assertEquals("Testing second node of list", new Integer(2), head.getNext().getElement());
		assertEquals("Testing third node of list", new Integer(3), head.getNext().getNext().getElement());
		assertEquals("Testing end of list", null, head.getNext().getNext().getNext());

		assertEquals("Testing node at back of list", new Integer(3), tail.getElement());
		assertEquals("Testing next to last node", new Integer(2), tail.getPrevious().getElement());
		assertEquals("Testing third to last node", new Integer(1), tail.getPrevious().getPrevious().getElement());
		assertEquals("Testing front of list", null, tail.getPrevious().getPrevious().getPrevious());
	}

	/**
	 * Tests the addToBack method of DoubleLinkedList.
	 */
	@Test
	public void testAddToBack() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		list.addToBack(1);
		list.addToBack(2);
		list.addToBack(3);
		DLNode<Integer> head = list.getFront();
		DLNode<Integer> tail = list.getBack();

		assertEquals("Testing last node of list", new Integer(3), tail.getElement());
		assertEquals("Testing next to last node", new Integer(2), tail.getPrevious().getElement());
		assertEquals("Testing third to last node", new Integer(1), tail.getPrevious().getPrevious().getElement());
		assertEquals("Testing front of list", null, tail.getPrevious().getPrevious().getPrevious());

		assertEquals("Testing node at front of list", new Integer(1), head.getElement());
		assertEquals("Testing second node of list", new Integer(2), head.getNext().getElement());
		assertEquals("Testing third node of list", new Integer(3), head.getNext().getNext().getElement());
		assertEquals("Testing end of list", null, head.getNext().getNext().getNext());
	}

	/**
	 * Tests the removeFromFront method of DoubleLinkedList.
	 */
	@Test
	public void testRemoveFromFront() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		list.addToFront(1);
		list.addToFront(2);
		list.addToFront(3);
		assertEquals("Removing element of list", new Integer(3), list.removeFromFront());
		assertEquals("Removing a second element", new Integer(2), list.removeFromFront());
		assertEquals("Removing a third element", new Integer(1), list.removeFromFront());
		assertEquals("Removed last element of list", true, list.isEmpty());
		try {
			list.removeFromFront();
			fail("Removing from empty list did not throw an exception.");
		}
		catch (java.util.NoSuchElementException e) {
			/* everything is good */
		}
		catch (Exception e) {
			fail("Removing from empty list threw the wrong type of exception.");
		}

		list.addToBack(6);
		list.addToBack(7);
		assertEquals("Removing element added to back of list", new Integer(6), list.removeFromFront());
		assertEquals("Removing second element added to back", new Integer(7), list.removeFromFront());
	}

	/**
	 * Tests the removeFromBack method of DoubleLinkedList.
	 */
	@Test
	public void testRemoveFromBack() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		list.addToBack(5);
		list.addToFront(4);
		list.addToBack(6);
		assertEquals("Removing element from back of list", new Integer(6), list.removeFromBack());
		assertEquals("Removing second element from back of list", new Integer(5), list.removeFromBack());
		assertEquals("Removing element from back that was added to front", new Integer(4), list.removeFromBack());
		assertEquals("Removing last element of list", true, list.isEmpty());
		try {
			list.removeFromBack();
			fail("Removing from empty list did not throw an exception.");
		}
		catch (java.util.NoSuchElementException e) {
			/* everything is good */
		}
		catch (Exception e) {
			fail("Removing from empty list threw the wrong type of exception.");
		}
	}

	/**
	 * Tests the linked list iterator.
	 */
	@Test
	public void testListIterator() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		for (int i = 5; i > 0; i--)
			list.addToFront(i);

		/* checking that we get out what we put it */
		int i = 1;
		for (Integer x: list)
			assertEquals("Testing value returned by iterator", new Integer(i++), x);



		if (i != 6)
			fail("The iterator did not run through all the elements of the list");
	}

	/**
	 * Tests the remove feature of the linked list iterator.
	 */
	@Test
	public void testListIteratorRemove() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		for (int i = 5; i > 0; i--)
			list.addToFront(i);

		/* testing removing the first element through the iterator */
		ListIterator<Integer> listIterator = (ListIterator<Integer>) list.iterator();
		listIterator.next();
		listIterator.remove();

		/* the list should now be 2 through 5 */
		int i = 2;
		for (Integer x: list)
			assertEquals("The iterator failed when removing the first element", new Integer(i++), x);
		if (i != 6)
			fail("The iterator failed when removing the first element");

		/* testing removing element 3 */
		listIterator = (ListIterator<Integer>) list.iterator();
		listIterator.next();
		listIterator.next();
		listIterator.remove();

		DLNode<Integer> head = list.getFront();
		DLNode<Integer> tail = list.getBack();

		assertEquals("Iterator failed to remove middle element", new Integer(2), head.getElement());
		assertEquals("Iterator failed to remove middle element", new Integer(4), head.getNext().getElement());
		assertEquals("Iterator failed to remove middle element", new Integer(5), head.getNext().getNext().getElement());
		assertEquals("Iterator failed to remove middle element", null, head.getNext().getNext().getNext());

		assertEquals("Iterator failed to remove middle element", new Integer(5), tail.getElement());
		assertEquals("Iterator failed to remove middle element", new Integer(4), tail.getPrevious().getElement());
		assertEquals("Iterator failed to remove middle element", new Integer(2), tail.getPrevious().getPrevious().getElement());
		assertEquals("Iterator failed to remove middle element", null, tail.getPrevious().getPrevious().getPrevious());

		/* testing removing the last element */
		while (listIterator.hasNext())
			listIterator.next();
		listIterator.remove();

		head = list.getFront();
		tail = list.getBack();

		assertEquals("Iterator failed to remove middle element", new Integer(2), head.getElement());
		assertEquals("Iterator failed to remove middle element", new Integer(4), head.getNext().getElement());
		assertEquals("Iterator failed to remove middle element", null, head.getNext().getNext());

		assertEquals("Iterator failed to remove middle element", new Integer(4), tail.getElement());
		assertEquals("Iterator failed to remove middle element", new Integer(2), tail.getPrevious().getElement());
		assertEquals("Iterator failed to remove middle element", null, tail.getPrevious().getPrevious());

		/* testing removing before calling next */
		listIterator = (ListIterator<Integer>) list.iterator();
		try {
			listIterator.remove();
			fail("Calling iterator's remove() before calling next() should throw an IllegalStateException");
		}
		catch (IllegalStateException e) {
			// all is good
		}
		catch (Exception e) {
			fail("The wrong exception thrown by the iterator remove() method.");
		}
	}
	
	@Test
	public void testListIteratorAdd() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();

		/* testing adding the first element though the iterator */
		ListIterator<Integer> listIterator = (ListIterator<Integer>) list.iterator();
		listIterator.add(1);
		assertEquals("Iterator failed to add first element", new Integer(1), list.getFront().getElement());
		
		/* testing adding the last element through the iterator */
		listIterator.add(2);
		assertEquals("Iterator failed to add last element", new Integer(2), list.getBack().getElement());
		
		/* testing adding an element to the middle through the iterator */
		listIterator.previous();
		listIterator.add(3);
		assertEquals("Iterator failed to add middle element", new Integer(3), list.getFront().getNext().getElement());
	}
	
	/**
	 * Tests the ScheduleSlot class
	 */
	@Test
	public void testScheduleSlot() {
		Job j = new Job(1, 1, 1, 1, 1);
		ScheduleSlot a = new ScheduleSlot(j, 4);
		assertEquals("Failed to create ScheduleSlot containing job", j, a.getJob());
		assertEquals("Failed to store start time", 4, a.getStartTime());
		a.setStartTime(3);
		assertEquals("Failed to set start time", 3, a.getStartTime());
	}
	
	/**
	 * Tests the Job class
	 */
	@Test
	public void testJob() {
		Job j = new Job(5, 4, 3, 2, 1);
		
		/* Testing constructor and getters */
		assertEquals("Failed to store ID", 5, j.getId());
		assertEquals("Failed to store Earliest Start", 4, j.getEarliestStart());
		assertEquals("Failed to store Deadline", 3, j.getDeadline());
		assertEquals("Failed to store Duration", 2, j.getDuration());
		assertEquals("Failed to store profit", 1, j.getProfit());
		
		/* Testing setters */
		j.setId(1);
		j.setEarliestStart(2);
		j.setDeadline(3);
		j.setDuration(4);
		j.setProfit(5);
		assertEquals("Failed to set ID", 1, j.getId());
		assertEquals("Failed to set Earliest Start", 2, j.getEarliestStart());
		assertEquals("Failed to set Deadline", 3, j.getDeadline());
		assertEquals("Failed to set Duration", 4, j.getDuration());
		assertEquals("Failed to set Profit", 5, j.getProfit());
		
		/* Testing equals() */
		Job a = new Job(1, 1, 1, 1, 1);
		Job b = new Job(1, 1, 1, 1, 1);
		assertTrue("Not returning true when given equal jobs", a.equals(b));
		b.setEarliestStart(2);
		assertFalse("Not returning false when given different jobs", a.equals(b));
		
		/* Testing compareTo() */
		assertEquals(-1, a.compareTo(b));
		assertEquals(1, b.compareTo(a));
		b.setEarliestStart(1);
		assertEquals(0, a.compareTo(b));
	}
}