import javafx.util.Pair;

import java.util.Map;
import java.util.PriorityQueue;

public class Program {

    /**
     * Encodes text with Huffman tree.
     * @param text text to encode.
     * @return Pair of strings: frequency table, encoded message.
     */
    public static Pair<String,String> encode(String text) {
        PriorityQueue<Node> q = Helper.createQueue(Helper.getCharacterFrequency(text));
        Node root  = Tree.createTreeFromNodes(q);
        Map<Character, String> characterEncoding = Tree.getCharacterEncodingFromTree(root);
        String encoded = Helper.encodeMessage(characterEncoding,text);
        String frequencyTable = Tree.convertTreeIntoFrequencyString(root);
        return new Pair<>(frequencyTable, encoded);
    }

    /**
     * Decodes previously encoded text using frequency table.
     * @param frequencyTable String representing frequency table.
     * @param text encoded text.
     * @return decoded text.
     */
    public static String decode(String frequencyTable, String text) {
        Node root = Tree.convertFrequencyStringIntoTree(frequencyTable);
        Map<Character, String> characterEncoding = Tree.getCharacterEncodingFromTree(root);
        return Helper.decodeMessage(characterEncoding,text);
    }
}
