package ArvoreRedBlack;

import java.util.LinkedList;
import java.util.Queue;

public class ARB <T extends Comparable<T>> {
    private ARBNode<T> root; //no raiz
    private ARBNode<T> nil; //no nil

    public ARB(){ //contrutor da arvore
        nil = new ARBNode<T>(); //cria um no nil
		root = nil; //quando a árvore for criada, a raiz vai ser nil
    }

    public boolean isEmpty(){ //checa se a raiz é nil, se for, a arvore esta vazia
        return (this.root == nil); 
    }

    public void insert(T value){
        ARBNode<T>newNode = new ARBNode<>(value); //cria novo nó com info T

        if(isEmpty()){//se a arvore estiver vazia
            this.root = newNode;
            //seta o pai da raiz como nil
            root.setPrevious(nil);
            //seta os filhos do primeiro nó inserido como nil
            root.setLeft(nil);
            root.setRight(nil);
            //seta a raiz preta(que sempre vai ser preta, por definição)
            root.setColor(0);
            System.out.println("Nó inserido! Primeira inserção feita.");
            return;
        }

        ARBNode<T> pai = nil;
        ARBNode<T> filho = root;

        while(filho != nil){ //percorre a arvore a partir da raiz buscando o ponto que o novo no vai ser inserido
            pai = filho;
            int compare = value.compareTo(filho.getInfo());
            if(compare == 0 && filho.getStatus()){ //se encontrar um nó ativo repetido, ele retorna e não adiciona
                System.out.println("Essa arvore nao aceita valores repetidos! ");
                return;
            }
            if(compare > 0){
                filho = filho.getRight();
            }else{
                filho = filho.getLeft();
            }
        }
        //aqui o ponto de inserção ja foi encontrado, filho aponta para nil(que vai ser o ponto de inserção) e pai para o seu pai
        //seta o pai do novo nó como pai
        newNode.setPrevious(pai);

        //verifica se o novo no vai ficar na direita ou esquerda do pai e seta ele
        if(value.compareTo(pai.getInfo()) < 0){
            pai.setLeft(newNode);
        }else{
            pai.setRight(newNode);
        }

        //ajusta os filhos do novo no como nil
        newNode.setLeft(nil);
        newNode.setRight(nil);

        //manda a arvore para verificar se é necessário fazer alterções
        corrigirArvore(newNode);
    }

    private void corrigirArvore(ARBNode<T> K) {
        
        while (K.getPrevious() != nil && K.getPrevious().getColor() == 1) { // Verificar se P não é nil e se é vermelho
            ARBNode<T> P = K.getPrevious();//define o pai
            ARBNode<T> G = P.getPrevious();//define o avô

            ARBNode<T> S; // cria o tio
    
            //define o tio
            if (G.getLeft() == P) { 
                S = G.getRight();
            } else {
                S = G.getLeft();
            }
    
            System.out.println("Corrigindo árvore para o nó: " + K.getInfo());
    
            if (S != nil && S.getColor() == 1) {//coloração
                //ajusta as cores de acordo com a regra
                S.setColor(0);
                P.setColor(0);
                if (G != root) {//se o avô for a raiz, ele vai continuar preto
                    G.setColor(1);
                }
                K = G; //atualiza o novo K para checar se vai ser necessário alterar alguma coisa após a coloração

            } else {//vai ser feita uma rotação         
                if (G.getLeft() == P) { //rotação vai ser à direita (simples ou dupla)
                    rotacaoDireita(K, P, G, S);
                } else {//rotação vai ser a esquerda (simples ou dupla)
                    rotacaoEsquerda(K, P, G, S);
                }
                return; //apos a rotação não tem necessidade de checar os nós acima
            }
        }
        root.setColor(0); //garantir que a raiz vai ser sempre preta
    }
    

    private void rotacaoDireita(ARBNode<T> K, ARBNode<T> P, ARBNode<T> G, ARBNode<T> S) {
        if (P.getLeft().equals(K)) {  //vai ser uma rotação simples pra direita
            if (G == root) {//se G for a raiz, ajustamos a raiz para P
                root = P;
            }
    
            ARBNode<T> antG = G.getPrevious(); 
            
            //ve se P estava a esquerda ou a direita do seu nó anterior e seta o filho (que era ocupado por G) desse nó como P
            if (antG != nil) {
                if (antG.getLeft().equals(G)) {
                    antG.setLeft(P);
                } else {
                    antG.setRight(P);
                }
            }
    
            P.setPrevious(antG);//seta o no anterior a P como o nó anterior a G

            ARBNode<T> TdirP = P.getRight(); //guarda a subárvore(se existir) da direita de P 
    
            //ajusta G como filho da direita de P e P como nó anterior a G
            P.setRight(G);
            G.setPrevious(P);
    
            //se P tiver subárvore, ela vai ficar a esquerda de G e guardar G como o valor anterior a ela
            if (TdirP != nil) {
                G.setLeft(TdirP);
                TdirP.setPrevious(G);
            } else {
                G.setLeft(nil);
            }
            //ajustando cores
            P.setColor(0);
            G.setColor(1);
    
        } else {  
            if (G == root) { //se G fosse raiz, ela vai ser ajustada pra K
                root = K;
            }
    
            ARBNode<T> antG = G.getPrevious(); 
    
            if (antG != nil) {//ajusta K como filho do nó anterior a G (no lugar que G ocupava)
                if (antG.getLeft().equals(G)) {
                    antG.setLeft(K);
                } else {
                    antG.setRight(K);
                }
            }

            
    
            K.setPrevious(antG); //ajusta o anterior de K como o que era anterior a G
    
            //guardando as subárvores de K (se existirem)
            ARBNode<T> TesqK = K.getLeft();
            ARBNode<T> TdirK = K.getRight();
    

            //checa se as subárvores  são diferentes de nil e às posiciona da forma correta
            if (TesqK != nil) {
                P.setRight(TesqK);
                TesqK.setPrevious(P);
            } else {
                P.setRight(nil);
            }
            if (TdirK != nil) {
                G.setLeft(TdirK);
                TdirK.setPrevious(G);
            } else {
                G.setLeft(nil);
            }
    
            //seta P e G como filhos de K e K como o nó anterior a eles
            K.setLeft(P);
            P.setPrevious(K);
            K.setRight(G);
            G.setPrevious(K);
    
            //ajusta as cores
            K.setColor(0);
            P.setColor(1);
            G.setColor(1);
        }
    }
    

    private void rotacaoEsquerda(ARBNode<T> K, ARBNode<T> P, ARBNode<T> G, ARBNode<T> S) {
        if (P.getRight().equals(K)) {  // Rotação simples à esquerda
            if (G == root) {//se G for raiz, vai ajustar ela para P
                root = P;
            }
    
            ARBNode<T> antG = G.getPrevious();
    
            if (antG != nil) {//se o antecessor de G não for nulo, vai setar P como filho da direita ou da esquerda
                if (antG.getLeft().equals(G)) {
                    antG.setLeft(P);
                } else {
                    antG.setRight(P);
                }
            }
    
            P.setPrevious(antG);//seta o anterior a G como pai de P
    
            ARBNode<T> TesqP = P.getLeft();//guarda a subarvore da esuqerda de P
    
            P.setLeft(G);//ajusta a esquerda de P
            G.setPrevious(P);//seta P como pai de G
    
            if (TesqP != nil) {//ajusta a subarvore de P (se existir)
                G.setRight(TesqP);
                TesqP.setPrevious(G);
            } else {
                G.setRight(nil);
            }
    
            //ajusta as cores
            P.setColor(0);
            G.setColor(1);
    
        } else {  // Rotação dupla à esquerda
            if (G == root) {//se G fosse a raiz, agora K vai ser raiz
                root = K;
            }
    
            ARBNode<T> antG = G.getPrevious();
    
            if (antG != nil) {//se o pai de G nao for nulo, vai setar ele como pai de K
                if (antG.getLeft().equals(G)) {
                    antG.setLeft(K);
                } else {
                    antG.setRight(K);
                }
            }
    
            K.setPrevious(antG);
    
            //guarda as subarvores de K
            ARBNode<T> TdirK = K.getRight();
            ARBNode<T> TesqK = K.getLeft();
    
            //ajusta as subarvores
            if (TdirK != nil) {
                P.setLeft(TdirK);
                TdirK.setPrevious(P);
            } else {
                P.setLeft(nil);
            }
    
            if (TesqK != nil) {
                G.setRight(TesqK);
                TesqK.setPrevious(G);
            } else {
                G.setRight(nil);
            }
    
            //faz a rotação dupla
            K.setLeft(G);
            G.setPrevious(K);
            K.setRight(P);
            P.setPrevious(K);
    
            //ajusta as cores
            K.setColor(0);
            P.setColor(1);
            G.setColor(1);
        }
    }
    
    
    public void passeioEmOrdem(){//função publica para ser chamada pela classe App para passeio em ordem
        if(this.isEmpty()){//se estiver vazia, não faz o passeio e retorna
            System.out.println("Arvore vazia!");
        }else{
            emOrdem(root);//se não estiver vazia chama o em ordem que vai realizar o passeio
        }
    }
    private void emOrdem(ARBNode<T> r){//passeio em ordem recursivo
        if(r != nil){
            emOrdem(r.getLeft());
            if(r.getStatus()){//se o nó estiver ativo, suas informações vão ser printadas
                if(r.getColor() == 0){//checa a cor do nó e printa
                    System.out.print("\u001B[30m" + "|" + "["+ r.getInfo()+"]" +"(preto)| "+"\u001B[0m");
                }else{
                    System.out.print("\u001B[31m" + "|" + "["+ r.getInfo()+"]" +"(Vermelho)| "+ "\u001B[0m");
                }
            }
            emOrdem(r.getRight());
        }
    }


    public void passeioPorNivel(){//função publica que vai chamar o passeio por nível
        if(this.isEmpty()){//se estiver vazia ele vai retornar para o main
            System.out.println("Arvore vazia!");
        }else{
            porNivel(root);
        }
    }
    private void porNivel(ARBNode<T> r){
        Queue<ARBNode<T>> fila = new LinkedList<>(); //fila para percorrer árvore por nível
        fila.add(r);//adiciona a raiz

        while(!fila.isEmpty()){
            r = fila.poll();
            if(r != nil){//se r nao for nil, vai printar as informações do nó
                if(r.getStatus()){//se o nó estiver ativo, suas informações vão ser printadas
                    if(r.getColor() == 0){//checa a cor do nó e printa
                        System.out.print("\u001B[30m" + "|" + "["+ r.getInfo()+"]" +"(preto)| "+"\u001B[0m");
                    }else{
                        System.out.print("\u001B[31m" + "|" + "["+ r.getInfo()+"]" +"(Vermelho)| "+ "\u001B[0m");
                    }
                }
                fila.add(r.getLeft());
                fila.add(r.getRight());
            }else{//se for nil vai printar null
                System.out.print("\u001B[30m" + "|" + "[nil]" +"(preto)| " +"\u001B[0m");
            }

        }
    }

    public void RemocaoPreguicosa(T value){
        if(isEmpty()){//se estiver vazia, retorna 
            System.out.println("Arvore vazia! ");
            return;
        }
        ARBNode<T>r = root;
        while(r != nil){ //percorre a árvore buscando o valor até r ser nil ou encontrar o nó
            int compare = value.compareTo(r.getInfo());
            if(compare == 0){//se encontrou sai do loo
                break;
            }else if(compare < 0){
                r = r.getLeft();
            }else{
                r = r.getRight();
            }
        }

        if(r == nil){//r chegou na folha e não encontrou o nó, então ele não esta na árvore
            System.out.println("O valor não está na árvore! ");
            return;
        }else{
            r.setStatus(false);//desativa o nó 
        }
    }
}
