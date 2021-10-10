/**
 * Deque data structure from Project1A of CS61B.
 * @author Andrei Dimitrascu
 * @param <T> the type of items stored in the deque
 */
public class LinkedListDeque<T> {
    /**
     * Node nested class.
     */
    private class Node {
        /** previous node. */
        private Node prev;
        /** node value. */
        private T item;
        /** next node. */
        private Node next;

        /**
         * Node constructor, set the value, next and prev pointers.
         * @param x - value of the node
         * @param prevNode - pointer to previous node
         * @param nextNode - pointer to next node
         */
        Node(T x, Node prevNode, Node nextNode) {
            item = x;
            prev = prevNode;
            next = nextNode;
        }

        /**
         * Node string representation.
         * @return string representation
         */
        @Override
        public String toString() {
            return item.toString();
        }
    }

    /**
     * This is a circular sentinel.
     * If list is empty, sentinel.next is sentinel itself,
     * and sentinel.prev is the sentinel itself
     */
    private Node sentinel;
    /** Cached size of the deque. */
    private int size;

    /**
     * Creates an empty Deque list.
     */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * Create a list of size 1.
     * @param item - first item of the list
     */
    public LinkedListDeque(T item) {
        sentinel = new Node(null, null, null);
        Node addedNode = new Node(item, sentinel, sentinel);
        sentinel.next = addedNode;
        sentinel.prev = addedNode;
        size = 1;
    }

    /**
     * Create a deep copy of other list.
     * changes to other should not affect the created list and vice versa
     * @param other - other list to be copied
     */
    public LinkedListDeque(LinkedListDeque other) {
        /* Create an empty list first */
        this();
        Node node = other.sentinel.next;
        for (int i = 0; i < other.size(); i++) {
            this.addLast(node.item);
            node = node.next;
        }
    }

    /**
     * Add an item to the Deque.
     * Sentinel.next should start pointing at it
     * @param item - item to be added
     */
    public void addFirst(T item) {
        Node addedNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = addedNode;
        sentinel.next = addedNode;
        if (isEmpty()) {
            sentinel.prev = sentinel.next;
        }
        size += 1;
    }

    /**
     * Adds item to the end of the deque
     * Sentinel.prev starts pointing at this item.
     * item's next starts pointing at the sentinel
     * previously last item's .next starts pointing at this item
     * items.prev starts poiting at previously last item
     * @param item - item to be added
     */
    public void addLast(T item) {
        Node oldLast = sentinel.prev;
        Node newLast = new Node(item, oldLast, sentinel);
        sentinel.prev = newLast;
        oldLast.next = newLast;
        size += 1;
    }

    /**
     * Insert an item at index.
     * S <--> 1 <--> 3 <--> 4
     * S <--> 1 (<--> 2) <--> 3 <--> 4
     * 2.next will be set to 3
     * 2.prev will be set to 3.prev
     * 3.prev will be set to 2
     * 2.prev.next will be set to 2
     * if index is past list size, call addLast
     * @param item - item to be inserted
     * @param index - index at which to insert the item
     */
    public void insert(T item, int index) {
        if (index >= size) {
            addLast(item);
            return;
        }
        Node current = getNode(index);
        Node newItem = new Node(item, current.prev, current);
        current.prev = newItem;
        newItem.prev.next = newItem;
        size += 1;
    }

    /**
     * Removes and returns the first item. If no such item exists, return null
     * S <--> 1 <--> 2
     * S.next --> 2
     * 2.prev <-- S
     * @return
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node toRemove = sentinel.next;
        sentinel.next = toRemove.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return toRemove.item;
    }

    /**
     * Removes last element and returns it.
     * If there is no last, return null
     * S <--> 1 <--> 2 <--> 3 -.
     * |____________________|
     * S.prev starts pointing at 2 (3.prev)
     * 2.next start pointing at sentinel
     * @return
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node toRemove = sentinel.prev;
        sentinel.prev = toRemove.prev;
        toRemove.prev.next = sentinel;
        size -= 1;
        return toRemove.item;
    }

    /**
     * Returns true is there are items in the
     * list (besides sentinel node), else false.
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Just return the hashed size.
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Get the item at index.
     * @param index - zero based
     * @return the item at index or null if item doesn't
     * exist or index is negative
     */
    public T get(int index) {
        return getNode(index).item;
    }

    /**
     * ! Internal use: get node at position index.
     * @param index - index to search
     * @return the Node at index or null if index is invalid
     */
    private Node getNode(int index) {
        if (size <= index || index < 0) {
            return null;
        }
        Node node = sentinel.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * Reverses the list.
     * Doesn't allocate new nodes
     * S <--> 3 <--> 2 <--> 1.
     * |__________________|
     * 1 <--> 2 <--> 3 <--> S
     * |____________________|
     */
    public void reverse() {
        Node p = sentinel;
        for (int i = 0; i <= size; i++) {
            Node next = p.next;
            p.next = p.prev;
            p.prev = next;
            p = next;
        }
    }

    /**
     * Reverse the list recursively.
     */
    public void reverseRecursive() {
        reverseRecursiveHelper(sentinel.next);
    }

    public void reverseRecursiveHelper(Node p) {
        Node next = p.next;
        p.next = p.prev;
        p.prev = next;
        if (p == sentinel) {
            return;
        }
        reverseRecursiveHelper(next);
    }

    /**
     * Get item at index, but recursively.
     * @param index - zero based
     * @return the item at index or null if item doesn't
     * exist or index is negative
     */
    public T getRecursive(int index) {
        if (size <= index || index < 0) {
            return null;
        }
        if (index == 0) {
            return sentinel.next.item;
        }
        return getRecursive(index - 1);
    }

    /**
     * Prints all items in the deque, space separated.
     * Adds an empty line at the end
     */
    public void printDeque() {
        Node node = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(node.toString() + " ");
            node = node.next;
        }
        System.out.println();
    }

    /**
     * Main function.
     * @param args arguments
     */
    public static void main(String[] args) {
        LinkedListDeque<String> list = new LinkedListDeque<>();
        list.addFirst("Andrei");
        list.addFirst("Max");
        list.addLast("Julia");
        LinkedListDeque<Integer> newList = new LinkedListDeque<>(list);
    }
}
