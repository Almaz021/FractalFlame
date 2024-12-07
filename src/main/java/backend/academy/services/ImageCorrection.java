package backend.academy.services;

import backend.academy.entities.FractalImage;
import backend.academy.entities.Pixel;
import backend.academy.interfaces.ImageProcessor;
import backend.academy.settings.Settings;

public class ImageCorrection implements ImageProcessor {
    @Override
    public FractalImage process(FractalImage image) {
        int xRes = image.xRes();
        int yRes = image.yRes();
        Pixel[][] pixels = image.data();
        double max = 0.0;
        double gamma = Settings.GAMMA;

        for (int row = 0; row < yRes; row++) {
            for (int col = 0; col < xRes; col++) {
                if (pixels[row][col].numberOfHits() != 0) {
                    pixels[row][col].normal(Math.log10(pixels[row][col].numberOfHits()));
                    if (pixels[row][col].normal() > max) {
                        max = pixels[row][col].normal();
                    }
                }
            }
        }

        for (int row = 0; row < yRes; row++) {
            for (int col = 0; col < xRes; col++) {
                pixels[row][col].normal(pixels[row][col].normal() / max);
                pixels[row][col].red(
                    (int) (pixels[row][col].red() * Math.pow(pixels[row][col].normal(), (1.0 / gamma))));
                pixels[row][col].green(
                    (int) (pixels[row][col].green() * Math.pow(pixels[row][col].normal(), (1.0 / gamma))));
                pixels[row][col].blue(
                    (int) (pixels[row][col].blue() * Math.pow(pixels[row][col].normal(), (1.0 / gamma))));
            }
        }

        return image;
    }
}
