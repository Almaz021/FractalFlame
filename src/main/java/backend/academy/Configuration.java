package backend.academy;

import backend.academy.entities.Rect;
import backend.academy.interfaces.Transformation;

/**
 * Stores configuration settings for fractal generation, including resolution, point configuration, coefficients,
 * colors, symmetries, transformations, and the region for fractal generation.
 */
public record Configuration(
    int[] resolution,
    int[] pointConfig,
    double[][] coefficients,
    int[][] colors,
    int numberOfColors,
    int symmetryCount,
    Transformation[] transformations,
    Rect rect
) {
}
