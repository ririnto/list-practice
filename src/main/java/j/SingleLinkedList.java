package j;

public class SingleLinkedList<T> {
    private final Node<T> head = new Node<>(null);
    private int size;

    public int getSize() {
        return this.size;
    }

    public void append(T value) {
        final Node<T> node;
        if (this.size == 0) {
            node = this.head;
        } else {
            node = this.getNodeAt(this.size - 1);
        }

        node.next = new Node<>(value);

        this.size++;
    }

    public void appendLeft(T value) {
        final Node<T> node = new Node<>(value);

        node.next = this.head.next;
        this.head.next = node;

        this.size++;
    }

    public void insert(int index, T value) {
        if (index == 0) {
            this.appendLeft(value);
        } else if (index == this.size) {
            this.append(value);
        } else {
            final Node<T> prevNode = this.getNodeAt(index - 1);
            final Node<T> node = new Node<>(value);

            node.next = prevNode.next;
            prevNode.next = node;

            this.size++;
        }
    }

    public T get(int index) {
        return this.getNodeAt(index).value;
    }

    public T removeAt(int index) {
        this.checkIndex(index);

        final Node<T> prevNode;
        if (index == 0) {
            prevNode = this.head;
        } else {
            prevNode = this.getNodeAt(index - 1);
        }

        final Node<T> node = prevNode.next;
        prevNode.next = node.next;

        this.size--;

        return node.value;
    }

    private Node<T> getNodeAt(int index) {
        this.checkIndex(index);

        Node<T> node = this.head;
        for (int i = 0; i < index + 1; i++) {
            node = node.next;
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
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}
