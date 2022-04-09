import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {

    @Test
    void getCharacterFrequencyTest() {
        String text1 = "AaLa Ma 100";
        String text2 = "\u1001\u1212\u2202\u2202\u2202";
        Map<Character, Integer> characterFrequency1 = Helper.getCharacterFrequency(text1);
        assertEquals(1,characterFrequency1.get('A'));
        assertEquals(3,characterFrequency1.get('a'));
        assertEquals(1,characterFrequency1.get('L'));
        assertEquals(1,characterFrequency1.get('M'));
        assertEquals(1,characterFrequency1.get('1'));
        assertEquals(2,characterFrequency1.get('0'));
        assertEquals(2,characterFrequency1.get(' '));

        Map<Character, Integer> characterFrequency2 = Helper.getCharacterFrequency(text2);
        assertEquals(1,characterFrequency2.get('\u1001'));
        assertEquals(1,characterFrequency2.get('\u1212'));
        assertEquals(3,characterFrequency2.get('\u2202'));
    }

    @Test
    void getCharacterFrequencyTest_null_when_empty_input() {
        String text = null;
        assertNull(Helper.getCharacterFrequency(text));
    }

    @Test
    void getCharacterFrequencyTest_null_when_null_input() {
        String text1 = null;
        assertNull(Helper.getCharacterFrequency(text1));
    }

    @Test
    void parseEncodedIntoTwoPartsTest() {
        throw new UnsupportedOperationException("Test not implemented");
    }

    @Test
    void createQueueTest() {
        throw new UnsupportedOperationException("Test not implemented");
    }

    @Test
    void encodeMessageTest() {
        throw new UnsupportedOperationException("Test not implemented");
    }

    @Test
    void decodeMessageTest() {
        throw new UnsupportedOperationException("Test not implemented");
    }
}