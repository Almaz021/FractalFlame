package backend.academy.generators;

import backend.academy.Configuration;
import backend.academy.entities.Pixel;
import backend.academy.entities.Rect;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@SuppressFBWarnings("CLI_CONSTANT_LIST_INDEX")
public class MultiThreadGenerator extends AbstractGenerator {

    /**
     * Initializes the generator with the given configuration and pixel data.
     */
    public MultiThreadGenerator(Configuration configuration, Pixel[][] data) {
        super(configuration, data);
    }

    /**
     * Starts the rendering process in multiple threads, each rendering a point on the fractal.
     */
    @Override
    protected void startRendering(Rect rect) {
        int[] resolution = {configuration.resolution().width(), configuration.resolution().height()};

        try (ExecutorService executorService =
                 Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {

            // Execute multiple rendering tasks concurrently
            for (int num = 0; num < configuration.pointsConfig().countOfPoints(); num++) {
                executorService.execute(() -> renderOnePoint(
                    ThreadLocalRandom.current(),
                    configuration.coefficients(),
                    configuration.symmetryCount(),
                    resolution));
            }
        }
    }

    @Override
    protected void handlePixelUpdate(int x, int y, Random random) {
        Pixel currPixel = data[y][x];
        synchronized (data[y][x]) {
            updatePixel(random, currPixel);
        }
    }

}
