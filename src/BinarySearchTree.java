import java.util.Comparator;

public class BinarySearchTree <T extends Comparable<T>> implements Tree<T>{

    private Node<T> root;

    @Override
    public Tree<T> insert(T data) {
        if (isEmpty()) {
            root = new Node<>(data);
        } else {
            insert(data, root);
        }
        return this;
    }

    public void insert(T data, Node<T> node) {
        if (data.compareTo(node.getData()) < 0 ) {
            if (node.getLeftChild() == null) {
                Node<T> newNode = new Node<>(data);
                node.setLeftChild(newNode);
            } else {
                insert(data, node.getLeftChild());
            }
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRightChild() == null) {
                Node<T> newNode = new Node<>(data);
                node.setRightChild(newNode);
            } else {
                insert(data, node.getRightChild());
            }
        }
    }

    //////////////////////////////////////////////////////
    // ANOTHER WAY OF INSERTING THE DATA

    public Tree<T> insert2 (T data) {
        root = insert2(data, root);
        return this;
    }

    public Node<T> insert2(T data, Node<T> node) {
        if (node == null) {
            return new Node<>(data);
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(insert2(data, node.getLeftChild()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(insert2(data, node.getRightChild()));
        }
        return node;
    }

    ///////////////////////////////////////////////////////////////

    @Override
    public void delete(T data) {
        root = delete(data, root);
    }

    public Node<T> delete(T data, Node<T> node) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild( delete(data,node.getLeftChild()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(delete(data,node.getRightChild()));
        } else {
            //One child or a leaf node
            if (node.getLeftChild() == null) {
                return (node.getRightChild());
            } else if (node.getRightChild() == null) {
                return (node.getLeftChild());
            }

            //If the parent has two children
            node.setData(getMax(node.getLeftChild()));
            node.setLeftChild(delete(node.getData(),node.getLeftChild()));
        }
        return node;
    }

    @Override
    public void traverse() {
        inOrderTraverse(root);
    }

    //In order traverse
    public void inOrderTraverse(Node<T> node) {
        if (node != null) {
            //As long as the node has a left child, the method gonna be recalled till it's a leaf node so its printed
            inOrderTraverse(node.getLeftChild());
            System.out.println(node);
            inOrderTraverse(node.getRightChild());
        }
    }

    @Override
    public T getMin() {
        if (isEmpty()) {
            return null;
        }

        Node<T> node = root;
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node.getData();
    }

    @Override
    public T getMax() {
        if (isEmpty()) {
            return null;
        }

        Node<T> node = root;
        while (node.getRightChild() != null) {
            node = node.getRightChild();
        }
        return node.getData();
    }

    public T getMax(Node<T> node) {
        if (node.getRightChild() != null) {
            return getMax(node.getRightChild());
        }
        return node.getData();
    }

    public T getMin(Node<T> node) {
        if (node.getLeftChild() != null) {
            return getMin(node.getLeftChild());
        }
        return node.getData();
    }


    //NEED SOME WORK, ITS PRINTING THE WHOLE BINARY SEARCH TREE AGAIN AND NOT THE NODE WE ARE LOOKING FOR!!!
//    public Node<T> getMatchingNode(T data, Node<T> node) {
//        if (data.compareTo(node.getData()) < 0) {
//            getMatchingNode(data, node.getLeftChild());
//        } else if (data.compareTo(node.getData()) > 0) {
//            getMatchingNode(data, node.getRightChild());
//        } else {
//            return node;
//        }
//        return node;
//    }
//
//    public Node<T> getMainNode() {
//        return root;
//    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    public String toString(){
        return root+" ";
    }
}
