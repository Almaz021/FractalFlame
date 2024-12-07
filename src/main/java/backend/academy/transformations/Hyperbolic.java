package backend.academy.transformations;

import backend.academy.entities.Point;
import backend.academy.interfaces.Transformation;

public class Hyperbolic implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double theta = Math.atan2(point.y(), point.x());

        double newX = Math.sin(theta) / r;
        double newY = r * Math.cos(theta);
        return new Point(newX, newY);
    }
}
