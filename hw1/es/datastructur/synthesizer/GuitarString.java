package es.datastructur.synthesizer;

/**
 * A guitar string calculated using Karplus-Strong algorithm.
 * @author Andrei Dimitrascu
 */
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;
    /** energy decay factor. */
    private static final double DECAY = .996;

    /** Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /**
     * Create a guitar string of the given frequency.
     * @param frequency - the frequency of the note
     */
    public GuitarString(double frequency) {
        buffer = new ArrayRingBuffer<>((int) Math.round(SR / frequency));
        while (!buffer.isFull()) {
            buffer.enqueue(0.0);
        }
    }


    /** Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        while (!buffer.isFull()) {
            buffer.enqueue(Math.random() - 0.5);
        }
    }

    /** Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double newDouble = buffer.dequeue();
        newDouble = 0.5 * (newDouble + sample()) * DECAY;
        buffer.enqueue(newDouble);
    }

    /** Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
