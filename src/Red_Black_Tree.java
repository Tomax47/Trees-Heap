import static java.awt.Color.BLACK;
import static java.awt.Color.RED;

public class Red_Black_Tree <T extends Comparable<T>> implements Tree<T>{

    /**
       COMPLETION OF SOME METHOD IS REQUIRED!
       NEEDS RECAPPING...
     */
    private Node<T> root;
    @Override
    public Tree<T> insert(T data) {
        Node<T> node = new Node<>(data);
        root = insert(root, node);
        recolorAndRotate(node);
        return this;
    }

    public Node<T> insert(Node<T> node, Node<T> nodeToInsert) {
        if (node == null) {
            return nodeToInsert ;
        }

        if (nodeToInsert.getData().compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(node.getLeftChild(),nodeToInsert));
            node.getLeftChild().setParent(node);
        } else if (nodeToInsert.getData().compareTo(node.getData()) > 0) {
            node.setRightChild(insert(node.getRightChild(),nodeToInsert));
            node.getRightChild().setParent(node);
        }
        return node;
    }

    @Override
    public void delete(T data) {

    }

    @Override
    public void traverse() {

    }

    @Override
    public T getMin() {
        return null;
    }

    @Override
    public T getMax() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private void recolorAndRotate(Node<T> node) {
        Node<T> parent = node.getParent();
        if (node != root && parent.getColor() == RED) {
            Node<T> grandParentNode = node.getParent().getParent();
            Node<T> uncleNode = parent.isLeftChild() ? grandParentNode.getRightChild() : grandParentNode.getLeftChild();

            if (uncleNode != null && uncleNode.getColor() == RED) {
                //recoloring
                handleRecoloring(parent,uncleNode,grandParentNode);
            } else if (parent.isLeftChild()) {
                //left-heavy situation
                handleLeftRotation(node,parent,grandParentNode);
            } else if (!parent.isLeftChild()) {
                //right-heavy situation
                handleRightRotation(node,parent,grandParentNode);
            }
        }
        root.setData((T) BLACK);
    }

    private void handleRecoloring(Node<T> parentNode, Node<T> uncleNode, Node<T> grandParentNode) {
        uncleNode.flipColor();
        parentNode.flipColor();
        grandParentNode.flipColor();
        recolorAndRotate(grandParentNode);
    }

    private void handleLeftRotation(Node<T> node, Node<T> parentNode, Node<T> grandParentNode) {
        if (!node.isLeftChild()) {
            rotateLeft(parentNode);
        }

        parentNode.flipColor();
        grandParentNode.flipColor();
        rotateRight(grandParentNode);
        recolorAndRotate(node.isLeftChild() ? parentNode : grandParentNode);
    }

    private void handleRightRotation(Node<T> node, Node<T> parentNode, Node<T> grandParentNode) {
        if (node.isLeftChild()) {
            rotateRight(parentNode);
        }

        parentNode.flipColor();
        grandParentNode.flipColor();
        rotateLeft(grandParentNode);
        recolorAndRotate(node.isLeftChild() ? grandParentNode : parentNode);
    }

    private void rotateRight(Node<T> node) {
        Node<T> leftNode = node.getLeftChild();
        node.setLeftChild(leftNode.getRightChild());
        if (node.getLeftChild() != null) {
            node.getLeftChild().setParent(node);
        }

        leftNode.setRightChild(node);
        leftNode.setParent(node.getParent());
        updateChildrenOfParentNode(node,leftNode);
        node.setParent(leftNode);
    }

    private void rotateLeft(Node<T> node) {
        Node<T> rightNode = node.getRightChild();
        node.setRightChild(rightNode.getLeftChild());
        if (node.getRightChild() != null) {
            node.getRightChild().setLeftChild(node);
        }
        rightNode.setLeftChild(node);
        rightNode.setParent(node.getParent());
        updateChildrenOfParentNode(node,rightNode);
        node.setParent(rightNode);
    }

    private void updateChildrenOfParentNode(Node<T> node, Node<T> tempNode) {
        if (node.getParent() == null) {
            root = tempNode;
        } else if (node.isLeftChild()) {
            node.getParent().setLeftChild(tempNode);
        } else {
            node.getParent().setRightChild(tempNode);
        }
    }
}
