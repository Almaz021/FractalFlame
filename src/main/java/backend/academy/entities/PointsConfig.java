package backend.academy.entities;

/**
 * Configuration for the number of points and the iterations associated with each point.
 */
public record PointsConfig(
    int countOfPoints,
    int iterations
) {
}
