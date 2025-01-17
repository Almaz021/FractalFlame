package backend.academy.transformations;

import backend.academy.entities.Point;
import backend.academy.interfaces.Transformation;

public class Disc implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double theta = Math.atan(point.y() / point.x());

        double newX = (theta / Math.PI) * Math.sin(Math.PI * r);
        double newY = (theta / Math.PI) * Math.cos(Math.PI * r);
        return new Point(newX, newY);
    }
}
