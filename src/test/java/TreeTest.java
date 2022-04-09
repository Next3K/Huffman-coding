import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
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
        throw new UnsupportedOperationException("Test not implemented");
    }

    @Test
    void convertTreeIntoTreeRepresentation() {
        throw new UnsupportedOperationException("Test not implemented");
    }

    @Test
    void convertTreeRepresentationIntoTree() {
        throw new UnsupportedOperationException("Test not implemented");
    }
}