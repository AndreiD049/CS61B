/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        Palindrome palindrome = new Palindrome();

        int maxPalindromes = 0;
        int nMaxPalindromes = 0;
        String longestPalindrome = "";
        int longestPalindromeN = 0;

        for (int i = 0; i < 26; i++) {
            In in = new In("../library-sp19/data/words.txt");
            CharacterComparator cc = new OffByN(i);
            int countPalindromes = 0;

            while (!in.isEmpty()) {
                String word = in.readString();
                if (word.length() >= minLength && palindrome.isPalindrome(word, cc)) {
                    countPalindromes++;
                    if (word.length() > longestPalindrome.length()) {
                        longestPalindrome = word;
                        longestPalindromeN = i;
                    }
                }
            }
            if (countPalindromes > maxPalindromes) {
                maxPalindromes = countPalindromes;
                nMaxPalindromes = i;
            }
            in.close();
        }

        /* Print Summary */
        System.out.printf("Most palindromes are there for N - %d (%d palindromes):\n", nMaxPalindromes, maxPalindromes);
        In in = new In("../library-sp19/data/words.txt");
        CharacterComparator cc = new OffByN(nMaxPalindromes);
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, cc)) {
                System.out.println(word);
            }
        }
        System.out.println("==================================");
        System.out.printf("Longest palindrome is for N - %d, and it's %s\n", longestPalindromeN, longestPalindrome);
    }
}