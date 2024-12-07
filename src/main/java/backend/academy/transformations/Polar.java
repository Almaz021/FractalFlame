package backend.academy.transformations;

import backend.academy.entities.Point;
import backend.academy.interfaces.Transformation;

public class Polar implements Transformation {
    @Override
    public Point apply(Point point) {
        double newX = Math.atan((point.x()) / point.y()) / Math.PI;
        double newY = Math.sqrt(point.x() * point.x() + point.y() * point.y()) - 1;
        return new Point(newX, newY);
    }
}
