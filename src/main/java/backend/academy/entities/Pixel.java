package backend.academy.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a pixel with RGB color values, position, and additional metadata.
 */
@Getter
public class Pixel {
    private final int x;
    private final int y;
    @Setter private int red;
    @Setter private int green;
    @Setter private int blue;
    private int numberOfHits;
    @Setter private double normal;

    /**
     * Constructs a pixel at the specified coordinates, initializing color and hit count to default values.
     */
    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
        red = 0;
        blue = 0;
        green = 0;
        numberOfHits = 0;
    }

    /**
     * Sets the RGB color values for this pixel.
     */
    public void setColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Increases the hit count of this pixel.
     */
    public void increaseHitCount() {
        numberOfHits++;
    }
}
