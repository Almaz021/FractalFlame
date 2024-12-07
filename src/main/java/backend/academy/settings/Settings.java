package backend.academy.settings;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Settings {
    public static final double GAMMA = 1.2;
    public static final double X_MIN = -3.9;
    public static final double X_MAX = 3.9;
    public static final double Y_MIN = -2.2;
    public static final double Y_MAX = 2.2;

    public static final int COEFFICIENTS_COUNT = 9;
    public static final int COEFFICIENT_A_INDEX = 0;
    public static final int COEFFICIENT_B_INDEX = 1;
    public static final int COEFFICIENT_C_INDEX = 2;
    public static final int COEFFICIENT_D_INDEX = 3;
    public static final int COEFFICIENT_E_INDEX = 4;
    public static final int COEFFICIENT_F_INDEX = 5;


    public static final int RGB_COLORS_COUNT = 3;
    public static final int RGB_RED_INDEX = 0;
    public static final int RGB_GREEN_INDEX = 1;
    public static final int RGB_BLUE_INDEX = 2;
    public static final int RGB_MAX_VALUE = 255;

    public static final int SKIP_STEPS_COUNT = 20;
    public static final int BIT_SHIFT_8 = 8;
    public static final int BIT_SHIFT_16 = 16;

}
