package backend.academy.transformations;

import backend.academy.entities.Point;
import backend.academy.interfaces.Transformation;

public class Sphere implements Transformation {
    @Override
    public Point apply(Point point) {
        double newX = point.x() / (point.x() * point.x() + point.y() * point.y());
        double newY = point.y() / (point.x() * point.x() + point.y() * point.y());
        return new Point(newX, newY);
    }
}
