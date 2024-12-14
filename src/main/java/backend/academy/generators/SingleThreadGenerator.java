package backend.academy.generators;

import backend.academy.Configuration;
import backend.academy.entities.Pixel;
import backend.academy.entities.Rect;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Random;

@SuppressFBWarnings("CLI_CONSTANT_LIST_INDEX")
public class SingleThreadGenerator extends AbstractGenerator {

    private final Random random;

    /**
     * Initializes the generator with the given random object, configuration, and pixel data.
     */
    public SingleThreadGenerator(Random random, Configuration configuration, Pixel[][] data) {
        super(configuration, data);
        this.random = random;
    }

    /**
     * Starts the rendering process using a single thread, rendering each point on the fractal sequentially.
     */
    @Override
    protected void startRendering(Rect rect) {
        int[] resolution = {configuration.resolution().width(), configuration.resolution().height()};
        for (int num = 0; num < configuration.pointsConfig().countOfPoints(); num++) {
            renderOnePoint(
                random,
                configuration.coefficients(),
                configuration.symmetryCount(),
                resolution);
        }
    }

    /**
     * Handles the update of the pixel at the given coordinates by modifying its color
     * based on a random selection.
     */
    @Override
    protected void handlePixelUpdate(int x, int y, Random random) {
        Pixel currPixel = data[y][x];
        updatePixel(random, currPixel);
    }

}
