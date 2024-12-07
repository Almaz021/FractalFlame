package backend.academy.entities;

/**
 * Represents a rectangle in 2D space with position and dimensions.
 */
public record Rect(double x, double y, double width, double height) {
    /**
     * Checks if a given point is within the bounds of the rectangle.
     */
    public boolean contains(Point p) {
        return p.x() >= x && p.x() < x + width && p.y() >= y && p.y() < y + height;
    }
}
