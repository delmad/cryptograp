package at.fhj.swd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Analyze {
	private int[] letterOccurency = new int[26];
	private ArrayList<String> wordsOccurency = new ArrayList<String>();
	private HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
	private HashMap<String, Integer> letterCount = new HashMap<String, Integer>();
	private double totalLetters = 0.0;
	private double totalWords = 0.0;
	
	public Analyze() {

	}

	public void countLetters(ArrayList<Character> inputText) {
		int encryptedLetter;
		for (Character c : inputText) {
			if (('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z')) {
				if (c.isUpperCase(c)) {
					encryptedLetter = c - 'A';
					(letterOccurency[encryptedLetter])++;
				} else {
					encryptedLetter = c - 'a';
					(letterOccurency[encryptedLetter])++;
				}
			}
		}
	}

	public void countWords() {
			
		for (String word : wordsOccurency) {
			int wordFrequency = Collections.frequency(wordsOccurency, word);
			if (!(wordCount.containsKey(word))) {
				wordCount.put(word, wordFrequency);
			}
			totalWords++;
		}

	}

	public void prepareList(ArrayList<String> inputList_) {
		for (String s : inputList_) {
			String clearedList = s.replaceAll("[^a-zA-Z]", " ");
			String[] arrayWord = clearedList.split(" ");

			for (int i = 0; i < arrayWord.length; i++) {
				if (arrayWord[i].length() > 3) {
					String lowerArrayWord = arrayWord[i].toLowerCase();
					wordsOccurency.add(lowerArrayWord);
				}

			}
		}
	}
	
	public static TreeMap<String, Integer> sortMapByValue(HashMap<String, Integer> map){
		Comparator<String> comparator = new MyComparator(map);
		TreeMap<String, Integer> result = new TreeMap<String, Integer>(comparator);
		result.putAll(map);
		return result;
	}
	
	public void prepareLetterMap(){
		for(int i = 0; i < letterOccurency.length; i++){
			char encryptedLetter = (char )(i + 'a');
			letterCount.put(Character.toString(encryptedLetter), letterOccurency[i]);
		}
	}
	
	public void calcLetters(){
		
		for (Map.Entry<String, Integer> entry : letterCount.entrySet()){
			String key = entry.getKey();
			Integer val = entry.getValue();
			totalLetters += val;
		}
		
	}
	
	
	public double getPercentLetters(int value){
		return ((value/totalLetters) * 100);
	}

	public int[] getLetterOccurency() {
		return letterOccurency;
	}

	public void setLetterOccurency(int[] letterOccurency) {
		this.letterOccurency = letterOccurency;
	}

	public ArrayList<String> getWordsOccurency() {
		return wordsOccurency;
	}

	public void setWordsOccurency(ArrayList<String> wordsOccurency) {
		this.wordsOccurency = wordsOccurency;
	}

	public HashMap<String, Integer> getWordCount() {
		return wordCount;
	}

	public void setWordCount(HashMap<String, Integer> wordCount) {
		this.wordCount = wordCount;
	}

	public double getTotalLetters() {
		return totalLetters;
	}

	public void setTotalLetters(double totalLetters) {
		this.totalLetters = totalLetters;
	}

	public HashMap<String, Integer> getLetterCount() {
		return letterCount;
	}

	public void setLetterCount(HashMap<String, Integer> letterCount) {
		this.letterCount = letterCount;
	}

	public double getTotalWords() {
		return totalWords;
	}

	public void setTotalWords(double totalWords) {
		this.totalWords = totalWords;
	}


}
