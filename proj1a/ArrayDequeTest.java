import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArrayDequeTest {
    @Test
    public void testAddLastSize() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addLast(10);
        assertTrue("List expected to be of size  1", list.size() == 1);
    }

    @Test
    public void testAddLastItem() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addLast(10);
        assertTrue("first item expected to be 10", list.get(0) == 10);
    }

    @Test
    public void testIsEmpty() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        assertTrue(list.isEmpty());
        list.addLast(10);
        assertTrue(!list.isEmpty());
    }

    @Test
    public void testRemoveLast() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        assertTrue(list.size() == 4);
        int result = list.removeLast();
        assertTrue(list.size() == 3);
        assertTrue(result == 40);
        list.removeLast();
        list.removeLast();
        list.removeLast();
        assertTrue(list.size() == 0);
        list.addFirst(10);
        assertTrue(list.size() == 1);
        assertTrue(list.get(0) == 10);
    }

    @Test
    public void testAddToFront() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addFirst(10);
        list.addFirst(20);
        assertTrue(list.size() == 2);
    }

    @Test
    public void testAddAndGet() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addFirst(10);
        list.addFirst(20);
        assertTrue(list.size() == 2);
        assertTrue(list.get(0) == 20);
        assertTrue(list.get(1) == 10);
    }

    @Test
    public void testRemoveFirstAfterAddLast() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addLast(5);
        list.addLast(10);
        int removed = list.removeFirst();
        assertTrue(list.size() == 1);
        assertTrue(removed == 5);
        removed = list.removeFirst();
        assertTrue(list.size() == 0);
        assertTrue(removed == 10);
    }

    @Test
    public void testRemoveEmptyList() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        Integer shouldBeNull = list.removeFirst();
        assertTrue(shouldBeNull == null);
    }

    @Test
    public void testDeepCopy() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        ArrayDeque<Integer> list2 = new ArrayDeque(list);
        assertTrue(list2.size() == 4);
        assertTrue(list2 != list);
        list.removeFirst();
        assertTrue(list.get(0) == 2);
        assertTrue(list2.get(0) == 1);
    }

    @Test
    public void testDeepCopyStrings() {
        ArrayDeque<String> list = new ArrayDeque<>();
        list.addLast("Andrei");
        list.addLast("Max");
        list.addLast("Bill");
        list.addLast("Julia");
        ArrayDeque<String> list2 = new ArrayDeque(list);
        assertTrue(list2.size() == 4);
        assertTrue(list2 != list);
        list.removeFirst();
        assertTrue(list.get(0).equals("Max"));
        assertTrue(list2.get(0).equals("Andrei"));
    }

    @Test
    public void testResizingLast() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            list.addLast(i);
        }
        for (int i = 0; i < 100; i++) {
            assertTrue(list.get(i) == i);
        }
    }

    @Test
    public void testResizingFirst() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        for (int i = 10; i > 0; i--) {
            list.addFirst(i);
        }
        assertTrue(list.get(0) == 1);
        assertTrue(list.get(1) == 2);
        assertTrue(list.get(2) == 3);
        assertTrue(list.get(3) == 4);
        assertTrue(list.get(4) == 5);
        assertTrue(list.get(5) == 6);
        assertTrue(list.get(6) == 7);
        assertTrue(list.get(7) == 8);
        assertTrue(list.get(8) == 9);
    }

    @Test
    public void testShrinking() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        for (int i = 0; i < 1000; i++) {
            list.addLast(i);
        }
        for (int i = 0; i < 999; i++) {
            list.removeLast();
        }
        assertTrue(list.arraySize() < 1000);
    }

    @Test
    public void testRemoveFirstLast() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        int a = list.removeFirst();
        assertEquals(a, 1);
        int b = list.removeLast();
        assertEquals(b, 4);
    }

    @Test
    public void testFirstRemoveFirstLast() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        int a = list.removeFirst();
        assertEquals(a, 4);
        int b = list.removeLast();
        assertEquals(b, 1);
    }
}
