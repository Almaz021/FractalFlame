import backend.academy.entities.Point;
import backend.academy.transformations.Disc;
import backend.academy.transformations.Handkerchief;
import backend.academy.transformations.Heart;
import backend.academy.transformations.Horseshoe;
import backend.academy.transformations.Hyperbolic;
import backend.academy.transformations.Linear;
import backend.academy.transformations.Polar;
import backend.academy.transformations.Sin;
import backend.academy.transformations.Sphere;
import backend.academy.transformations.Swirl;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AllTests {

    @Test
    public void discTransformTest() {
        Point point = new Point(0.5, 0.5);
        Point correct = new Point(0.19892330039187023, -0.15142496676970335);

        Point actual = new Disc().apply(point);

        assertThat(Objects.equals(correct, actual)).isTrue();
    }

    @Test
    public void handkerchiefTransformTest() {
        Point point = new Point(0.5, 0.5);
        Point correct = new Point(0.7049407680778463, 0.7049407680778463);

        Point actual = new Handkerchief().apply(point);

        assertThat(Objects.equals(correct, actual)).isTrue();
    }

    @Test
    public void heartTransformTest() {
        Point point = new Point(0.5, 0.5);
        Point correct = new Point(0.37282172672531666, -0.6008360509170146);

        Point actual = new Heart().apply(point);

        assertThat(Objects.equals(correct, actual)).isTrue();
    }

    @Test
    public void horseshoeTransformTest() {
        Point point = new Point(0.5, 0.5);
        Point correct = new Point(0.0, 0.7071067811865475);

        Point actual = new Horseshoe().apply(point);

        assertThat(Objects.equals(correct, actual)).isTrue();
    }

    @Test
    public void hyperbolicTransformTest() {
        Point point = new Point(0.5, 0.5);
        Point correct = new Point(0.9999999999999999, 0.5000000000000001);

        Point actual = new Hyperbolic().apply(point);

        assertThat(Objects.equals(correct, actual)).isTrue();
    }

    @Test
    public void linearTransformTest() {
        Point point = new Point(0.5, 0.5);
        Point correct = new Point(0.5, 0.5);

        Point actual = new Linear().apply(point);

        assertThat(Objects.equals(correct, actual)).isTrue();
    }

    @Test
    public void polarTransformTest() {
        Point point = new Point(0.5, 0.5);
        Point correct = new Point(0.25, -0.2928932188134524);

        Point actual = new Polar().apply(point);

        assertThat(Objects.equals(correct, actual)).isTrue();
    }

    @Test
    public void sinTransformTest() {
        Point point = new Point(0.5, 0.5);
        Point correct = new Point(0.479425538604203, 0.479425538604203);

        Point actual = new Sin().apply(point);

        assertThat(Objects.equals(correct, actual)).isTrue();
    }

    @Test
    public void sphereTransformTest() {
        Point point = new Point(0.5, 0.5);
        Point correct = new Point(1.0, 1.0);

        Point actual = new Sphere().apply(point);

        assertThat(Objects.equals(correct, actual)).isTrue();
    }

    @Test
    public void swirlTransformTest() {
        Point point = new Point(0.5, 0.5);
        Point correct = new Point(-0.19907851164308488, 0.6785040502472879);

        Point actual = new Swirl().apply(point);

        assertThat(Objects.equals(correct, actual)).isTrue();
    }
}
