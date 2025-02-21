public class SimpleLinkedList {

    Node head;

    public void insert(int value) {
        Node newNode = new Node(value);
        newNode.setNext(this.head);
        this.head = newNode;
    }

}
