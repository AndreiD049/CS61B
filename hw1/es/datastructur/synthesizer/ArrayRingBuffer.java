package es.datastructur.synthesizer;
import java.util.Iterator;

/** Array Ring buffer data structure.
 * @author Andrei Dimitrascu
 */
public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /** Index for the next dequeue or peek. */
    private int first;
    /** Index for the next enqueue. */
    private int last;
    /** Variable for the fillCount. */
    private int fillCount;
    /** Array for storing the buffer data. */
    private final T[] rb;

    /** Ring Buffer iterator inner class. */
    private class RingBufferIterator implements Iterator<T> {
        /** current element processed by iterator. */
        private int currentEl;

        /** Construct a new Iterator. */
        RingBufferIterator() {
            currentEl = 0;
        }

        @Override
        public boolean hasNext() {
            return currentEl < fillCount;
        }

        @Override
        public T next() {
            T result = rb[wrapIndex(first + currentEl)];
            currentEl += 1;
            return result;
        }
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     * @param capacity - maximum capacity of the array
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        fillCount = 0;
        first = 0;
        last = 0;
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer.
     * if no room, throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        last = wrapIndex(last + 1);
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T result = rb[first];
        first = wrapIndex(first + 1);
        fillCount -= 1;
        return result;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    /**
     * returns the wrapped index if it goes out of bounds.
     * Ex: if currentIndex is 5 and capacity is 5, then returns 0
     * Ex: if currentIndex is 6 and capacity is 5,
     * then returns 1 (should not happen)
     * @param currentIndex new index
     */
    private int wrapIndex(int currentIndex) {
        return currentIndex >= rb.length
                ? currentIndex % rb.length
                : currentIndex;
    }

    @Override
    public Iterator<T> iterator() {
        return new RingBufferIterator();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> otherArray = (ArrayRingBuffer<T>) other;
        if (this.fillCount != otherArray.fillCount) {
            return false;
        }
        for (int i = 0; i < this.fillCount; i += 1) {
            T el1 = this.rb[wrapIndex(i + first)];
            T el2 = otherArray.rb[otherArray.wrapIndex(i + otherArray.first)];
            if (!el1.equals(el2)) {
                return false;
            }
        }
        return true;
    }
}
