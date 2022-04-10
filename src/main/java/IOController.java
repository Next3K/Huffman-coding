import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class providing useful IO methods.
 */
public class IOController {

    /**
     * Read text from file inside "inputFiles" directory which has.
     * @param filename name of the file.
     * @return text contents of the file.
     * @throws IOException Any IO exception.
     */
    public static String read(String filename) throws IOException {
        File input = new File("inputFiles" + File.separator + filename + ".txt");
        return new String(Files.readAllBytes(Paths.get(input.getAbsolutePath())));
    }

    /**
     * Writes given text to file inside "outputFiles" directory.
     * @param filename name of file to write the text.
     * @param text text to write.
     * @throws IOException Any IO exception.
     */
    public static void write(String filename, String text) throws IOException {
        File output = new File("outputFiles" + File.separator + filename + ".txt");
        Files.write(Paths.get(output.getAbsolutePath()), text.getBytes());
    }
}
