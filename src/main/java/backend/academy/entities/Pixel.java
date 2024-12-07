package backend.academy.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Pixel {
    private final int x;
    private final int y;
    @Setter private int red;
    @Setter private int green;
    @Setter private int blue;
    private int numberOfHits;
    @Setter private double normal;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
        red = 0;
        blue = 0;
        green = 0;
        numberOfHits = 0;
    }

    public void setColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void increaseHitCount() {
        numberOfHits++;
    }
}
