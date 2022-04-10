import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

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

    public static String convertTreeIntoTreeRepresentation(Node root) {
        return null;
    }

    public static Node convertTreeRepresentationIntoTree(String representation) {
        return null;
    }
}
