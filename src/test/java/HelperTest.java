import javafx.util.Pair;
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
        String input1 = "00101101001000101010D10C10B0A";
        Pair<String, String> twoParts = Helper.parseEncodedIntoTwoParts(input1);
        assertEquals("001011010010001010",twoParts.getKey());
        assertEquals("10D10C10B0A",twoParts.getValue());
    }

    @Test
    void parseEncodedIntoTwoPartsTest_null_when_empty_input() {
        String input1 = "";
        Pair<String, String> twoParts = Helper.parseEncodedIntoTwoParts(input1);
        assertNull(twoParts);
    }

    @Test
    void parseEncodedIntoTwoPartsTest_null_when_empty_message() {
        String input1 = "10D10C10B0A";
        Pair<String, String> twoParts = Helper.parseEncodedIntoTwoParts(input1);
        assertNull(twoParts.getKey());
        assertEquals("10D10C10B0A",twoParts.getValue());
    }

    @Test
    void parseEncodedIntoTwoPartsTest_null_when_empty_code() {
        String input1 = "001011010010001010";
        Pair<String, String> twoParts = Helper.parseEncodedIntoTwoParts(input1);
        assertEquals("001011010010001010",twoParts.getKey());
        assertNull(twoParts.getValue());
    }

    @Test
    void parseEncodedIntoTwoPartsTest_when_tree_only_has_one_node() {
        String input1 = "00000000000000000000A"; // only letter 'A' present inside the message
        Pair<String, String> twoParts = Helper.parseEncodedIntoTwoParts(input1);
        assertEquals("0000000000000000000",twoParts.getKey());
        assertEquals("0A",twoParts.getValue());
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