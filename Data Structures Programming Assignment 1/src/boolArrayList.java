import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author Thomas Lerner
 *
 */
public class boolArrayList implements boolList {
	
	int counter = 0;
	boolean[] arr = new boolean[10];
	
	@Override
	public int size() {
		return counter;
	}

	@Override
	public void insert(int i, boolean value) {
		int arrLength = arr.length;
		if(arrLength == counter) {
			boolean[] newArr = new boolean[arr.length + 10];
			for(int x = 0; x < arrLength; x++) {
				newArr[x] = arr[x];
			}
			arr = newArr;
		}
		if(counter > i) {
			boolean replaceWith = value;
			boolean toReplace;
			for(int x = i; x < counter; x++) {
				toReplace = arr[x];
				arr[x] = replaceWith;
				replaceWith = toReplace;
			}
		}
		else {
			arr[i+1] = value;
		}
		counter++;
	}

	@Override
	public void remove(int i) {
		if(counter > i) {
			for(int x = i; x < counter; x--) {
				arr[x] = arr[x+1];
			}
			counter--;
		}
	}

	@Override
	public boolean lookup(int i) {
		if(counter >= i) {
			return arr[i];
		}
		else {
			throw new NullPointerException();
		}
	}

	@Override
	public boolean negateAll() {
		if(counter != 0) {
			for(int x = 0; x < counter; x++) {
				arr[x] = !arr[x];
			}
			return true;
		}
		else {
			throw new NullPointerException();
		}
	}
	
	@Override
	public Iterator<Boolean> iterator() {
		return new ListIterator<Boolean>(){

			@Override
			public void add(Boolean arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean hasPrevious() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Boolean next() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int nextIndex() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Boolean previous() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int previousIndex() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void set(Boolean arg0) {
				// TODO Auto-generated method stub
				
			}
		}
	}

}