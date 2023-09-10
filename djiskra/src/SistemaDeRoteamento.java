import Classes.Cliente;
import Classes.Grafo;

import java.util.Scanner;

public class SistemaDeRoteamento {
    public static void main(String[] args) {
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

        int E;
        System.out.print("Digite o número de distâncias (arestas): ");
        E = scanner.nextInt();

        for (int i = 0; i < E; i++) {
            int origem, destino, peso;
            System.out.print("Digite a origem da distância " + (i + 1) + ": ");
            origem = scanner.nextInt();
            System.out.print("Digite o destino da distância " + (i + 1) + ": ");
            destino = scanner.nextInt();
            System.out.print("Digite a distância entre os bairros: ");
            peso = scanner.nextInt();
            grafo.adicionarAresta(origem, destino, peso);
        }

        System.out.print("Digite o nome do nó de origem: ");
        scanner.nextLine(); // Consumir a nova linha
        String origemNome = scanner.nextLine();
        System.out.print("Digite o nome do nó de destino: ");
        String destinoNome = scanner.nextLine();

        int origem = -1, destino = -1;
        int i;
        int j;
        for (i = 0; i < V; i++) {
            if (grafo.clientes.get(i).bairro.equals(origemNome)) {
                origem = i;
            }
        }
        for (j = 0; j < V; j++) {
            if (grafo.clientes.get(i).bairro.equals(destinoNome)) {
                destino = j;
            }
        }

        if (origem != -1 && destino != -1) {
            System.out.println("O caminho mais curto de "+grafo.clientes.get(i).bairro +" para "+ grafo.clientes.get(i).bairro+" é:");
//            grafo.dijkstra(origem, destino);
        } else {
            System.out.println("Origem ou destino não encontrados.");
        }

        scanner.close();
    }
}