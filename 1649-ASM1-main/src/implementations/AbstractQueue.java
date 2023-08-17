package implementations;

public interface AbstractQueue<E> extends Iterable<E> {
    void enqueue(E element);

    E dequeue();


    int size();
    boolean isEmpty();
}

