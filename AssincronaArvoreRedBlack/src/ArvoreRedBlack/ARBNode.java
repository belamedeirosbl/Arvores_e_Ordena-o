package ArvoreRedBlack;

public class ARBNode<T extends Comparable<T>> {
    private ARBNode<T> previous; //pai
    private ARBNode<T> left; //no da esquerda
    private ARBNode<T> right; //no da direita
    private T info;
    private int color;  //preto 0 e vermelho 1
    private boolean status;  //booleano para indicar se nó esta ativo ou inativo

    public ARBNode() {//Construtor do no nil
        this.color = 0; //por definição, sempre vai ser preto
        this.status = true; 
        this.left = this; //aponta para si mesmo
        this.right = this; 
        this.previous = this; 
    }

    public ARBNode(T info) {
        this.info = info;
        this.left = null;  //inicializando como nulo
        this.right = null;
        this.previous = null;
        this.color = 1;  //por definição, todo nó adicionado vai ser vermelho
        this.status = true;  
    }

    //gets e sets

    public int getColor() {
        return color;
    }

    public void setColor(int cor) {
        this.color = cor;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public ARBNode<T> getLeft() {
        return left;
    }

    public void setLeft(ARBNode<T> left) {
        this.left = left;
    }

    public ARBNode<T> getRight() {
        return right;
    }

    public void setRight(ARBNode<T> right) {
        this.right = right;
    }

    public ARBNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(ARBNode<T> pai) {
        this.previous = pai;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
