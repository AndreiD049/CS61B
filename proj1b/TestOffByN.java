import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestOffByN {

    public static Palindrome palindrome = new Palindrome();

    @Test
    public void testOffByN() {
        CharacterComparator cc = new OffByN(5);
        assertTrue(palindrome.isPalindrome("a", cc));
        assertTrue(palindrome.isPalindrome("af", cc));
        assertTrue(palindrome.isPalindrome("azuf", cc));

        assertFalse(palindrome.isPalindrome("ab", cc));
        assertFalse(palindrome.isPalindrome("abff", cc));
        assertFalse(palindrome.isPalindrome("abaff", cc));
    }
}
