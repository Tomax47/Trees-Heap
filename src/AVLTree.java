public class AVLTree <T extends Comparable<T>> implements Tree<T>{

    private Node<T> root;
    @Override
    public Tree<T> insert(T data) {
        root = insert(data,root);
        return this;
    }

    private Node<T> insert(T data, Node<T> node) {
        if (node == null) {
            return new Node<>(data);
        }

        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(data,node.getLeftChild()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(insert(data,node.getRightChild()));
        } else {
            return node;
        }
        updateHeight(node);
        return applyRotation(node);
    }

    @Override
    public void delete(T data) {
        root = delete(data, root);
    }

    private Node<T> delete(T data, Node<T> node) {
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(delete(data, node.getLeftChild()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(delete(data, node.getRightChild()));
        } else {
            //One child or leaf node
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                return node.getLeftChild();
            }

            //Two children
            node.setData(getMax(node.getLeftChild()));
            node.setLeftChild(delete(node.getData(),node.getLeftChild()));
        }
        updateHeight(node);
        return applyRotation(node);
    }

    @Override
    public void traverse() {inOrderTraverse(root);}
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

    @Override
    public boolean isEmpty() {
        return false;
    }

    private Node<T> applyRotation(Node<T> node) {
        int balance  = balance(node);

        if (balance > 1) {
            //Left-Heavy situation
            if (balance(node.getLeftChild()) < 0) {
                //Left-Right case
                node.setLeftChild(rotateLeft(node.getLeftChild()));
            }
            return rotateRight(node);
        }

        if (balance < -1) {
            //Right-Heavy situation
            if (balance(node.getRightChild()) > 0) {
                //Right-left case
                node.setRightChild(rotateRight(node.getRightChild()));
            }
            return rotateLeft(node);
        }
        return node;
    }

    private void updateHeight(Node<T> node) {
        int maxHeight = Math.max(height(node.getLeftChild()), height(node.getRightChild()));
        node.setHeight(maxHeight + 1);
    }

    private int height(Node<T> node) {
        return node != null ? node.getHeight() : 0;
    }

    private int balance(Node<T> node) {
        return node != null ? height(node.getLeftChild())- height(node.getRightChild()) : 0;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> leftNode = node.getLeftChild();
        Node<T> centerNode = leftNode.getRightChild();

        leftNode.setRightChild(node);
        node.setLeftChild(centerNode);
        updateHeight(node);
        updateHeight(leftNode);

        return leftNode;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> rightNode = node.getRightChild();
        Node<T> centerNode = rightNode.getLeftChild();

        rightNode.setLeftChild(node);
        node.setRightChild(centerNode);
        updateHeight(node);
        updateHeight(rightNode);

        return rightNode;
    }
}
