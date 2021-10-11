/**
 * @author  Andrei Dimitrascu
 * @param <T>
 */
interface Deque<T> {
    void addFirst(T item);
    void addLast(T item);
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

}
