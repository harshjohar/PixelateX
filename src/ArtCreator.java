import java.io.IOException;

/**
 * <h1>Art Creator class</h1>
 * <p>This is the main entry point of the project.</p>
 * <p>
 * Parses command line arguments. First it checks for options. If there are any options it stores the options and
 * removes them re-creates the array without the options. Then it checks if the number of CLI are valid, if not it prints
 * usage message. If however, it is valid it passes them accordingly to {@link ImageProcessing} constructors.
 * <p>
 * <br>
 * <h3>Static Fields</h3>
 * {@link ArtCreator#popUpImage}
 * {@link ArtCreator#saveAsImage}
 * {@link ArtCreator#mode}
 *
 * @author harshjohar
 */
public class ArtCreator {
    /**
     * Message for usage of the program via command line interface in case the user uses it wrongly or needs help.
     */
    private static final String usage = """
            Usage: java ArtCreator <options> <pixels(optional)> <source image path>
            Options:
            -i to disable image popup
            -s if you wish to render and save the text as image
            -a for set of characters 1
            -b for set of characters 2
            -c for set of characters 3
            -d for set of characters 4
            Note that use only one of the a,b or c option at a time otherwise the behaviour is undefined
            """;

    private static boolean popUpImage = false;
    private static boolean saveAsImage = false;
    private static int mode = -1;

    public static void main(String[] args) {
        parseArguments(args);
    }

    /**
     * Calls different options available with repect to the number of arguments
     *
     * @param args Command line arguments
     */
    private static void parseArguments(String[] args) {
        if (args.length == 0) {
            System.out.println(usage);
            return;
        }

        args = parseOptions(args);
        if (args.length == 0 || args.length > 2) {
            System.out.println(usage);
            return;
        }

        if (args.length == 1) {
            printAndPerformActions(args[0]);
            return;
        }

        // args length == 2
        printAndPerformActions(Integer.parseInt(args[0]), args[1]);
    }

    /**
     * Parses the options(Strings that start with '-'), sets the state of global variables and returns a new array without those options
     *
     * @param args Command line arguments
     * @return new array of arguments
     */
    private static String[] parseOptions(String[] args) {
        while (args[0].startsWith("-")) {
            boolean isDone = false;
            if (args[0].contains("i")) {
                popUpImage = true;
                isDone = true;
            }
            if (args[0].contains("s")) {
                saveAsImage = true;
                isDone = true;
            }
            if (args[0].contains("d")) {
                mode = 2;
                isDone = true;
            } else if (args[0].contains("c")) {
                mode = 1;
                isDone = true;
            } else if (args[0].contains("b")) {
                mode = 0;
                isDone = true;
            } else if (args[0].contains("a")) {
                mode = -1;
                isDone = true;
            }

            if (isDone) {
                args = getArgsAndReturnNext(args);
                continue;
            }
            // let's roll again
            switch (args[0]) {
                case "-i" -> popUpImage = true;
                case "-s" -> saveAsImage = true;
                case "-a" -> mode = -1;
                case "-b" -> mode = 0;
                case "-c" -> mode = 1;
                case "-d" -> mode = 2;
                default -> {
                    System.out.println(usage);
                    System.exit(0);
                }
            }
            args = getArgsAndReturnNext(args);
        }
        return args;
    }

    private static String[] getArgsAndReturnNext(String[] args) {
        String[] argsCopy = new String[args.length];
        System.arraycopy(args, 0, argsCopy, 0, argsCopy.length);
        args = new String[args.length - 1];
        System.arraycopy(argsCopy, 1, args, 0, argsCopy.length - 1);
        return args;
    }

    /**
     * Generate art for the given filePath with the default pixel count
     *
     * @param filePath Path to the image
     */
    private static void printAndPerformActions(String filePath) {
        try {
            ImageProcessing processedImage = new ImageProcessing(filePath);
            handleOutput(processedImage);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Generate art for the given filePath with the given pixel count and filePath
     *
     * @param sides    pixelCount
     * @param filePath Path to the image
     */
    private static void printAndPerformActions(int sides, String filePath) {
        try {
            System.out.println("Generating art...");
            if(sides > 600) {
                System.out.println("Maximum permissible size is 600 x 600");
                System.out.println("Generating max sized image...");
            }
            sides = Math.min(600, sides);
            ImageProcessing processedImage = new ImageProcessing(filePath, sides);
            handleOutput(processedImage);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Pixels should be an integer.");
        }
    }

    private static void handleOutput(ImageProcessing processedImage) throws IOException {
        ImageToAsciiGenerator generator = new ImageToAsciiGenerator(processedImage.getSide());
        String text = generator.getAsciiArt(processedImage.getImage(), mode + 1);
        if (popUpImage)
            new ImageShower(text, processedImage.getSide());
        else System.out.println(text);
        if (saveAsImage)
            new RenderAndSaveTextAsImage(processedImage.getSide()).save(text);
    }
}
