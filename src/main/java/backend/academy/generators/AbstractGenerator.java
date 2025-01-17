package backend.academy.generators;

import backend.academy.Configuration;
import backend.academy.entities.FractalImage;
import backend.academy.entities.Pixel;
import backend.academy.entities.Point;
import backend.academy.entities.Rect;
import backend.academy.settings.Settings;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Random;

@SuppressFBWarnings("CLI_CONSTANT_LIST_INDEX")
public abstract class AbstractGenerator {

    protected Configuration configuration;
    protected Pixel[][] data;

    /**
     * Initializes the generator with a given configuration and pixel data.
     */
    public AbstractGenerator(Configuration configuration, Pixel[][] data) {
        this.configuration = configuration;
        this.data = data;
    }

    /**
     * Generates the fractal image by starting the rendering process and returning a new FractalImage.
     */
    public FractalImage generateFractalImage() {
        startRendering(configuration.rect());
        return new FractalImage(data, configuration.resolution().width(), configuration.resolution().height());
    }

    /**
     * Abstract method to start rendering the fractal within the specified rectangle.
     */
    protected abstract void startRendering(Rect rect);

    /**
     * Renders a single point on the fractal using the given random generator and coefficients.
     */
    protected void renderOnePoint(
        Random random,
        double[][] coefficients,
        int symmetryCount,
        int[] resolution
    ) {

        Point point = generateRandomPoint(random);

        for (int step = -Settings.SKIP_STEPS_COUNT; step < configuration.pointsConfig().iterations(); step++) {
            point = pointTransformation(random, coefficients, point);
            if (step >= 0) {
                applySymmetryAndRenderPoint(point, symmetryCount, resolution, random);
            }
        }
    }

    /**
     * Generates a random point.
     */
    private Point generateRandomPoint(Random random) {
        double newX =
            random.nextDouble(configuration.rect().x(), configuration.rect().x() + configuration.rect().width());
        double newY =
            random.nextDouble(configuration.rect().y(), configuration.rect().y() + configuration.rect().height());
        return new Point(newX, newY);
    }

    /**
     * Applies a random transformation to the given point based on the specified coefficients.
     */
    private Point pointTransformation(Random random, double[][] coefficients, Point point) {
        int i = random.nextInt(coefficients.length);
        int j = random.nextInt(configuration.transformations().length);

        Point resultPoint = transformPoint(point, coefficients, i);
        resultPoint = configuration.transformations()[j].apply(resultPoint);
        return resultPoint;
    }

    /**
     * Applies symmetry to the given point and renders it if it's within the valid rectangle.
     */
    private void applySymmetryAndRenderPoint(Point point, int symmetryCount, int[] resolution, Random random) {
        double newX = point.x();
        double newY = point.y();

        double theta2 = 0.0;

        for (int s = 0; s < symmetryCount; s++) {
            theta2 += ((2 * Math.PI) / symmetryCount);
            double xRot = newX * Math.cos(theta2) - newY * Math.sin(theta2);
            double yRot = newX * Math.sin(theta2) + newY * Math.cos(theta2);

            if (!configuration.rect().contains(new Point(xRot, yRot))) {
                continue;
            }

            int x1 =
                (int) ((xRot - configuration.rect().x()) / (configuration.rect().width()) * resolution[0]);
            int y1 =
                (int) ((yRot - configuration.rect().y()) / (configuration.rect().height()) * resolution[1]);

            if (x1 < resolution[0] && y1 < resolution[1] && x1 > 0 && y1 > 0) {
                handlePixelUpdate(x1, y1, random);
            }
        }
    }

    abstract void handlePixelUpdate(int x, int y, Random random);

    /**
     * Updates the color of the given pixel, modifying its color based on the random selection.
     */
    protected void updatePixel(Random random, Pixel currPixel) {
        int k = random.nextInt(configuration.colors().length);
        if (currPixel.numberOfHits() == 0) {
            int r = configuration.colors()[k][Settings.RGB_RED_INDEX];
            int g = configuration.colors()[k][Settings.RGB_GREEN_INDEX];
            int b = configuration.colors()[k][Settings.RGB_BLUE_INDEX];
            currPixel.setColor(r, g, b);
        } else {
            int r = (currPixel.red() + configuration.colors()[k][Settings.RGB_RED_INDEX]) / 2;
            int g = (currPixel.green() + configuration.colors()[k][Settings.RGB_GREEN_INDEX]) / 2;
            int b = (currPixel.blue() + configuration.colors()[k][Settings.RGB_BLUE_INDEX]) / 2;
            currPixel.setColor(r, g, b);
        }
        currPixel.increaseHitCount();
    }

    /**
     * Transforms the point using a set of coefficients based on a specific transformation.
     */
    private static Point transformPoint(Point point, double[][] coefficients, int i) {
        double x = point.x();
        double y = point.y();
        double newX =
            coefficients[i][Settings.COEFFICIENT_A_INDEX] * x + coefficients[i][Settings.COEFFICIENT_B_INDEX] * y
            + coefficients[i][Settings.COEFFICIENT_C_INDEX];
        double newY =
            coefficients[i][Settings.COEFFICIENT_D_INDEX] * x + coefficients[i][Settings.COEFFICIENT_E_INDEX] * y
            + coefficients[i][Settings.COEFFICIENT_F_INDEX];
        return new Point(newX, newY);
    }
}
