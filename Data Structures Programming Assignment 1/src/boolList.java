import java.util.Iterator;

/**
 * @author Thomas Lerner
 *
 */
public interface boolList {
	
	int size();
	
	void insert(int i, boolean value);
	
	void remove(int i);
	
	boolean lookup(int i);
	
	boolean negateAll();

	Iterator<Boolean> iterator();
	
}