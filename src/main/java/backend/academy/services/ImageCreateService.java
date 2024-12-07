package backend.academy.services;

import backend.academy.entities.FractalImage;
import backend.academy.entities.Pixel;
import backend.academy.settings.Settings;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ImageCreateService {

    public void save(FractalImage image, Path filename, String format) throws IOException {
        int w = image.xRes();
        int h = image.yRes();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int rgb = getRgb(image, i, j);
                bi.setRGB(j, i, rgb);
            }
        }

        File outputFile = filename.toFile();
        ImageIO.write(bi, format, outputFile);
    }

    private static int getRgb(FractalImage image, int i, int j) {
        Pixel pixel = image.data()[i][j];

        int r;
        int g;
        int b;
        if (pixel == null) {
            r = 0;
            g = 0;
            b = 0;
        } else {
            r = pixel.red();
            g = pixel.green();
            b = pixel.blue();
        }

        return (r << Settings.BIT_SHIFT_16) | (g << Settings.BIT_SHIFT_8) | b;
    }
}
