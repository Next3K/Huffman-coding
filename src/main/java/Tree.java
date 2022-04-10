import javafx.util.Pair;

import java.util.*;

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
     * Method converting tree to its string representation.
     * String representation is a frequency table: example "D4C3B2A1"
     * - D occurred 4 times
     * - C occurred 3 times
     * - B occurred 2 times
     * - A occurred 1 times
     * This frequency table can be converted into tree
     * @param root root node of a tree.
     * @return String representation.
     */
    public static String convertTreeIntoTreeRepresentation(Node root) {
        Map<Character, Integer> mapping = getTreeRepresentation(new HashMap<>(), root);
        TreeSet<Pair<Character,Integer>> pairs =
                new TreeSet<>((a,b) -> b.getValue() - a.getValue());
        StringBuilder builder = new StringBuilder();
        for (var entry : mapping.entrySet()) {
            pairs.add(new Pair<>(entry.getKey(),entry.getValue()));
        }
        for (var entry : pairs) {
            builder.append(entry.getKey()); // example D4 (letter 'D' * 4)
            builder.append(entry.getValue());
        }
        return builder.toString();
    }

    public static Node convertTreeRepresentationIntoTree(String representation) {
        return null;
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
