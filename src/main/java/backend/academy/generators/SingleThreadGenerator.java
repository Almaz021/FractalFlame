package backend.academy.generators;

import backend.academy.Configuration;
import backend.academy.entities.Pixel;
import backend.academy.entities.Rect;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Random;

@SuppressFBWarnings("CLI_CONSTANT_LIST_INDEX")
public class SingleThreadGenerator extends AbstractGenerator {

    private final Random random;

    public SingleThreadGenerator(Random random, Configuration configuration, Pixel[][] data) {
        super(configuration, data);
        this.random = random;
    }

    @Override
    protected void startRendering(Rect rect) {
        int[] resolution = {configuration.resolution()[0], configuration.resolution()[1]};
        for (int num = 0; num < configuration.pointConfig()[0]; num++) {
            renderOnePoint(
                random,
                configuration.coefficients(),
                configuration.symmetryCount(),
                resolution,
                data,
                false);
        }
    }
}
