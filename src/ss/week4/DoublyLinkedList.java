package ss.week4;

import java.util.LinkedList;

public class DoublyLinkedList<E> {

    private int size;
    private Node head;

    //@ ensures size == 0;
    public DoublyLinkedList() {
        size = 0;
        head = new Node(null);
        head.next = head;
        head.previous = head;
    }

    /**
     * Inserts a new element at a given index in the list.
     *
     * @param index   The index at which to insert the element
     * @param element The element to add
     */
    /*@ requires element != null;
    requires index <= size;
    ensures size == \old(size) + 1;
    ensures get(index) == element;
     @*/
    public void add(int index, E element) {
        Node newNode = new Node(element);
        if (index == 0) {
            Node h = head;
            head = newNode;
            head.previous = head;
            head.next = h;
            h.previous = head;
        } else {
            Node prevNode = getNode(index - 1);
            Node nextNode = getNode(index);
            prevNode.next = newNode;
            nextNode.previous = newNode;
            newNode.previous = prevNode;
            newNode.next = nextNode;
        }
        size++;
    }

    /**
     * Removes an element at a given index
     *
     * @param index the index to remove the element at
     */
    /*@ requires index <= size;
    ensures size == \old(size) - 1;
     @*/
    public void remove(int index) {
        Node next = getNode(index + 1);
        if (index == 0){
            head = next;
            head.previous = head;
        } else {
            Node prev = getNode(index - 1);
            prev.next = next;
            next.previous = prev;
        }
        size--;
    }

    //@ requires index <= size;
    public E get(int index) {
        Node p = getNode(index);
        return p.element;
    }

    /**
     * The node containing the element with the specified index.
     * The head node if the specified index is -1.
     */
    //@ requires i > -1 && i < size;
    public Node getNode(int i) {
        Node p = head;
        int pos = 0;
        while (pos < i) {
            p = p.next;
            pos = pos + 1;
        }
        return p;
    }

    public int size() {
        return this.size;
    }

    public class Node {
        public Node(E element) {
            this.element = element;
            this.next = null;
            this.previous = null;
        }

        private final E element;
        public Node next;
        public Node previous;

        public E getElement() {
            return element;
        }
    }
}
