import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {

    private final String message = "000001011";
    private final String frequencies = "D0000000000000100C0000000000000011B0000000000000010A0000000000000001";

    @Test
    void completeInputEncoding() {
        assertEquals(message,Program.encode("DDDDCCCBBA").getKey());
        assertEquals(frequencies,Program.encode("DDDDCCCBBA").getValue());
    }

    @Test
    void completeInputDecoding() {
        assertEquals("DDDDCCCBBA",Program.decode(frequencies,message));
    }
}