import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
        str1 = preProcess(str1);
        str2 = preProcess(str2);

        Map<Character, Integer> charCount1 = mapString(str1);
        Map<Character, Integer> charCount2 = mapString(str2);

        return charCount1.equals(charCount2);
    }

    private static Map<Character, Integer> mapString(String str) {
        HashMap<Character, Integer> charCount = new HashMap<>();

        for (char c : str.toCharArray()) {
            if (c == ' ') continue;

            if (charCount.containsKey(c)) {
                charCount.put(c, charCount.get(c) + 1);
                continue;
            }

            charCount.put(c, 1);
        }

        return charCount;
    }
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		return str.toLowerCase()
                .replaceAll("[^a-z ]", "");
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		Map<Character, Integer> charCount = mapString(preProcess(str));

        String anagram = "";

        while (!charCount.isEmpty()) {
            int randIndex = (int) (Math.random() * charCount.size());
            Character randChar = (Character)charCount.keySet().toArray()[randIndex];
            int randCount = charCount.get(randChar);

            anagram += randChar;

            if (randCount == 1) {
                charCount.remove(randChar);
            } else {
                charCount.put(randChar, randCount - 1);
            }
        }

		return anagram;
	}
}
