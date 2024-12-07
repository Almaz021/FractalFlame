package backend.academy.transformations;

import backend.academy.entities.Point;
import backend.academy.interfaces.Transformation;

public class Horseshoe implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());

        double newX = (1 / r) * (point.x() - point.y()) * (point.x() + point.y());
        double newY = (1 / r) * 2 * point.x() * point.y();
        return new Point(newX, newY);
    }
}
