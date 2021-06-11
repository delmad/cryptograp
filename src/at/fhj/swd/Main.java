package at.fhj.swd;

import java.awt.event.InputMethodListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {

		Caesar caesar = new Caesar();
		FileHandler fh;
		FileHandler fhOut;
		Analyze analyze = new Analyze();

		if (args.length == 5 && args[0].equalsIgnoreCase("-key") && args[2].equalsIgnoreCase("-caesar")) {
			caesar.setCipherKey(Integer.parseInt(args[1]));
			fh = new FileHandler(args[3], args[4]);
			fh.readFile();
			analyze.countLetters(fh.getCharArray());
			caesar.encrypt(fh.getCharArray());
			fh.writeToFile(caesar.getEncryptedList());
		} else if (args.length == 2 && args[0].equalsIgnoreCase("-findkey")) {
			fh = new FileHandler(args[1], null);
			fh.readFile();
			caesar.findKey(fh.getCharArray());
			System.out.println("Der Schlüssel ist: " + caesar.getCipherKey());
		} else if (args.length == 3 && args[0].equalsIgnoreCase("-f")) {
			fh = new FileHandler(args[1], args[2]);
			fh.readFile();
			/*
			 * fhOut = new FileHandler(args[2]); fh.setFilePath(args [1]);
			 */

			// analyze.countWords(fh.getInputLines());
			analyze.countLetters(fh.getCharArray());
			analyze.prepareList(fh.getInputLines());
			analyze.prepareLetterMap();
			analyze.countWords();
			analyze.calcLetters();
			

			TreeMap<String, Integer> sortedMapWords = analyze.sortMapByValue(analyze.getWordCount());
			TreeMap<String, Integer> sortedMapLetters = analyze.sortMapByValue(analyze.getLetterCount());
			fh.writeAnalyzedText(sortedMapLetters, Constants.TABLE_LETTER_BEGIN, analyze.getTotalLetters());
			fh.writeAnalyzedText(sortedMapWords, Constants.TABLE_WORD_BEGIN, analyze.getTotalWords());

		}
	}

}
