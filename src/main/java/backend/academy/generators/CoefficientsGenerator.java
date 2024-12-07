package backend.academy.generators;

import backend.academy.settings.Settings;
import java.security.SecureRandom;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CoefficientsGenerator {

    public static double[][] getAffineCoefficients(int n, double min, double max, SecureRandom random) {
        double[][] coefficients = new double[n][Settings.COEFFICIENTS_COUNT];

        for (int i = 0; i < n; i++) {
            double a;
            double b;
            double c;
            double d;
            double e;
            double f;
            do {
                a = random.nextDouble(min, max);
                b = random.nextDouble(min, max);
                c = random.nextDouble(min, max);
                d = random.nextDouble(min, max);
                e = random.nextDouble(min, max);
                f = random.nextDouble(min, max);
            } while (!(checkConditions(a, b, d, e)));

            coefficients[i][Settings.COEFFICIENT_A_INDEX] = a;
            coefficients[i][Settings.COEFFICIENT_B_INDEX] = b;
            coefficients[i][Settings.COEFFICIENT_C_INDEX] = c;
            coefficients[i][Settings.COEFFICIENT_D_INDEX] = d;
            coefficients[i][Settings.COEFFICIENT_E_INDEX] = e;
            coefficients[i][Settings.COEFFICIENT_F_INDEX] = f;
        }

        return coefficients;
    }

    public static int[][] getColors(int n, SecureRandom random) {
        int[][] colors = new int[n][Settings.RGB_COLORS_COUNT];
        for (int i = 0; i < n; i++) {
            colors[i][0] = random.nextInt(Settings.RGB_MAX_VALUE);
            colors[i][1] = random.nextInt(Settings.RGB_MAX_VALUE);
            colors[i][2] = random.nextInt(Settings.RGB_MAX_VALUE);
        }

        return colors;
    }

    private static boolean checkConditions(double a, double b, double d, double e) {
        return ((a * a + d * d) < 1) && ((b * b + e * e) < 1)
               && ((a * a + b * b + d * d + e * e) < (1 + (a * e - b * d) * (a * e - b * d)));
    }
}
