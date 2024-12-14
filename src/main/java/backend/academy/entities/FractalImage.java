package backend.academy.entities;

/**
 * Represents a fractal image with pixel data and resolution.
 */
public record FractalImage(Pixel[][] data, int xRes, int yRes) {
    /**
     * Creates a FractalImage with the specified resolution.
     */
    public static FractalImage create(int xRes, int yRes) {
        Pixel[][] data = generateTable(xRes, yRes);
        return new FractalImage(data, xRes, yRes);
    }

    /**
     * Generates a 2D table of pixels for the given resolution.
     */
    private static Pixel[][] generateTable(int xRes, int yRes) {
        Pixel[][] data = new Pixel[yRes][xRes];
        for (int i = 0; i < yRes; i++) {
            for (int j = 0; j < xRes; j++) {
                data[i][j] = pixel(i, j);
            }
        }
        return data;
    }

    /**
     * Creates a pixel at the specified coordinates.
     */
    private static Pixel pixel(int x, int y) {
        return new Pixel(x, y);
    }
}
