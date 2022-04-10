import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {

    private final String message = "0000101010111111110";
    private final String frequencies = "D0000000000000100C0000000000000011B0000000000000010A0000000000000001";

    private final String message2 = "11111111111111111111";  // 20 times letter 'A'
    private final String frequencies2 = "A0000000000010100"; // 20 times letter 'A'


    @Test
    void completeInputEncoding_when_input_has_multiple_letters() {
        assertEquals(frequencies,Program.encode("DDDDCCCBBA").getKey());
        assertEquals(message,Program.encode("DDDDCCCBBA").getValue());
    }

    @Test
    void completeInputDecoding_when_input_has_multiple_letters() {
        assertEquals("DDDDCCCBBA",Program.decode(frequencies,message));
    }

    @Test
    void completeInputEncoding_when_input_has_one_letter() {
        assertEquals(frequencies2,Program.encode("AAAAAAAAAAAAAAAAAAAA").getKey());
        assertEquals(message2,Program.encode("AAAAAAAAAAAAAAAAAAAA").getValue());
    }

    @Test
    void completeInputDecoding_when_input_has_one_letter() {
        assertEquals("AAAAAAAAAAAAAAAAAAAA",Program.decode(frequencies2,message2));
    }
}