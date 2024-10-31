import java.util.Scanner;
import ArvoreRedBlack.*;
    public class App {
    public static void main(String[] args) throws Exception {
        int op = -1; //inicializa op com -1
        ARB<Integer> arvoreRedBlack = new ARB<Integer>();//cria árvore de Integer
        Scanner input = new Scanner(System.in);
        System.out.println("-=-=-ARVORE " + "\u001B[31m" + "RED" + "\u001B[30m"+"BLACK"+"\u001B[0m" + "=-=-");//colorir o nome REDBLACK
        do{ 
            System.out.println("\n=-=-=-=-=-MENU=-=-=-=-=-");
            System.out.println("1: Inserir valor");
            System.out.println("2: Passeio em ordem");
            System.out.println("3: Passeio por nível");
            System.out.println("4: Remover valor");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-");

            System.out.println("Qual opção deseja? ");
            op = input.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Informe o valor que deseja inserir: ");
                    int value = input.nextInt();
                    arvoreRedBlack.insert(value);
                    break;
                case 2: 
                    arvoreRedBlack.passeioEmOrdem();
                    break;
                case 3:
                    arvoreRedBlack.passeioPorNivel();
                    break;
                    
                case 4:
                    System.out.println("Informe o valor que deseja remover: ");
                    int remove = input.nextInt();
                    arvoreRedBlack.RemocaoPreguicosa(remove);
                default:
                    break;
            }
        }while(op != 0);
        System.out.println("Fechando programa... ");
        input.close();
    }
}
