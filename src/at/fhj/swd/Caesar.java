package at.fhj.swd;

import java.util.ArrayList;

public class Caesar {
	// ArrayList tipa Character
	private ArrayList<Character> encryptedList = new ArrayList<Character>();
	private ArrayList<Character> decryptedList = new ArrayList<Character>();
	private int cipherKey;

	public int getCipherKey() {
		return cipherKey;
	}

	// TODO: letterFrequency

	public void encrypt(ArrayList<Character> inputText) {
		char encryptedLetter;
		for (Character c : inputText) {
			if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z')) {
				if (Character.isUpperCase(c)) {
					encryptedLetter = (char) (((c + cipherKey - 'A') % 26) + 'A');
					encryptedList.add(encryptedLetter);
				} else {
					encryptedLetter = (char) (((c + cipherKey - 'a') % 26) + 'a');
					encryptedList.add(encryptedLetter);
				}
			} else {
				encryptedList.add(c);
			}

		}
	}

	public void decrypt(ArrayList<Character> inputText) {
		char decryptedLetter;
		int num = 26;
		for (Character c : inputText) {
			if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z')) {
				if (Character.isUpperCase(c)) {
					decryptedLetter = (char) (((c + (num - cipherKey) - 'A') % 26) + 'A');
					decryptedList.add(decryptedLetter);
				} else {
					decryptedLetter = (char) (((c + (num - cipherKey) - 'a') % 26) + 'a');
					decryptedList.add(decryptedLetter);
				}
			} else {
				decryptedList.add(c);
			}
		}
	}

	public void findKey(ArrayList<Character> encryptedText) {
		int encryptedLetter;
		int[] letterCounter = new int[26];
		int position = 0;
		int maxOccurency = 0;

		for (Character c : encryptedText) {
			if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z')) {
				if (Character.isUpperCase(c)) {
					encryptedLetter = (c - 'A');
					(letterCounter[encryptedLetter])++;

				} else {
					encryptedLetter = (c - 'a');
					(letterCounter[encryptedLetter])++;
				}
			}
		}

		for (int i = 0; i < letterCounter.length; i++) {
			if (letterCounter[i] > maxOccurency) {
				maxOccurency = letterCounter[i];
				position = i;
			}
		}

		if (position > 4) {
			setCipherKey((position + 65) - 69);
		} else if (position < 4) {
			setCipherKey(69 - (position + 65));
		} else {
			setCipherKey(0);
		}
	}

	public ArrayList<Character> getEncryptedList() {
		return encryptedList;
	}

	public void setEncryptedList(ArrayList<Character> encryptedList) {
		this.encryptedList = encryptedList;
	}

	public ArrayList<Character> getDecryptedList() {
		return decryptedList;
	}

	public void setDecryptedList(ArrayList<Character> decryptedList) {
		this.decryptedList = decryptedList;
	}

	public void setCipherKey(int cipherKey) {
		this.cipherKey = cipherKey;
	}

	public Caesar() {

	}

}
