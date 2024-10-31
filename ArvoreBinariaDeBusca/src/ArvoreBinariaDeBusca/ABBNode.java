package ArvoreBinariaDeBusca;
public class ABBNode <T extends Comparable<T>> {
    private ABBNode<T> left;
    private ABBNode<T> right;
    private T info;

    public ABBNode(T value){
        this.info = value;
        this.right = null;
        this.left = null;
    }

    public T getInfo() {
        return info;
    }
    public void setInfo(T info){
        this.info = info;
    }
    public ABBNode<T> getLeft(){
        return left;
    }
    public void setLeft(ABBNode<T> left) {
        this.left = left;
    }
    public ABBNode<T> getRight() {
        return right;
    }
    public void setRight(ABBNode<T> right){
        this.right = right;
    }

}
