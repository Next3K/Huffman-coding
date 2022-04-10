import java.util.*;

/**
 * Class containing useful static functions.
 */
public class Helper {

    /**
     * Counts frequency of characters inside text.
     * @param text text to analyse.
     * @return Map of characters and corresponding frequencies.
     */
    public static Map<Character,Integer> getCharacterFrequency(String text) {
        if (text == null || text.length() == 0) {
            return null;
        }

        Map<Character, Integer> frequency = new HashMap<>(23);

        for (int i = 0; i < text.length(); i++) {
            if (!frequency.containsKey(text.charAt(i))) {
                frequency.put(text.charAt(i), 1);
            } else {
                frequency.put(text.charAt(i), frequency.get(text.charAt(i)) + 1);
            }
        }

        return frequency;
    }

    /**
     * Creates a queue of Nodes.
     * Ordering based on the frequency.
     * @param mapping mapping of character and corresponding frequency.
     * @return queue.
     */
    public static PriorityQueue<Node> createQueue(Map<Character,Integer> mapping) {
        if (mapping == null) {
            return null;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>(mapping.size());
        for (var elem : mapping.entrySet()) {
            queue.add(new Node(elem.getValue(), elem.getKey()));
        }

        return queue;
    }

    /**
     * Encodes text input.
     * @param encoding mapping of each character to unique code.
     * @param message text to be encoded.
     * @return encoded text, characters replaced with codes.
     */
    public static String encodeMessage(Map<Character,String> encoding, String message) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            String str = encoding.get(message.charAt(i));
            builder.append(str);
        }
        return builder.toString();
    }

    /**
     * Decodes previously encoded text input.
     * @param encoding mapping of characters to codes used to encode the initial text.
     * @param message message to decode.
     * @return decoded text, initial text before encoding.
     */
    public static String decodeMessage(Map<Character,String> encoding, String message) {
        Set<Map.Entry<Character, String>> entries = encoding.entrySet();
        int initialEntrySetSize = entries.size();
        Map<String, Character> map = new HashMap<>(initialEntrySetSize);
        for (var entry : entries) {
            map.put(entry.getValue(),entry.getKey()); // exchange keys and values
        }
        assert initialEntrySetSize == map.size();

        StringBuilder builder = new StringBuilder();

        int borderIndex = 0;
        for (int i = 1; i <= message.length(); i++) {
            if (map.containsKey(message.substring(borderIndex,i))) {
                builder.append(map.get(message.substring(borderIndex,i)));
                borderIndex = i;
            }
        }

        return builder.toString();
    }

}
