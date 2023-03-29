import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Converts grayscale {@link BufferedImage} to ASCII art
 *
 * @author harshjohar
 */
public class ImageToAsciiGenerator {
    private final static String[] ASCII_CHARACTER_SETS = new String[]{" .,_-=+:;cba!?1023456789$W#@Ñ", " ░▄▀█", " .,_-=+:;cba!?1023456789$W#@Ñ░", " .,_-=+:;cba!?1023456789$W#@Ñ░▄▀█"};
    private final static int[] ASCII_CHARACTER_SETS_LENGTH = new int[]{ASCII_CHARACTER_SETS[0].length(), ASCII_CHARACTER_SETS[1].length(), ASCII_CHARACTER_SETS[2].length(), ASCII_CHARACTER_SETS[3].length()};

    private final int side;

    /**
     * Initialise the side variable
     *
     * @param side Number of pixels in the grayscale {@link BufferedImage}
     */
    ImageToAsciiGenerator(int side) {
        this.side = side;
    }

    /**
     * Maps the average of red, blue and green value of grayscale {@link BufferedImage} to the given {@code characterSet}
     * and converts the image into ASCII art
     *
     * @param image {@link BufferedImage} whose height = width = side that was initialized for this object.
     * @param code The code of available character sets
     * @return The ASCII art string.
     */
    public String getAsciiArt(BufferedImage image, int code) {
        StringBuilder output = new StringBuilder();
        for(int i=0; i<side; i++) {
            for(int j=0; j<side; j++) {
                Color color = new Color(image.getRGB(j, i));
                int rgb = color.getRed() + color.getGreen() + color.getBlue();
                rgb /= 3;
                double rgb2 = (double) rgb / 255;
                int index = (int) Math.round(rgb2 * (ASCII_CHARACTER_SETS_LENGTH[code] - 1));
                output.append(ASCII_CHARACTER_SETS[code].charAt(index));
            }
            output.append("\n");
        }
        return output.toString();
    }
}
