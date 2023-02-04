package j;

public class DoubleLinkedList<T> {
    private final Node<T> head = new Node<>(null);
    private final Node<T> tail = new Node<>(null);
    private int size;

    public DoubleLinkedList() {
        this.head.next = this.tail;
        this.tail.next = this.head;
    }

    public int getSize() {
        return this.size;
    }

    public void append(T value) {
        final Node<T> newNode = new Node<>(value);
        newNode.prev = this.tail.prev;
        newNode.next = this.tail;
        this.tail.prev.next = newNode;
        this.tail.prev = newNode;

        this.size++;
    }

    public void appendLeft(T value) {
        final Node<T> newNode = new Node<>(value);
        newNode.prev = this.head;
        newNode.next = this.head.next;
        this.head.next.prev = newNode;
        this.head.next = newNode;

        this.size++;
    }

    public void insert(int index, T value) {
        if (index == 0) {
            this.appendLeft(value);
        } else if (index == this.size) {
            this.append(value);
        } else {
            final Node<T> nextNode = this.getNodeAt(index);
            final Node<T> node = new Node<>(value);

            nextNode.prev.next = node;
            node.prev = nextNode.prev;
            node.next = nextNode;
            nextNode.prev = node;

            this.size++;
        }
    }

    public T get(int index) {
        return this.getNodeAt(index).value;
    }

    public T removeAt(int index) {
        final Node<T> node = this.getNodeAt(index);
        node.prev.next = node.next;
        node.next.prev = node.prev;

        this.size--;

        return node.value;
    }

    private Node<T> getNodeAt(int index) {
        this.checkIndex(index);

        Node<T> node;
        if (index < this.size / 2) {
            node = this.head;
            for (int i = 0; i < index + 1; i++) {
                node = node.next;
            }
        } else {
            node = this.tail;
            for (int i = 0; i < this.size - index; i++) {
                node = node.prev;
            }
        }

        return node;
    }

    private void checkIndex(int index) {
        if (index < 0 || this.size <= index) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    private static class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}
