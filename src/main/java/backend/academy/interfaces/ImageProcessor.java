package backend.academy.interfaces;

import backend.academy.entities.FractalImage;

@FunctionalInterface
public
interface ImageProcessor {
    FractalImage process(FractalImage image);
}
