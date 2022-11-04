/**
 * Node class used for constructing linked lists associated to hash table entries
 * @param <Record> Record object
 */

public class Node<Record> {
    private Node<Record> next;
    private final Record rec;


    /**
     * Constructor initializes the node
     * @param rec Record object
     */
    public Node(Record rec) {
        next = null;
        this.rec = rec;
    }

    /**
     * Getter method for the next node
     * @return next node
     */
    public Node<Record> getNext() {
        return next;
    }

    /**
     * Setter method for next node;
     * @param node next node
     */
    public void setNext(Node<Record> node) {
        next = node;
    }

    /**
     * Getter method for the Record object in node
     * @return Record object in node.
     */
    public Record getRec() {
        return rec;
    }
}



