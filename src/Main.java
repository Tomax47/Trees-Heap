import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random();

        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        for (int i = 0; i < 10; i++) {
            int number = rnd.nextInt(-10,10);
            binarySearchTree.insert(number);
            System.out.print(number+" ");
        }

        System.out.println("\n"+binarySearchTree.toString());
        System.out.println("Max in the BST : "+binarySearchTree.getMax());
        System.out.println("Min in the BST : "+binarySearchTree.getMin());
        System.out.println("Min in the node of the value 1 :"+binarySearchTree.getNode(1));

        System.out.println("\nIn-Order traversing a tree : ");
        binarySearchTree.traverse();


//        System.out.println(binarySearchTree.getMatchingNode(3, binarySearchTree.getMainNode()));
    }
}
