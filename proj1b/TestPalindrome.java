import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindromeOdd() {
        boolean isTrue = palindrome.isPalindrome("racecar");
        assertTrue(isTrue);

        boolean isFalse = palindrome.isPalindrome("horse");
        assertFalse(isFalse);
    }

    @Test
    public void testIsPalindromeEven() {
        boolean isTrueEven = palindrome.isPalindrome("noon");
        assertTrue(isTrueEven);

        boolean isFalseEven = palindrome.isPalindrome("rancor");
        assertFalse(isFalseEven);
    }

    @Test
    public void testIsPalindromeCornerCase() {
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
    }

}