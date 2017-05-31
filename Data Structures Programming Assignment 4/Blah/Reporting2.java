import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Thomas Lerner
 *
 */
public class Reporting2 {

	public static void main(String[] args) throws IOException {
		long[] QSTimes = new long[3];
		long[] HSTimes = new long[3];
		long[] MSTimes = new long[3];
		int[] QSResults;
		int[] HSResults;
		int[] MSResults;
		
		// Performs and records three tests for each sorting algorithm and writes the first tests to a file
		for(int x = 0; x < 3; x++) {
			// If this is the first test, write the results to a file
			if(x == 0) {
				QSTimes[x] = Sorting.quickSort(QSResults = Arrays.copyOf(readFile(args[0]), readFile(args[0]).length));
				HSTimes[x] = Sorting.heapSort(HSResults = Arrays.copyOf(readFile(args[0]), readFile(args[0]).length));
				MSTimes[x] = Sorting.mergeSort(MSResults = Arrays.copyOf(readFile(args[0]), readFile(args[0]).length));
				writeFile("QS", QSResults);
				writeFile("HS", HSResults);
				writeFile("MS", MSResults);
			}
			else {
				QSTimes[x] = Sorting.quickSort(readFile(args[0]));
				HSTimes[x] = Sorting.heapSort(readFile(args[0]));
				MSTimes[x] = Sorting.mergeSort(readFile(args[0]));
			}

		}
		// Prints properly formatted results to console
		System.out.print("HS tsl26 = " + Reporting1.findMedian(HSTimes) +
				"ns; QS tsl26 = " + Reporting1.findMedian(QSTimes) +
				"ns; MS tsl26 = " + Reporting1.findMedian(MSTimes) + "ns"
		);
	}

	// Converts an input file into an array of ints
	public static int[] readFile(String input_file) throws IOException {
		// Used arraylist because size of array is not known
		ArrayList<Integer> arrList = new ArrayList<Integer>();

		String s;
		FileReader input = new FileReader(input_file);
		BufferedReader reader = new BufferedReader(input);
		// Reads a line and adds it to the arraylist
		while((s = reader.readLine()) != null) {
			arrList.add(Integer.parseInt(s));
		}
		reader.close();
		// Transcribes the arraylist to an array
		int[] arr = new int[arrList.size()];
		for (int i=0; i < arr.length; i++) {
			arr[i] = arrList.get(i).intValue();
		}
		return arr;
	}
	
	// Writes a new file for the given sorting algorithm and array
	public static void writeFile(String testType, int[] arr) throws IOException {
		File file = new File(testType + " tsl26.txt");
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for(int x : arr) {
			writer.write(x + System.getProperty("line.separator"));
		}
		writer.close();
	}


}
