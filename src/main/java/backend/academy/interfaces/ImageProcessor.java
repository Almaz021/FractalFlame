package backend.academy.interfaces;

import backend.academy.entities.FractalImage;

@FunctionalInterface
public interface ImageProcessor {

    /**
     * Processes a given fractal image and returns a processed version of it.
     */
    FractalImage process(FractalImage image);
}
