package implementations;

public interface AbstractQueue<E> extends Iterable<E> {
    void enqueue(E element);

    E dequeue();

    void offer(E element);
    E poll();
    E peek();
    int size();
    boolean isEmpty();
}

