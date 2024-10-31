package ArvoreBinariaDeBusca;
import java.util.LinkedList;
import java.util.Queue;
public class ABB<T extends Comparable <T>>{
    ABBNode<T> root;

    public ABB(){
        this.root = null;
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public boolean isFull(){
        return false;
    }

    public void inserir(T value){
        ABBNode<T> novo = new ABBNode<T>(value); 
        if(this.isEmpty()){
            this.root = novo;
        }else{            
            ABBNode<T> filho = this.root;
            ABBNode<T> pai = null;
            while(filho != null){
                pai = filho;
                int comparar = value.compareTo(filho.getInfo());
                if(comparar == 0){
                    System.out.println("Não é permitido valores iguais! ");
                    return;
                }
                else if(comparar > 0){
                    filho = filho.getRight();
                }else{
                    filho = filho.getLeft();
                }

            }
            
            if(pai.getInfo().compareTo(value) > 0){
                pai.setLeft(novo);
            }else{
                pai.setRight(novo);
            }
        }
    }


    public void passeio(int op){
        if(isEmpty()){
            System.out.println("Arvore vazia! ");
            return;
        }else{
            switch (op) {
                case 1:
                    passeioEmOrdem(root);
                    break;
                case 2: 
                    passeioPreOrdem(root);
                    break;
                case 3:
                    passeioPosOrdem(root);
                    break;
                case 4:
                    passeioPorNível();
                    break;
            }
        }
    }   

    private void passeioEmOrdem(ABBNode<T> node){
        if(node != null){
            passeioEmOrdem(node.getLeft());
            System.out.println(node.getInfo());
            passeioEmOrdem(node.getRight());
        }    
    }
    private void passeioPreOrdem(ABBNode<T> node){
        if(node != null){
            System.out.println(node.getInfo());
            passeioEmOrdem(node.getLeft());
            passeioEmOrdem(node.getRight());
        }    
    }
    private void passeioPosOrdem(ABBNode<T> node){
        if(node != null){
            passeioEmOrdem(node.getLeft());
            passeioEmOrdem(node.getRight());
            System.out.println(node.getInfo());
        }    
    }

    private void passeioPorNível(){
        ABBNode<T>r =  this.root;
        if(isEmpty()){
            System.out.println("Arvore vazia");
            return;
        }else{
            Queue<ABBNode<T>> fila = new LinkedList<>();
            fila.add(r);
            while(!fila.isEmpty()){
                ABBNode<T> noAtual = fila.poll();

                System.out.println(noAtual.getInfo());
                if(noAtual.getLeft() != null){
                    fila.add(noAtual.getLeft());
                }
                if(noAtual.getRight() != null){
                    fila.add(noAtual.getRight());
                }
            }
        }
    }

    public void verificarNo(T value){
        ABBNode<T> busca = encontrarNo(value);
        if(busca == null){
            System.out.println("O nó não esta na arvore!");
        }else{
            System.out.println("O nó esta na arvore!");       
        }
    }

    public ABBNode<T> encontrarNo(T value){
        if(this.isEmpty()){
            return null;
        }
        ABBNode<T> aux = this.root;
        while(aux != null){
            int compare = value.compareTo(aux.getInfo());
            if(compare == 0){
                return aux;
            }else if(compare > 0){
                aux = aux.getRight();
            }else{
                aux = aux.getLeft();
            }
        }
        return null;
    }

    public void remove(T value){
        if(isEmpty()){
            System.out.println("Árvore vazia!");
            return;
        }else{
            ABBNode<T> node = encontrarNo(value);
            if(node == null){
                System.out.println("O valor não está na árvore! ");
                return;
            }
        }
        this.root = removeNode(this.root, value);
        
    }

    private ABBNode<T> removeNode(ABBNode<T> node, T value){
        if(node == null){
           return null; 
        }
        int compare = value.compareTo(node.getInfo());
        if(compare == 0){
            if(node.getLeft() == null && node.getRight() == null){
                return null;
            }else if(node.getLeft() == null){
                return node.getRight();
            }else if(node.getRight() == null){
                return node.getLeft();
            }else{
                ABBNode<T> pai = node, filho = node.getLeft();
                while(filho.getRight() != null){
                    pai = filho;
                    filho = filho. getRight();
                }

                if(pai == node){
                    node.setLeft(filho.getLeft());
                }else{
                    pai.setRight(filho.getLeft());
                }
            }

        }else if(compare > 0){
            node.setRight(removeNode(node.getRight(), value));
        }else{
            node.setLeft(removeNode(node.getLeft(), value));
        }
        return node;
    }   
}
//a e b mesmo sinal rotação simples, A vai ser o primeiro desbalancado e b vai ser o prox no da subarvore de maior altura
//na remocao avl tem que mudar o no removio com 2 filhos para recursivo para ele descer checando os fat
//fat é reajustado no retorno da recursao, por isso o código deve ser recursivo.
//precisa tambem ir ajustando a recursão no compare > e < que 0. precisa também ajudar as recursões
//se a > 0 rot a esq, se a < 0, a dir
//se o fat de B dor zero a rotação é simples
//se remocao a diretia do no muda fat -1, se  a esqurd fat+1
//se ta mais alta do lado direto, o b vai ser no lado diret=ito e viceversa.
//na remoção focar no ajuste do fat dos nós
//na rotação é preciso alterar: são os ajste do fat, as rotações em si permanecem a mesma
//definindo padrao para cada remoção que acontece
//na remocao a recursividade precisa voltar até a raiz, os dados, no caso não srrão setados para false(como na insersão)
//quando encontar um -2 ou +2 o A foi identificado
//o fat vai ser ajustado acima do A até a raiz
//AVL TREE tem site que monta árvore