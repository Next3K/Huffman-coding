import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {

    private final Map<Character, Integer> frequencies = new HashMap<>(4);
    private final Map<Character, String> characterEncoding = new HashMap<>(4);

    {
        frequencies.put('a',5);
        frequencies.put('c',1);
        frequencies.put('V',7);
        frequencies.put('R',3);

        characterEncoding.put('A',"000");
        characterEncoding.put('B',"001");
        characterEncoding.put('C',"01");
        characterEncoding.put('D',"1");

    }


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
    void createQueueTest() {
        PriorityQueue<Node> queue = Helper.createQueue(frequencies);
        assertEquals('c',queue.poll().getCharacter());
        assertEquals('R',queue.poll().getCharacter());
        assertEquals('a',queue.poll().getCharacter());
        assertEquals('V',queue.poll().getCharacter());
    }

    @Test
    void createQueueTest_returns_null_when_input_is_null() {
        PriorityQueue<Node> queue = Helper.createQueue(null);
        assertNull(queue);
    }

    @Test
    void encodeMessageTest() {
        String message = "ABCD";
        assertEquals("000001011", Helper.encodeMessage(characterEncoding,message));
    }

    @Test
    void decodeMessageTest() {
        String messageEncoded = "000001011";
        assertEquals("ABCD", Helper.decodeMessage(characterEncoding,messageEncoded));
    }
}