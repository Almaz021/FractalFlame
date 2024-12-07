package backend.academy.services;

import backend.academy.Configuration;
import backend.academy.entities.FractalImage;
import backend.academy.entities.Rect;
import backend.academy.enums.ImageFormat;
import backend.academy.generators.AbstractGenerator;
import backend.academy.generators.CoefficientsGenerator;
import backend.academy.generators.MultiThreadGenerator;
import backend.academy.generators.SingleThreadGenerator;
import backend.academy.interfaces.Transformation;
import backend.academy.settings.Settings;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SuppressWarnings("checkstyle:magicnumber")
public class StartService {
    private final PrintWriter writer;

    /**
     * Starts the fractal image generation process by gathering input from the user
     * and generating the fractal image with specified parameters.
     */
    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        int xRes = getWidth(scanner);
        int yRes = getHeight(scanner);
        int countOfPoints = getCountOfPoints(scanner);
        int iterations = getIterations(scanner);
        int numberOfAffineTransformations = getNumberOfAffineTransformations(scanner);
        int numberOfColors = getNumberOfColors(scanner);
        int symmetryCount = getSymmetryCount(scanner);
        int numberOfThreads = getNumberOfThreads(scanner);
        ImageFormat imageFormat = getImageFormat(scanner);
        int numberOfTransformations = getNumberOfTransformations(scanner);
        Transformation[] transformations = getTransformations(scanner, numberOfTransformations);

        FractalImage fractalImage = createFractalImage(xRes, yRes);
        Configuration configuration = createConfiguration(
            new int[] {xRes, yRes}, countOfPoints, iterations, numberOfAffineTransformations,
            numberOfColors, symmetryCount, transformations);

        generateAndSaveImages(fractalImage, configuration, numberOfThreads, imageFormat);
    }

    /**
     * Prompts the user to enter the width of the image and returns the input.
     */
    private int getWidth(Scanner scanner) {
        writer.println("Please enter the width of the image: ");
        return scanner.nextInt();
    }

    /**
     * Prompts the user to enter the height of the image and returns the input.
     */
    private int getHeight(Scanner scanner) {
        writer.println("Please enter the height of the image: ");
        return scanner.nextInt();
    }

    /**
     * Prompts the user to enter the count of points and returns the input.
     */
    private int getCountOfPoints(Scanner scanner) {
        writer.println("Please enter count of points: ");
        return scanner.nextInt();
    }

    /**
     * Prompts the user to enter the number of iterations per point and returns the input.
     */
    private int getIterations(Scanner scanner) {
        writer.println("Please enter the iterations per point: ");
        return scanner.nextInt();
    }

    /**
     * Prompts the user to enter the number of affine transformations and returns the input.
     */
    private int getNumberOfAffineTransformations(Scanner scanner) {
        writer.println("Please enter the number of affine transformations: ");
        return scanner.nextInt();
    }

    /**
     * Prompts the user to enter the number of colors and returns the input.
     */
    private int getNumberOfColors(Scanner scanner) {
        writer.println("Please enter the number of colors: ");
        return scanner.nextInt();
    }

    /**
     * Prompts the user to enter the number of symmetries and returns the input.
     */
    private int getSymmetryCount(Scanner scanner) {
        writer.println("Please enter the number of symmetry: ");
        return scanner.nextInt();
    }

    /**
     * Prompts the user to enter the number of threads (single or multi-thread) and returns the input.
     */
    private int getNumberOfThreads(Scanner scanner) {
        writer.println("Please enter the number (1 for singleThread, other for multiThread): ");
        return scanner.nextInt();
    }

    /**
     * Prompts the user to enter the number of transformations and returns the input.
     */
    private int getNumberOfTransformations(Scanner scanner) {
        writer.println("Please enter the number of transformations: ");
        return scanner.nextInt();
    }

    /**
     * Prompts the user to select transformations based on input and returns an array of transformations.
     */
    private Transformation[] getTransformations(Scanner scanner, int numberOfTransformations) {
        writer.println("""
            Transformations:
            1. Disc
            2. Handkerchief
            3. Heart
            4. Horseshoe
            5. Hyperbolic
            6. Linear
            7. Polar
            8. Sin
            9. Swirl
            10. Sphere
            """);
        Transformation[] transformations = new Transformation[numberOfTransformations];
        for (int i = 0; i < numberOfTransformations; i++) {
            writer.println("Please enter the number of transformation: ");
            int transformationNumber = scanner.nextInt();
            transformations[i] = selectTransformation(transformationNumber);
        }
        return transformations;
    }

    /**
     * Prompts the user to enter the image format (png, jpeg, or bmp) and returns the selected format.
     */
    private ImageFormat getImageFormat(Scanner scanner) {
        writer.println("Please enter the image format (png, jpeg or bmp): ");
        String format = scanner.next().toLowerCase();
        return switch (format) {
            case "jpeg" -> ImageFormat.JPEG;
            case "bmp" -> ImageFormat.BMP;
            default -> ImageFormat.PNG;
        };
    }

    /**
     * Creates a new FractalImage object with the specified resolution.
     */
    private FractalImage createFractalImage(int xRes, int yRes) {
        return FractalImage.create(xRes, yRes);
    }

    /**
     * Creates a Configuration object with the specified parameters.
     */
    private Configuration createConfiguration(
        int[] resolution, int countOfPoints, int iterations,
        int numberOfAffineTransformations, int numberOfColors, int symmetryCount, Transformation[] transformations
    ) {

        SecureRandom random = new SecureRandom();
        double[][] coefficients =
            CoefficientsGenerator.getAffineCoefficients(numberOfAffineTransformations, -1, 1, random);
        int[][] colors = CoefficientsGenerator.getColors(numberOfColors, random);

        writer.println(coefficients.length + " " + Arrays.deepToString(coefficients));

        Rect rect = new Rect(
            Settings.X_MIN,
            Settings.Y_MIN,
            Settings.X_MAX - Settings.X_MIN,
            Settings.Y_MAX - Settings.Y_MIN);

        return new Configuration(
            resolution,
            new int[] {countOfPoints, iterations},
            coefficients,
            colors,
            numberOfColors,
            symmetryCount,
            transformations,
            rect);
    }

    /**
     * Generates and saves the fractal image to disk.
     */
    private void generateAndSaveImages(
        FractalImage fractalImage,
        Configuration configuration,
        int thread,
        ImageFormat imageFormat
    ) throws IOException {
        final long startTime = System.currentTimeMillis() / 1000;

        AbstractGenerator generator;
        if (thread == 1) {
            generator =
                new SingleThreadGenerator(new SecureRandom(), configuration, fractalImage.data());
        } else {
            generator = new MultiThreadGenerator(configuration, fractalImage.data());
        }
        FractalImage resultImage = generator.generateFractalImage();

        final long endTime = System.currentTimeMillis() / 1000;
        writer.println("Total execution time: " + (endTime - startTime) + "s");

        String directory = System.getProperty("user.home");

        String resultTest = directory + "/test.%s";
        String savedTo = "Saved to %s";
        String resultTestCorrection = directory + "/test_correction.%s";

        saveImage(resultImage, resultTest.formatted(imageFormat.extension()), imageFormat);

        writer.println(savedTo.formatted(resultTest.formatted(imageFormat.extension())));

        FractalImage fractalCorrect = new ImageCorrection().process(fractalImage);
        saveImage(fractalCorrect, resultTestCorrection.formatted(imageFormat.extension()), imageFormat);

        writer.println(savedTo.formatted(resultTestCorrection.formatted(imageFormat.extension())));

    }

    /**
     * Saves the given fractal image to a file in the specified format.
     */
    private void saveImage(FractalImage image, String path, ImageFormat imageFormat) throws IOException {
        ImageCreateService.save(image, Path.of(path), imageFormat.extension());
    }

    /**
     * Selects the transformation based on the given number.
     */
    private Transformation selectTransformation(int n) {
        return switch (n) {
            case 1 -> new Disc();
            case 2 -> new Handkerchief();
            case 3 -> new Heart();
            case 4 -> new Horseshoe();
            case 5 -> new Hyperbolic();
            case 6 -> new Linear();
            case 7 -> new Polar();
            case 8 -> new Sin();
            case 9 -> new Swirl();
            default -> new Sphere();
        };
    }
}
