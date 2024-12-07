package backend.academy.transformations;

import backend.academy.entities.Point;
import backend.academy.interfaces.Transformation;

public class Linear implements Transformation {
    @Override
    public Point apply(Point point) {
        return point;
    }
}
