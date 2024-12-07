package backend.academy.enums;

import lombok.Getter;

@Getter
public enum ImageFormat {
    JPEG("jpeg"),
    BMP("bmp"),
    PNG("png");

    private final String extension;

    ImageFormat(String extension) {
        this.extension = extension;
    }
}

