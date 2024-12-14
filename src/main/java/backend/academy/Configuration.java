package backend.academy;

import backend.academy.entities.PointsConfig;
import backend.academy.entities.Rect;
import backend.academy.entities.Resolution;
import backend.academy.interfaces.Transformation;

/**
 * Stores configuration settings for fractal generation, including resolution, point configuration, coefficients,
 * colors, symmetries, transformations, and the region for fractal generation.
 */
public record Configuration(
    /* Array representing the resolution (width, height) of the configuration. */
    Resolution resolution,

    /* Array representing the configuration of points (countOfPoints and iterations). */
    PointsConfig pointsConfig,

    /* 2D array of affine coefficients used for affine transformations. */
    double[][] coefficients,

    /* 2D array representing color coefficients (RGB). */
    int[][] colors,

    /* The number of symmetries in the final image */
    int symmetryCount,

    /* Array of transformations applied to each point. */
    Transformation[] transformations,

    /* A rectangle defining the boundaries within which the point must be */
    Rect rect
) {
}
