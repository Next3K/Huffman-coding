import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    private final Node n1 = new Node(10,'a');
    private final Node n2 = new Node(2,'z');

    @Test
    void getDataTest() {
        assertEquals(10, n1.getData());
    }

    @Test
    void getCharacterTest() {
        assertEquals('a', n1.getCharacter());
    }

    @Test
    void getLeftTest() {
        assertNull(n1.getLeft());
        n1.setLeft(n2);
        assertEquals(n2,n1.getLeft());
    }

    @Test
    void getRightTest() {
        assertNull(n1.getLeft());
        n1.setLeft(n2);
        assertEquals(n2,n1.getLeft());
    }

    @Test
    void setDataTest() {
        n1.setData(11);
        assertEquals(11, n1.getData());
    }

    @Test
    void setCharacterTest() {
        n1.setCharacter('o');
        assertEquals('o', n1.getCharacter());
    }

    @Test
    void setLeftTest() {
        n1.setLeft(n2);
        assertEquals(n2,n1.getLeft());
    }

    @Test
    void setRightTest() {
        n1.setLeft(n2);
        assertEquals(n2,n1.getLeft());
    }

    @Test
    void compareToTest_return_negative_when_smaller() {
        assertTrue(n2.compareTo(n1) < 0);
    }

    @Test
    void compareToTest_return_positive_when_bigger() {
        assertTrue(n1.compareTo(n2) > 0);
    }

    @Test
    void compareToTest_return_zero_when_equal() {
        n1.setData(10);
        n2.setData(10);
        assertEquals(0, n1.compareTo(n2));
    }
}