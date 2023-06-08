import lombok.Data;
import lombok.NonNull;

@Data
public class Node <T extends Comparable<T>> { //Extending the Comparable class is needed to compare the left & right children

    @NonNull private T data;
    private Node<T> leftChild;
    private Node<T> rightChild;

}
