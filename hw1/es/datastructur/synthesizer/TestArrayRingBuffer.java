package es.datastructur.synthesizer;

import static org.junit.Assert.*;

import org.junit.Test;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someBasicTest() {
        ArrayRingBuffer<Double> arr = new ArrayRingBuffer<>(5);
        assertEquals(arr.capacity(), 5);
        assertEquals(arr.fillCount(), 0);
    }

    @Test
    public void testEnqueue() {
        ArrayRingBuffer<Double> arr = new ArrayRingBuffer<>(5);
        arr.enqueue(1.0);
        assertEquals(arr.fillCount(), 1);
    }

    @Test(expected = RuntimeException.class)
    public void testEnqueueFull() {
        ArrayRingBuffer<Double> arr = new ArrayRingBuffer<>(2);
        arr.enqueue(1.0);
        arr.enqueue(1.0);
        arr.enqueue(1.0);
    }

    @Test
    public void testDequeue() {
        ArrayRingBuffer<Double> arr = new ArrayRingBuffer<>(2);
        arr.enqueue(1.0);
        arr.enqueue(2.0);
        double one = arr.dequeue();
        assertEquals(one, 1.0, 0.01);
        double two = arr.dequeue();
        assertEquals(two, 2, 0.01);
    }

    @Test(expected = RuntimeException.class)
    public void testDequeueEmpty() {
        ArrayRingBuffer<Double> arr = new ArrayRingBuffer<>(2);
        arr.dequeue();
    }

    @Test
    public void testPeek() {
        ArrayRingBuffer<Double> arr = new ArrayRingBuffer<>(2);
        arr.enqueue(1.0);
        double one = arr.peek();
        assertEquals(one, 1, 0.01);
        arr.enqueue(2.0);
        double notTwo = arr.peek();
        assertEquals(notTwo, 1, 0.01);
        arr.dequeue();
        double two = arr.peek();
        assertEquals(two, 2, 0.01);
    }

    @Test(expected = RuntimeException.class)
    public void testPeekEmpty() {
        ArrayRingBuffer<Double> arr = new ArrayRingBuffer<>(5);
        arr.peek();
    }

    @Test
    public void testWholeProcess() {
        ArrayRingBuffer<Double> arr = new ArrayRingBuffer<>(3);
        assertTrue(arr.isEmpty());
        arr.enqueue(1.0);
        arr.enqueue(2.0);
        arr.enqueue(3.0);
        assertTrue(arr.isFull());
        assertEquals(arr.dequeue(), 1.0, 0.01);
        assertFalse(arr.isFull());
        arr.enqueue(4.0);
        assertTrue(arr.isFull());
        assertEquals(arr.dequeue(), 2.0, 0.01);
        assertEquals(arr.peek(), 3.0, 0.01);
        assertEquals(arr.fillCount(), 2);
    }

    @Test
    public void testIterator() {
        ArrayRingBuffer<Double> arr = new ArrayRingBuffer<>(5);
        arr.enqueue(1.0);
        arr.enqueue(2.0);
        arr.enqueue(3.0);
        arr.enqueue(4.0);
        arr.enqueue(5.0);
        double expected = 1.0;
        for (Double el : arr) {
            assertEquals(el, expected, 0.01);
            expected += 1.0;
        }
    }

    @Test
    public void testEquals() {
        ArrayRingBuffer<Double> arr = new ArrayRingBuffer<>(5);
        arr.enqueue(1.0);
        arr.enqueue(2.0);
        arr.enqueue(3.0);
        arr.enqueue(4.0);
        arr.enqueue(5.0);

        assertTrue(arr.equals(arr));
        assertFalse(arr.equals(null));
        assertFalse(arr.equals("String"));

        ArrayRingBuffer<Double> arr2 = new ArrayRingBuffer<>(5);
        arr2.enqueue(2.0);
        arr2.enqueue(3.0);
        arr2.enqueue(4.0);
        arr2.enqueue(5.0);
        arr.dequeue();
        assertTrue(arr.equals(arr2));
        arr.enqueue(6.0);
        arr2.enqueue(6.0);
        assertTrue(arr.equals(arr2));
    }
}

