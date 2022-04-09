import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

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

    public static Pair<String,String> parseEncodedIntoTwoParts(String encodedText) {
        return null;
    }

    public static PriorityQueue<Node> createQueue(Map<Character,Integer> nodes) {
        return null;
    }

    public static String encodeMessage(Map<Character,String> encoding, String message) {
        return null;
    }

    public static String decodeMessage(Map<Character,String> encoding, String message) {
        return null;
    }

}
