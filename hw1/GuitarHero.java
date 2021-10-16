import es.datastructur.synthesizer.GuitarString;

import java.util.Arrays;

/**
 * Allows playing notes on the keyboard.
 * @author Andrei Dimitrascu
 */
public class GuitarHero {
    /** Base frequency. */
    private static final int BASE_FEQ = 440;
    /**
     * Runs GUI window and waits for key press.
     */
    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] strings = new GuitarString[keyboard.length()];
        for (int i = 0; i < strings.length; i += 1) {
            double freq = BASE_FEQ * Math.pow(2.0, (double) (i - 24) / 12);
            System.out.println(freq);
            strings[i] = new GuitarString(freq);
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) {
                    strings[index].pluck();
                }
            }

            double sample = Arrays
                    .stream(strings)
                    .map(s -> s.sample())
                    .reduce(
                            0.0,
                            (aDouble, guitarString) -> aDouble + guitarString
                    );
            StdAudio.play(sample);

            Arrays.stream(strings).forEach(s -> s.tic());
        }

    }
}
