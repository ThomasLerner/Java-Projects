import java.util.Arrays;
import java.util.Random;

/**
 * @author Thomas Lerner
 *
 */
public class Reporting1 {

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		// Finds data for array sizes 1,000, 10,000, 100,000, and 1,000,000 or all sorts
		for(int x = 1000; x <= 1000000; x *= 10) {
			findData("QuickSort", x);
			findData("HeapSort", x);
			findData("MergeSort", x);
		}
		System.out.println("\n \n Total running time: " + 
				((System.nanoTime() - startTime)/(1000000000.0)) + " Seconds");
	}

	// Finds median of an input array by sorting it, then choosing middle value
	public static long findMedian(long[] arr) {
		Arrays.sort(arr);
		return arr[1];	
	}

	// Finds mean of an input array usinc classic style
	public static double meanVal(long[] arr) {
		long mean = 0;
		for(int x = 0; x < arr.length; x++) {
			mean += arr[x];
		}
		mean /= arr.length;
		return mean;
	}
	
	// Finds variance of an input array using given formula
	public static double varianceVal(long[] arr, double mean) {
		long variance = 0;
		for(int x = 0; x < arr.length; x++) {
			variance += (arr[x] - mean) * (arr[x] - mean);
		}
		variance /= arr.length - 1.0;
		return variance;
	}

	// Generates an array of given length with ordered contents
	public static int[] sortedArrayGenerator(int size) {
		int[] arr = new int[size];
		for(int x = 0; x < size; x++) {
			arr[x] = x;
		}
		return arr;
	}

	// Generates an array of given length with reverse ordered contents
	public static int[] reverseArrayGenerator(int size) {
		int[] arr = new int[size];
		for(int x = 0; x < size; x++) {
			arr[x] = size - (x + 1);
		}
		return arr;
	}

	// Generates an array of given length with randomly ordered contents, resetting seed using
	// system time every time loop iterates
	public static int[] randomArrayGenerator(int size){
		int[] arr = new int[size];
		for(int x = 0; x < size; x++){
			arr[x] = new Random().nextInt();
		}
		return arr;
	}


	// Finds the median running time for three ordered and reverse order arrays of given size
	// using given algorithm, then finds mean and variance of running time for ten randomly
	// ordered arrays of given size using given algorithm
	public static void findData(String s, int size) {
		if(s.equals("QuickSort")) {
			long[] sortedResults = new long[3];
			long[] reverseResults = new long[3];
			long[] randomResults = new long[10];
			// Calculates and stores three running times for sorted and reverse sorted arrays
			for(int x = 0; x < 3; x++) {
				sortedResults[x] = Sorting.quickSort(sortedArrayGenerator(size));
				reverseResults[x] = Sorting.quickSort(reverseArrayGenerator(size));
			}
			// Calculates and stores ten running times for randomized arrays
			for(int x = 0; x < 10; x++) {
				randomResults[x] = Sorting.quickSort(randomArrayGenerator(size));
			}
			//  Prints data in nice looking format
			System.out.println("QuickSort of array size " + size);
			System.out.println("Median for ordered: " + findMedian(sortedResults) + "ns");
			System.out.println("Median for reversed order: " + findMedian(reverseResults) + "ns");
			double mean = meanVal(randomResults);
			System.out.println("Mean for random order: " + mean + "ns");
			System.out.println("Variance for random order: " + varianceVal(randomResults, mean) + "ns");
			System.out.print("\n \n");
		}
		// These next two statements are exactly the same, but for different sorts
		else if(s.equals("HeapSort")) {
			long[] sortedResults = new long[3];
			long[] reverseResults = new long[3];
			long[] randomResults = new long[10];

			for(int x = 0; x < 3; x++) {
				sortedResults[x] = Sorting.heapSort(sortedArrayGenerator(size));
				reverseResults[x] = Sorting.heapSort(reverseArrayGenerator(size));
			}
			for(int x = 0; x < 10; x++) {
				randomResults[x] = Sorting.heapSort(randomArrayGenerator(size));
			}
			System.out.println("HeapSort of array size " + size);
			System.out.println("Median for ordered: " + findMedian(sortedResults) + "ns");
			System.out.println("Median for reversed order: " + findMedian(reverseResults) + "ns");
			double mean = meanVal(randomResults);
			System.out.println("Mean for random order: " + mean + "ns");
			System.out.println("Variance for random order: " + varianceVal(randomResults, mean) + "ns");
			System.out.print("\n \n");
		}
		else if(s.equals("MergeSort")) {
			long[] sortedResults = new long[3];
			long[] reverseResults = new long[3];
			long[] randomResults = new long[10];

			for(int x = 0; x < 3; x++) {
				sortedResults[x] = Sorting.mergeSort(sortedArrayGenerator(size));
				reverseResults[x] = Sorting.mergeSort(reverseArrayGenerator(size));
			}
			for(int x = 0; x < 10; x++) {
				randomResults[x] = Sorting.mergeSort(randomArrayGenerator(size));
			}
			System.out.println("MergeSort of array size " + size);
			System.out.println("Median for ordered: " + findMedian(sortedResults) + "ns");
			System.out.println("Median for reversed order: " + findMedian(reverseResults) + "ns");
			double mean = meanVal(randomResults);
			System.out.println("Mean for random order: " + mean + "ns");
			System.out.println("Variance for random order: " + varianceVal(randomResults, mean) + "ns");
			System.out.print("\n \n");
		}
	}
}