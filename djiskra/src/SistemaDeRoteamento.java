import Classes.Cliente;
import Classes.Grafo;

import java.util.Scanner;

public class SistemaDeRoteamento {
    public static void main(String[] args) {
        int origem, destino;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o número de clientes (vértices): ");
        int V = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Grafo grafo = new Grafo(V);

        for (int i = 0; i < V; i++) {
            System.out.print("Digite o nome do cliente " + (i + 1) + ": ");
            String nome = scanner.nextLine();
            System.out.print("Digite o bairro do cliente " + (i + 1) + ": ");
            String bairro = scanner.nextLine();
            grafo.clientes.add(new Cliente(nome, bairro));
        }



        int decisao = 10;
        while (true){
            String o, des;
            int dis;
            System.out.println(" Digite:\n\n(1) Adcionar 1 Cliente\n(2) Remover 1 Cliente\n(3) Adicionar 1 rua\n(4) Interditar uma Rua\n(5) Calcular Fota\n(0) Encerrar o sistema ");
                decisao = scanner.nextInt();

            switch (decisao){
                case 1:
                    System.out.print("Digite o nome do cliente: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o bairro do cliente: ");
                    String bairro = scanner.nextLine();
                    grafo.adicionarCliente(nome, bairro);
                break;

                case 2:
                    grafo.removerCliente();
                    break;

                case 3:
                    System.out.println("Digite o nome da origem: ");
                    o = scanner.nextLine();
                    System.out.println("Digite o nome do destino: ");
                    des = scanner.nextLine();
                    System.out.println("Digite a distância: ");
                    dis = scanner.nextInt();
                    grafo.addArestaNo(o, des, dis);
                    //adicionar aresta
                    break;

                case 4:
                    System.out.println("Digite o nome da origem: ");
                    origem = scanner.nextInt();
                    System.out.println("Digite o nome do destino: ");
                    destino = scanner.nextInt();
                    grafo.removerAresta(origem, destino);
                    //remover aresta
                    break;

                case 5:
                    //rodar o djisktra
                    System.out.println("Digite o ponto de partida: ");
                    o = scanner.nextLine();
                    System.out.println("Digite o ponto de chegada: ");
                    des = scanner.nextLine();
                    grafo.dijkstra(o, des);
                    break;

                case 0:
                    System.out.println("Encerrando o sistema");
                    return;
                    //encerrrar o sistema

                default:
                    System.out.println("Digite um Valor válido!!");
            }
        }
    }
}