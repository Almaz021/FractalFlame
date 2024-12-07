package backend.academy.transformations;

import backend.academy.entities.Point;
import backend.academy.interfaces.Transformation;

public class Swirl implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = point.x() * point.x() + point.y() * point.y();

        double newX = point.x() * Math.sin(r) - point.y() * Math.cos(r);
        double newY = point.x() * Math.sin(r) + point.y() * Math.cos(r);
        return new Point(newX, newY);
    }
}
