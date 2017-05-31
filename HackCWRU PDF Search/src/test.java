
/**
 * @author Thomas Lerner
 *
 */
public class test {
	
	// We don't need to call this method using another recursively because there is no
	// need to sort the two sides once they are sorted, since they can only have one value
	
	void redBlueQuicksort(SomeObject[] arr) {
		int left = 0;
		int right = arr.length-1;
		// We don't need a pivot because there is nothing to compare; there are only ever two possible values

		while (left <= right) {
			while(arr[left].getKey().equals("red") && left < arr.length - 1) {
				left++;
			}
			while(arr[right].getKey().equals("blue") && right > 0) {
				right--;
			}
			if(left <= right) {
				SomeObject temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				left++;
				right--;
			}
		}
	}
	
	void executeStringCode(String s) {
		
	}
	
	
	
	
	public static void main(String[] args) {
		SomeObject[] arr = {new SomeObject("blue"), new SomeObject("blue"), new SomeObject("blue"), 
				new SomeObject("blue"), new SomeObject("blue"), new SomeObject("blue")};
		
		test t = new test();
		t.redBlueQuicksort(arr);
		
		for(SomeObject y : arr) {
			System.out.print(y.getKey() + ", ");
		}
	}
}
