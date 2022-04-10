import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IOController {

    public static String read(String filename) throws IOException {
        File input = new File("inputFiles" + File.separator + filename + ".txt");
        return new String(Files.readAllBytes(Paths.get(input.getAbsolutePath())));
    }

    public static void write(String filename, String text) throws IOException {
        File output = new File("outputFiles" + File.separator + filename + ".txt");
        Files.write(Paths.get(output.getAbsolutePath()), text.getBytes());
    }
}
