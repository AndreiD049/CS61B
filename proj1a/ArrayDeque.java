import java.util.Arrays;

/**
 * My implementation of Array based Deque
 * @author Andrei Dimitrascu
 */
public class ArrayDeque<T> {
    private T[] items;
    private int size;
    /**
     * Necessary to handle addition to the front of the list.
     * When element is added to the front of the list
     * we don't move all the elements of the array to the right
     * instead, we move frontIndex to the left (in circular manner),
     * potentially to the end of the array
     */
    private int frontIndex;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        frontIndex = 0;
    }

    /**
     * Creates a deep copy of other
     * @param other - other array
     */
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.items.length];
        System.arraycopy(other.items, 0, items, 0, other.items.length);
        frontIndex = other.frontIndex;
        size = other.size;
    }

    /**
     * Adds an item to the front of the list.
     * Moves the frontIndex to the left instead of moving elements.
     * @param item - item to be added
     */
    public void addFirst(T item) {
        if (size >= items.length) {
            resize(items.length * 2);
        }
        frontIndex = frontIndex - 1 < 0 ? items.length - 1 : frontIndex - 1;
        items[frontIndex] = item;
        size += 1;
    }

    /**
     * Adds an item to end of the list.
     * @param item - element to be added
     */
    public void addLast(T item) {
        if (size >= items.length) {
            resize(items.length * 2);
        }
        items[size] = item;
        size += 1;
    }

    /**
     * Remove last element from list and return it.
     * If no such item, returns null
     * @return removed element
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (getUsageFactor() < 0.25) {
            resize(size * 2);
        }
        T result = items[size - 1];
        items[size - 1] = null;
        size -= 1;
        return result;
    }

    /**
     * Removes the element in front and returns is
     * If list is empty, returns null
     * @return the removed element
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (getUsageFactor() < 0.25) {
            resize(size * 2);
        }
        T removed = items[frontIndex];
        items[frontIndex] = null;
        size -= 1;
        frontIndex = (frontIndex + 1) % items.length;
        return removed;
    }

    /**
     * Get element at index.
     * @param index - index to look at
     * @return element at index
     */
    public T get(int index) {
        int shiftedIndex = (index + frontIndex) % items.length;
        return items[shiftedIndex];
    }

    /**
     * Get actual size of the Deque.
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns internal array length.
     * Used for tests
     * @return items array length
     */
    public int arraySize() {
        return items.length;
    }

    /**
     * Returns true if list is empty.
     * @return true/false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * prints the list elements, space separated.
     * Inserts a newline after last element
     */
    public void printDeque() {
        int itemIndex = frontIndex;
        for (int i = 0; i < size; i++) {
            System.out.printf("%s ", items[itemIndex].toString());
            itemIndex = (itemIndex + 1) % items.length;
        }
        System.out.println();
    }

    /**
     * Returns the usage factor of the list.
     * When usage factor drops below 25% (0.25),
     * list should be shrinked
     * @return the usage factor as a double
     */
    private double getUsageFactor() {
        return (double)size / items.length;
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        int srcStartIndex = frontIndex;
        int destStartIndex = 0;
        int srcItemsCount = size;
        if (srcStartIndex != 0) {
            int count = Math.min(items.length - srcStartIndex, capacity);
            System.arraycopy(items, srcStartIndex, newArray, destStartIndex, count);
            srcStartIndex = 0;
            destStartIndex += count;
            srcItemsCount -= count;
        }
        if (srcItemsCount > 0) {
            System.arraycopy(items, srcStartIndex, newArray, destStartIndex, srcItemsCount);
        }
        frontIndex = 0;
        items = newArray;
    }

    /**
     * Just for test
     */
    public static void main(String[] args) {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addFirst(5);
        System.out.println(list.getUsageFactor());
    }
}
