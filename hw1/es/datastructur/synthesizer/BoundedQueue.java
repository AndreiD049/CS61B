package es.datastructur.synthesizer;

import java.util.Iterator;

/**
 * Interface that all bounded queues should implement.
 * @author Andrei Dimitrascu
 */
public interface BoundedQueue<T> extends  Iterable<T> {
    /** returns the size of the buffer. */
    int capacity();
    /** returns the number of items currently in the buffer. */
    int fillCount();
    /**
     * adds an item to the end of the buffer.
     * @param x element to be added.
     */
    void enqueue(T x);
    /**
     * removes an items from the front of the buffer.
     * @return the retrieved element
     */
    T dequeue();
    /**
     * @return (but does not delete) the items in front.
     */
    T peek();
    /**
     * Iterator.
     * @return the iterator object
     */
    Iterator<T> iterator();

    /** returns true when Queue is empty. */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /** returns true if there is no more space. */
    default boolean isFull() {
        return fillCount() == capacity();
    }
}
