import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

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
     * Parse encoded text (bits + encoded tree representation) into two parts.
     * @param encodedText encoded text (bits + encoded tree representation).
     * @return pair of Strings: message bits and encoded tree representation.
     */
    public static Pair<String,String> parseEncodedIntoTwoParts(String encodedText) {
        if (encodedText.length() == 0) {
            return null;
        }

        String messagePart = null;
        String codePart = null;
        boolean found = false;

        for (int i = 0; i < encodedText.length(); i++) {
            if (encodedText.charAt(i) != '0' && encodedText.charAt(i) != '1') {
                found = true;

                if (i == encodedText.length() - 1) {
                    return new Pair<>(encodedText.substring(0,i - 1),encodedText.substring(i - 1));
                }

                int startCodeIndex = i - 2;
                if (startCodeIndex != 0) {
                    messagePart = encodedText.substring(0,startCodeIndex);
                }
                codePart = encodedText.substring(startCodeIndex);
                break;
            }
        }

        if (!found) { // did not find part encoding the tree
            return new Pair<>(encodedText,null); // return message part and null
        }

        return new Pair<>(messagePart,codePart);
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

    public static String encodeMessage(Map<Character,String> encoding, String message) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            String str = encoding.get(message.charAt(i));
            builder.append(str);
        }
        return builder.toString();
    }

    public static String decodeMessage(Map<Character,String> encoding, String message) {
        return null;
    }

}
