package es.datastructur.synthesizer;

/** Imports the required audio library from the
 * edu.princeton.cs.introcs package. */

import edu.princeton.cs.introcs.StdAudio;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the GuitarString class.
 *  @author Josh Hug
 */

public class TestGuitarString {
    @Test
    public void testPluckTheAString() {
        double CONCERT_A = 440.0;
        GuitarString aString = new GuitarString(CONCERT_A);
        aString.pluck();
        for (int i = 0; i < 50000; i += 1) {
            StdAudio.play(aString.sample());
            aString.tic();
        }
    }


    @Test
    public void testTic() {
        GuitarString s = new GuitarString(11025);
        s.pluck();

        double s1 = s.sample();
        s.tic();
        double s2 = s.sample();
        s.tic();
        double s3 = s.sample();
        s.tic();
        double s4 = s.sample();

        s.tic();

        double s5 = s.sample();
        double expected = 0.996 * 0.5 * (s1 + s2);

        assertEquals(expected, s5, 0.001);

    }

}
