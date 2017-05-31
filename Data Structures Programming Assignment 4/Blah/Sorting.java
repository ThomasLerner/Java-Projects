/**
 * @author Thomas Lerner
 *
 */
public class Sorting {

	// Swaps two values in an array
	public static void swap(int i, int x, int[] arr) {
		int temp = arr[i];
		arr[i] = arr[x];
		arr[x] = temp;
	}

	/*
	 * Methods dedicated to HeapSort begin here
	 * 
	 */

	// Executes functions needed to perform HeapSort and records running time
	public static long heapSort(int[] arr) {
		long startTime = System.nanoTime();
		int heapLength = arr.length - 1;
		// Turns array into sorted heap
		buildHeap(heapLength, arr);
		// Turns heap into sorted array
		for(int x = heapLength; x > 0; x--) {
			swap(0, x, arr);
			heapLength--;
			siftDown(0, heapLength, arr);
		}
		return(System.nanoTime() - startTime);
	}

	private static void buildHeap(int heapLength, int[] heap) {
		// Builds a max on top heap from an input array
		for (int i = heapLength / 2; i >= 0; i--) {
			siftDown(i, heapLength, heap);
		}
	}

	// Recursively sifts a value down the heap if it has children larger than itself
	public static void siftDown(int i, int heapLength, int[] heap){

		// Defining left and right children
		int left = (i * 2);
		int right = (i * 2) + 1;

		// Determining the largest child
		int largest;
		if(left <= heapLength && heap[left] > heap[i]) {
			largest = left;
		}
		else {
			largest = i;
		}
		if(right <= heapLength && heap[right] > heap[largest]) {
			largest = right;
		}
		// If node has children larger than itself, sift it down again
		if(largest != i) {
			swap(i, largest, heap);
			siftDown(largest, heapLength, heap);
		}
	}
	
	/*
	 * Methods dedicated to HeapSort end here
	 * 
	 * Methods dedicated to QuickSort begin here
	 * 
	 */
	
	// Executes functions needed to perform QuickSort and records running time
	public static long quickSort(int[] arr) {
		long startTime = System.nanoTime();
		myQuickSort(arr, 0, arr.length - 1);
		return(System.nanoTime() - startTime);
	}
	
	// Recursively partitions and swaps until array is sorted
	public static void myQuickSort(int[] arr, int first, int last) {
		int index = partition(arr, first, last);
		if(first < index - 1) {
			myQuickSort(arr, first, index - 1);
		}
		if(index < last) {
			myQuickSort(arr, index, last);
		}
	}
	
	// Chooses a pivot, then runs through subarrays on either side and swaps as needed until
	// indices overlap
	public static int partition(int[] arr, int first, int last) {
		int pivot = arr[(first + last) / 2];
		int left = first;
		int right = last;
		while(left <= right) {
			while(arr[left] < pivot) {
				left++;
			}
			while(arr[right] > pivot) {
				right--;
			}
			if(left <= right) {
				swap(left, right, arr);
				left++;
				right--;
			}
		}
		return left;
	}
	
	/*
	 * Methods dedicated to QuickSort end here
	 * 
	 * Methods dedicated to MergeSort begin here
	 * 
	 */
	
	// Executes functions needed to perform MergeSort and records running time
	public static long mergeSort(int[] arr) {
		long startTime = System.nanoTime();
		int[] temp = new int[arr.length];
		// Run through array in blocks
		for(int x = 1; x < arr.length; x *= 2) {
			// Combine blocks of length x
			for(int y = 0; y < arr.length; y += (2 * x)) {
				merge(arr, temp, y, y + x, y + (2 * x));
			}
			// Copy blocks back to array after merging
			for(int i = 0; i < arr.length; i++) {
				arr[i] = temp[i];
			}
		}
		return (System.nanoTime() - startTime);
	}
	
	// Merges the subarrays in a given range
	public static void merge(int[] arr, int[] temp, int left, int mid, int right) {
		// Verify that index is not out of range (out of range if block is not a power of 2
		// If it is, we still have to copy, so cannot just return
		if(mid > arr.length) {
			mid = arr.length;
		}
		if(right > arr.length) {
			right = arr.length;
		}
		// Create indexes based on ends of the blocks
		int i = left;
		int j = mid;
		// While a block has unsorted elements in it
		while(i < mid || j < right) {
			// If they both have unsorted elements, add to temp the lowest element
			if(i < mid && j < right) {
				if(arr[i] < arr[j]) {
					temp[left++] = arr[i++];
				}
				else {
					temp[left++] = arr[j++];
				}
			}
			// If the left block had no unsorted elements, add to temp from the right block
			else if(i == mid) {
				temp[left++] = arr[j++];
			}
			// If the right block was empty, add to temp from the left block
			else if(j == right) {
				temp[left++] = arr[i++];
			}
		}
	}
	
	
}