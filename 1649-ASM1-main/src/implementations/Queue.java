package implementations;


import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {
    private Node<E> head;
    private int size;


    public void removeElementAt(int deleteIndex) {
        if (deleteIndex < 0 || deleteIndex >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        if (deleteIndex == 1) {
            head = head.next;
            size--;
            return;
        }

        Node<E> current = head;
        for (int i = 1; i < deleteIndex ; i++) {
            current = current.next;
        }

        Node<E> deletedNode = current.next;
        current.next = deletedNode.next;
        size--;
    }
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    public Queue() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    private void ensureNonEmpty() {
        if (this.size == 0) {
            throw new IllegalStateException("Queue is empty");
        }
    }

    @Override
    public void enqueue(E element) {
        Node<E> newNode = new Node<>(element);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node<E> current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        this.size++;
    }

    @Override
    public E dequeue() {
        ensureNonEmpty();
        E element = this.head.element;
        Node<E> temp = this.head.next;
        this.head.next = null;
        this.head = temp;
        this.size--;
        return element;
    }

    @Override
    public void offer(E element) {

    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E peek() {
        ensureNonEmpty();
        return this.head.element;
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = this.head;
        while (current != null) {
            sb.append(current.element).append(" ");
            current = current.next;
        }
        return sb.toString();
    }
}