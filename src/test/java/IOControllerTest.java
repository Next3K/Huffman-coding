import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class IOControllerTest {

    @Test
    void readTest() throws IOException {
        assertEquals("My test text",IOController.read("test"));
    }

    @Test
    void writeTest() throws IOException {
        IOController.write("test", "just changed text");
        File input = new File("outputFiles" + File.separator + "test.txt");
        String str = new String(Files.readAllBytes(Paths.get(input.getAbsolutePath())));
        assertEquals("just changed text", str);
    }
}