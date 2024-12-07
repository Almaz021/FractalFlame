package backend.academy.entities;

public record FractalImage(Pixel[][] data, int xRes, int yRes) {
    public static FractalImage create(int xRes, int yRes) {
        Pixel[][] data = generateTable(yRes, xRes);
        return new FractalImage(data, xRes, yRes);
    }

    private static Pixel[][] generateTable(int yRes, int xRes) {
        Pixel[][] data = new Pixel[yRes][xRes];
        for (int i = 0; i < yRes; i++) {
            for (int j = 0; j < xRes; j++) {
                data[i][j] = pixel(i, j);
            }
        }
        return data;
    }

    private static Pixel pixel(int x, int y) {
        return new Pixel(x, y);
    }
}
