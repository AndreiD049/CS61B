public class Palindrome {

    /**
     * Creates a Deque of characters from a string.
     * @param word - given string
     * @return the Deque data structure
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            result.addLast(word.charAt(i));
        }
        return result;
    }

    /**
     * Checks whether the given word is a palindrome
     * @param word the given string
     * @return true if word is a palindrome, false otherwise
     */
    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }

    /**
     * Private helper method for isPalindrome(String word).
     * Uses recursion
     * @param deque - the deque to be checked
     * @return whether the deque represents a palindrome
     */
    private boolean isPalindrome(Deque<Character> deque) {
        if (deque.size() <= 1) {
            return true;
        }
        Character first = deque.removeFirst();
        Character last = deque.removeLast();
        if (!first.equals(last)) {
            return false;
        }
        return isPalindrome(deque);
    }

}
