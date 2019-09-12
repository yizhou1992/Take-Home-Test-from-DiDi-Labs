
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Word_Anagram {

	private Set<String> dict;

	public Word_Anagram(String path) throws FileNotFoundException {

		dict = new HashSet<>();

		File file = new File(path);
		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			dict.add(line);
		}
		scanner.close();
	}

	public Set<String> getAllWords(String characters) {
		Set<String> ans = new HashSet<>();
		boolean[] used = new boolean[characters.length()];
		backtrack(ans, "", characters.toCharArray(), used);
		return ans;

	}

	private void backtrack(Set<String> finalAnsList, String word, char[] charList, boolean[] used) {

		if (word.length() > charList.length) {
			return;
		}
		if (dict.contains(word)) {
			finalAnsList.add(word);
		}

		for (int i = 0; i < charList.length; i++) {
			if (used[i]) {
				continue;
			}
			used[i] = true;
			backtrack(finalAnsList, word + charList[i], charList, used);
			used[i] = false;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {

		
		String dict = args[0];
		String testCases = args[1];

		Word_Anagram word_Anagram = new Word_Anagram(dict);

		File file = new File(testCases);
		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			System.out.println("Input characters :" + line);
			System.out.println("Possible  words:");
			System.out.println(word_Anagram.getAllWords(line));
			System.out.println("");
		}
		scanner.close();

	}
}
