package backend.academy.transformations;

import backend.academy.entities.Point;
import backend.academy.interfaces.Transformation;

public class Heart implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double theta = Math.atan(point.y() / point.x());

        double newX = r * Math.sin(theta * r);
        double newY = -r * Math.cos(theta * r);
        return new Point(newX, newY);
    }
}
