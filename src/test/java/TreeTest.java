import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    private final PriorityQueue<Node> nodes = new PriorityQueue<>(4);
    private final PriorityQueue<Node> nodesTwo = new PriorityQueue<>(4);

    {
        nodes.add(new Node(4,'D'));
        nodes.add(new Node(2,'B'));
        nodes.add(new Node(1,'A'));
        nodes.add(new Node(3,'C'));

        nodesTwo.add(new Node(10,'A'));

    }

    @Test
    void createTreeFromNodes() {
        Node root = Tree.createTreeFromNodes(nodes); // root (character '-')
        assertEquals('D', root.getLeft().getCharacter());
        Node right = root.getRight(); // right node with two children
        assertEquals('C', right.getLeft().getCharacter());
        Node rightRight = right.getRight(); // right, right node with two children
        assertEquals('B', rightRight.getRight().getCharacter());
        assertEquals('A', rightRight.getLeft().getCharacter());

    }

    @Test
    void createTreeFromNodes_when_only_one_node_given() {
        Node root = Tree.createTreeFromNodes(nodesTwo);
        assertNull(root.getLeft());
        assertNull(root.getRight());

    }

    @Test
    void getCharacterEncodingFromTree() {
        var charCodes = Tree.getCharacterEncodingFromTree(Tree.createTreeFromNodes(nodes));
        assertEquals("0",charCodes.get('D'));
        assertEquals("10",charCodes.get('C'));
        assertEquals("111",charCodes.get('B'));
        assertEquals("110",charCodes.get('A'));
    }

    @Test
    void getCharacterEncodingFromTree_when_tree_only_has_one_node() {
        var charCodes = Tree.getCharacterEncodingFromTree(Tree.createTreeFromNodes(nodesTwo));
        assertEquals("1",charCodes.get('A'));
    }


    @Test
    void convertTreeIntoFrequencyStringTest() {
        Node root = Tree.createTreeFromNodes(nodes);
        assertEquals("D0000000000000100C0000000000000011B0000000000000010A0000000000000001",
                Tree.convertTreeIntoFrequencyString(root));
    }

    @Test
    void convertFrequencyStringIntoTreeTest() {
        Node converted = Tree.convertFrequencyStringIntoTree(
                "D0000000000000100C0000000000000011B0000000000000010A0000000000000001");
        assertEquals('D', converted.getLeft().getCharacter());
        Node right = converted.getRight(); // right node with two children
        assertEquals('C', right.getLeft().getCharacter());
        Node rightRight = right.getRight(); // right, right node with two children
        assertEquals('B', rightRight.getRight().getCharacter());
        assertEquals('A', rightRight.getLeft().getCharacter());

    }
}