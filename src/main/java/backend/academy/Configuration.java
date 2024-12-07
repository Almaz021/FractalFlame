package backend.academy;

import backend.academy.entities.Rect;
import backend.academy.interfaces.Transformation;

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
