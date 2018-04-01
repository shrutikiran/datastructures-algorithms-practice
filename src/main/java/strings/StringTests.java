package strings;

/**
 * Created by kirn on 4/1/18.
 */
public class StringTests {
    public static void main(String[] args) {
        String inputString = "abc";
        System.out.println("print k-ary strings: " + inputString);
        printPermutations(inputString, "");
        System.out.println();

        System.out.println("print anagram strings: " + inputString);
        printAnagrams(inputString, "", 0);
        System.out.println();
    }

    public static void printPermutations(String inputSet, String permutation) {
        if (permutation == null || inputSet == null || inputSet.length() <= 0) {
            return;
        }

        if (permutation.length() == inputSet.length()) {
            System.out.println(permutation);
            return;
        }

        for (int i = 0; i < inputSet.length(); i++) {
            printPermutations(inputSet, permutation + inputSet.charAt(i));
        }
    }

    public static void printAnagrams(String inputSet, String anagram, int usedCharacters) {
        if (anagram == null || inputSet == null || inputSet.length() <= 0) {
            return;
        }

        if (anagram.length() == inputSet.length()) {
            System.out.println(anagram);
            return;
        }

        for (int i = 0; i < inputSet.length(); i++) {
            int currentCharFlag = (1 << (inputSet.charAt(i) - 'a'));
            if ((usedCharacters & currentCharFlag) == 0) {
                printAnagrams(inputSet, anagram + inputSet.charAt(i), (usedCharacters | currentCharFlag));
            }
        }
    }
}
