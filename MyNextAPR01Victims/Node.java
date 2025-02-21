public class Node {

    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return this.value;
    }

    public Node getNext() {
        return this.next
    }

    public void setNext(Node next) {
        this.next = next;
    }

}
