package implementations;

public interface AbstractStack<E> extends Iterable<E> {
    void push(E element);
    boolean isEmpty();
}
