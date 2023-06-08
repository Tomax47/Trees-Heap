import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import java.awt.*;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;

//Annotations r from the lombok dependency
@Data
public class Node <T extends Comparable<T>> { //Extending the Comparable class is needed to compare the left & right children

    @NonNull private T data;
    private Node<T> leftChild;
    private Node<T> rightChild;

    /**
     Added for the AVL tree ->
     it equals one, cuz when a node is created or inserted into an AVL tree, its a leaf node, and we know that the height of a leaf node is equal to one
     */
    private int height = 1;

    /**
     The color variable is dedicated for the RBT, it can be valued by red or black, aslo be a String, Enum or a Color using the Color class provided by Java.
     We create the parent node cuz it's crucial to keep track of the parent node and even the grandparent in the RBT
     */
    private Color color = RED;
    @ToString.Exclude private Node<T> parent;

    public boolean isLeftChild() {
        return this == parent.getLeftChild();
    }

    public void flipColor() {
        setColor(color == RED ? BLACK : RED);
    }
}
