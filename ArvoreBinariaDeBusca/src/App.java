import java.util.Scanner;
import ArvoreBinariaDeBusca.ABB;

public class App {

    public static void main(String[] args) throws Exception {
        int op;
        ABB<Integer> arvore = new ABB<Integer>();
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("0: Sair");
            System.out.println("1: Adicionar valor");
            System.out.println("2: Passeio na árvore");
            System.out.println("3: Verificar se um nó esta na árvore");
            System.out.println("4: Remover nó");
            System.out.println("Escolha uma opção:");
            op = input.nextInt();
            input.nextLine();
            switch (op) {
                case 1:
                    System.out.println("Informe o valor que deseja inserir: ");
                    Integer value = input.nextInt();
                    arvore.inserir(value);
                    break;
                    
                case 2:
                    System.out.println("Qual passeio deseja?");
                    System.out.println("1- Em Ordem\n2- Pré ordem\n3- Pós ordem\n4- Por nível\n");
                    int opcao = input.nextInt();
                    arvore.passeio(opcao);
                    break;
                    
                case 3:
                    System.out.println("Informe o valor que deseja verificar se esta na árvore: ");
                    Integer verificar = input.nextInt();
                    arvore.verificarNo(verificar);
                    break;
                    
                case 4:
                    System.out.println("Informe o valor que deseja remover da árvore: ");
                    Integer remover = input.nextInt();
                    arvore.remove(remover);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (op != 0);
        System.out.println("Fechando programa...");
        input.close();
    }

}
