import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Thomas Lerner
 *
 */
public class HuffmanCompressor {
	public static HashMap<Character, String> map = new HashMap<Character, String>();
	public static ArrayList<HuffmanNode> nodeArrList = new ArrayList<HuffmanNode>();
	public static FreqData[] freqArr = new FreqData[255];
	
	/*
	 * Generates an arraylist of HuffmanNodes from a text file
	 */
	public static ArrayList<HuffmanNode> nodeGenerator(String inputName, String outputName) throws IOException {
		FileReader input = new FileReader(inputName);
		BufferedReader reader = new BufferedReader(input);
		
		int data;
		
		while((data = reader.read()) != -1) {
			if(data < 256) {
				if(freqArr[data] != null) {
					freqArr[data].frequency++;
				}
				else {
					freqArr[data] = new FreqData((char) data, 0);
				}
			}
		}
		
		reader.close();
				
		for(int x = 0; x < freqArr.length; x++) {
			if(freqArr[x] != null) {
				nodeArrList.add(new HuffmanNode(freqArr[x].data, freqArr[x].frequency));
			}
		}
		
		bubbleSort();
		
		return nodeArrList;
	}
	
	/*
	 * BubbleSort helper method
	 */
	public static void bubbleSort() {
		for(int i = nodeArrList.size() -1; i > 0; i--) {
			for(int j = 0; j < i; j++) {
				if(nodeArrList.get(j).frequency > nodeArrList.get(j +1).frequency) {
					HuffmanNode temp = nodeArrList.get(j);
					nodeArrList.set(j, nodeArrList.get(j + 1));
					nodeArrList.set(j + 1, temp);
				}
			}
		}
	}
	
	/*
	 * Turns an arraylist of HuffmanNodes into a Huffman Trree
	 */
	public static HuffmanNode huffmanAlgo() {
		HuffmanNode root;
		
		while(nodeArrList.size() > 1) {
			if(nodeArrList.get(0).frequency == 0) {
				nodeArrList.remove(0);
			}
			else {
				HuffmanNode left = nodeArrList.remove(0);
				HuffmanNode right = nodeArrList.remove(0);
				HuffmanNode replacement = new HuffmanNode(null, left.frequency + right.frequency);
				replacement.left = left;
				replacement.right = right;
				nodeArrList.add(replacement);
			}
		}
		root = nodeArrList.get(0);
		return root;
	}
	
	/*
	 * Traverses the Huffman Tree, recording the encoding and character value of leaf nodes.
	 */
	public static void huffmanTraverser(HuffmanNode root, String s) {
		if(root == null) {
			return;
		}
		if(root.left == null && root.right == null) {
			map.put(root.inChar, s);
		}
		
		huffmanTraverser(root.left, s + "0");
		huffmanTraverser(root.right, s + "1");
	}
	
	/**
	 * Reads the input file and converts it to the huffman codes
	 * 
	 * @param inputName
	 * @param outputName
	 * @throws IOException
	 */
	public static void outputAndStatistics(String inputName, String outputName) throws IOException {
		FileReader input = new FileReader(inputName);
		BufferedReader reader = new BufferedReader(input);
		StringBuilder output = new StringBuilder();

		int x;

		while((x = reader.read()) != -1) {
			if(x < 256) {
				output.append(map.get((char)x));
			}
		}
		reader.close();
		
		File file = new File(outputName);
		file.createNewFile();
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(output.toString());
		writer.close();
		
		int sum = 0;
		for(Character j : map.keySet()) {
			if(map.containsKey('j')) {
				sum += map.get('j').length() * freqArr[j].frequency;
			}
		}
		
		double savings = (1.0 - ((sum / nodeArrList.get(0).frequency) / 8.0)) * 100.0;
		
		String statisticsString = "Savings: " + savings + "% \n \n" + map.toString();
		
		File file2 = new File("huffmanStatistics.txt");
		file2.createNewFile();
		BufferedWriter writer2 = new BufferedWriter(new FileWriter(file2));
		writer2.write(statisticsString);
		writer2.close();
		
		System.out.println(statisticsString);
	}
	
	public static void main(String[] args) throws IOException {
		nodeGenerator(args[0], args[1]);
		huffmanTraverser(huffmanAlgo(), "");
		outputAndStatistics(args[0], args[1]);
	}
	
}
