package backend.academy.enums;

import lombok.Getter;

/**
 * Enum representing different image formats with their file extensions.
 */
@Getter
public enum ImageFormat {
    JPEG("jpeg"),
    BMP("bmp"),
    PNG("png");

    private final String extension;

    /**
     * Constructor to initialize the image format with its file extension.
     */
    ImageFormat(String extension) {
        this.extension = extension;
    }
}
