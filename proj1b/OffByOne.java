public class OffByOne implements CharacterComparator {

    /**
     * Return true of characters are off by 1
     * @param x - character 1
     * @param y - character 2
     * @return true if chars are off by 1
     */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == 1;
    }
}
