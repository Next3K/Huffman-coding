import javafx.util.Pair;

import java.util.*;

/**
 * Provides useful static functions related to Huffman tree.
 */
public class Tree {

    /**
     * Creates tree from collection of nodes.
     * @param q priority queue of nodes.
     * @return root node of created tree.
     */
    public static Node createTreeFromNodes(PriorityQueue<Node> q) {

        if (q == null) {
            return null;
        }

        if (q.size() == 1) {
            return q.peek();
        }

        Node root = null;

        while (q.size() > 1) {

            Node x = q.peek();
            q.poll();
            Node y = q.peek();
            q.poll();
            assert y != null;
            Node f = new Node(x.getData() + y.getData(), '-');

            f.setLeft(x);
            f.setRight(y);
            root = f;
            q.add(f);
        }

        return root;
    }


    /**
     * Traverses the tree and maps characters to series of bits.
     * Ensures that if only one character appears in the tree, it is represented by bit '1'.
     * @param root root node of the tree.
     * @return map of characters and corresponding strings.
     */
    public static Map<Character,String> getCharacterEncodingFromTree(Node root) {
        var charEncoding = getCharacterEncodingRecursive(new HashMap<>(), root, "");
        if (charEncoding.size() == 1) { // if only one letter in mapping, replace '-' with  '1'
            Character key = null;
            for (var entry : charEncoding.entrySet()) {
                key = entry.getKey();
                break;
            }
            charEncoding.put(key, "1");
        }
        return charEncoding;
    }

    /**
     * Recursive function that does all the 'hard work' that Tree.getCharacterEncodingFromTree method
     * seems to be doing.
     * @param charCodes represents mapping of characters and strings (codes).
     * @param root current root node.
     * @param s current path counting from original root.
     * @return mapping with one more entry (created from current node).
     */
    private static Map<Character,String> getCharacterEncodingRecursive(
            Map<Character, String> charCodes, Node root, String s) {

        if (root == null) {
            return charCodes;
        }

        if (root.getCharacter() != '-') {
            charCodes.put(root.getCharacter(),s);
        }

        charCodes =  getCharacterEncodingRecursive(charCodes, root.getLeft(), s + "0");
        charCodes =  getCharacterEncodingRecursive(charCodes, root.getRight(), s + "1");

        return charCodes;
    }

    /**
     * Converts Huffman tree into string that represents frequency of letters.
     * @param root root node of a Huffman tree.
     * @return String representation of letter frequencies.
     */
    public static String convertTreeIntoFrequencyString(Node root) {
        Map<Character, Integer> mapping = getTreeRepresentation(new HashMap<>(), root);
        TreeSet<Pair<Character,Integer>> pairs =
                new TreeSet<>((a,b) -> b.getValue() - a.getValue()); // sort pairs by integer, the smallest first
        StringBuilder builder = new StringBuilder();
        for (var entry : mapping.entrySet()) {
            pairs.add(new Pair<>(entry.getKey(),entry.getValue()));
        }
        for (var entry : pairs) {
            builder.append(entry.getKey()); // letter
            int number = entry.getValue(); // frequency
            String binaryRepresentation =
                    String.format("%16s", Integer.toBinaryString(number)).replace(" ", "0");
            builder.append(binaryRepresentation); // covert decimal to binary
        }
        return builder.toString();
    }

    /**
     * Converts string with frequency of letters into tree.
     * String representation is a frequency table:
     *  "D0000000000000100C0000000000000011B0000000000000010A0000000000000001"
     * - D occurred 4 times
     * - C occurred 3 times
     * - B occurred 2 times
     * - A occurred 1 times
     *  Letter can occur max: 2^16 times.
     *  Letter can occur min:    1 time.
     * @param representation string representing frequency of letters.
     * @return Tree corresponding to the frequency table.
     */
    public static Node convertFrequencyStringIntoTree(String representation) {

        Map<Character, Integer> mapping = new HashMap<>(representation.length() / 17);

        for (int i = 0; i < representation.length(); i += 17) {
            Character c = representation.charAt(i);
            String numberPart = representation.substring(i + 1, i + 17);
            int counter = Integer.parseInt(numberPart,2);
            mapping.put(c,counter);
        }

        return createTreeFromNodes(Helper.createQueue(mapping)); // map -> queue -> Huffman tree
    }

    /**
     * Recursive function doing all 'hard work' on behalf of Tree.convertTreeIntoTreeRepresentation(...).
     * @param mapping Character and corresponding number of occurrences.
     * @param node node to start.
     * @return map with new entries.
     */
    private static Map<Character, Integer> getTreeRepresentation(Map<Character,Integer> mapping, Node node) {
        char c = node.getCharacter();
        if (c != '-') {
            mapping.put(c,node.getData());
        }
        if (node.getRight() != null) {
            getTreeRepresentation(mapping,node.getRight());
        }
        if (node.getLeft() != null) {
            getTreeRepresentation(mapping,node.getLeft());
        }
        return mapping;
    }
}
