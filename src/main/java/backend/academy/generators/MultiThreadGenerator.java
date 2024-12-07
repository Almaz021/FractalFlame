package backend.academy.generators;

import backend.academy.Configuration;
import backend.academy.entities.Pixel;
import backend.academy.entities.Rect;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@SuppressFBWarnings("CLI_CONSTANT_LIST_INDEX")
public class MultiThreadGenerator extends AbstractGenerator {

    public MultiThreadGenerator(Configuration configuration, Pixel[][] data) {
        super(configuration, data);
    }

    @Override
    protected void startRendering(Rect rect) {
        int[] resolution = {configuration.resolution()[0], configuration.resolution()[1]};

        try (ExecutorService executorService =
                 Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {

            for (int num = 0; num < configuration.pointConfig()[0]; num++) {
                executorService.execute(() -> renderOnePoint(
                    ThreadLocalRandom.current(),
                    configuration.coefficients(),
                    configuration.symmetryCount(),
                    resolution,
                    data,
                    true));
            }
        }
    }
}
