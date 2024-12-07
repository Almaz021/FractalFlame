package backend.academy.transformations;

import backend.academy.entities.Point;
import backend.academy.interfaces.Transformation;

public class Sin implements Transformation {
    @Override
    public Point apply(Point point) {
        double newX = Math.sin(point.x());
        double newY = Math.sin(point.y());
        return new Point(newX, newY);
    }
}
