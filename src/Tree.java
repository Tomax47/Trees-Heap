public interface Tree<T extends Comparable<T>> {

    Tree<T> insert(T data);

    void delete(T data);
    void traverse();

    T getMin();
    T getMax();

    boolean isEmpty();

}
