import lombok.Getter;
import lombok.Setter;

public class Node implements Comparable<Node> {

    public Node(int data, char character) {
        this.data = data;
        this.character = character;
    }

    @Override
    public int compareTo(Node o) {
        return this.getData() - o.getData();
    }

    @Getter @Setter
    int data;

    @Getter @Setter
    private char character;

    @Getter @Setter
    private Node left;

    @Getter @Setter
    private Node right;


}
