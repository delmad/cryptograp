package at.fhj.swd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class FileHandler {

	private String filePath;
	private String outputPath;
	private ArrayList<Character> charArray = new ArrayList<Character>();
	private ArrayList<String> inputLines = new ArrayList<String>();
	private static FileWriter writer = null;

	public FileHandler(String filePath_, String outputPath_) {

		this.filePath = filePath_;
		this.outputPath = outputPath_;
		try {
			this.writer = new FileWriter(outputPath_, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void readFile() {
		try {
			FileReader reader = new FileReader(filePath);
			BufferedReader bReader = new BufferedReader(reader);
			String input = null;
			while ((input = bReader.readLine()) != null) {
				inputLines.add(input);
				char[] line = input.toCharArray();
				for (int i = 0; i < line.length; i++) {
					charArray.add(line[i]);
				}
				charArray.add('\n');
			}
			bReader.close();

		} catch (FileNotFoundException ex1) {
			System.out.println("Die Datei konnte nicht gefunden werden!");
		} catch (IOException ex) {
			System.out.println("Datei kann nicht gelesen werden!");
		}
	}

	public void writeToFile(ArrayList<Character> outputText) {
		try {
			writer = new FileWriter(outputPath);
			for (Character c : outputText) {
				writer.write(c);
			}
			writer.close();
		} catch (IOException e) {

			System.out.println("Die Datei konnte nicht ausgegeben werden!");
		}

	}

	public void writeAnalyzedText(TreeMap<String, Integer> map, String letterOrWord_, double total) {
		DecimalFormat f = new DecimalFormat("#0.00");
		boolean letterOrWord = false;
		int count = 0;
		try {
			if (letterOrWord_.equals(Constants.TABLE_WORD_BEGIN)) {
				letterOrWord = true;
				writer.append(Constants.TABLE_WORD_BEGIN);
			}else{
				letterOrWord = false;
				writer.append(Constants.HTML_BEGIN);
				writer.append(Constants.TABLE_LETTER_BEGIN);
			}
			for (Map.Entry<String, Integer> entry : map.entrySet()) {
				String key = entry.getKey();
				Integer value = entry.getValue();
				writer.append(
						"<tr><td>" + key + "</td><td>" + Integer.toString(value) +
						"</td><td>" + f.format(((value/total)*100.0)) + "</td></tr>");
			if ((count > 24) && (letterOrWord)) {
					writer.append(Constants.HTML_END);
					writer.close();
					return;
				}
				count++;
			}
		} catch (IOException e) {

			System.out.println("Die Datei konnte nicht ausgegeben werden!");
		}
	}

	public ArrayList<Character> getCharArray() {
		return charArray;
	}

	public void setCharArray(ArrayList<Character> charArray) {
		this.charArray = charArray;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public ArrayList<String> getInputLines() {
		return inputLines;
	}

	public void setInputLines(ArrayList<String> inputLines) {
		this.inputLines = inputLines;
	}

}