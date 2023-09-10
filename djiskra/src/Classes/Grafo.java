package Classes;

import java.util.*;

public class Grafo {
    int V; // Número de vértices (clientes)
    public List<Cliente> clientes; // Lista de clientes
    List<List<Aresta>> adjacencias; // Lista de adjacências

    public Grafo(int V) {
        this.V = V;
        clientes = new ArrayList<>();
        adjacencias = new ArrayList<>(V);

        for (int i = 0; i < V; i++) {
            adjacencias.add(new ArrayList<>());
        }
    }
    public void adicionarCliente(String nome, String bairro){
        if(nome!=null && bairro!=null){
            Cliente cliente = new Cliente(nome, bairro);
            clientes.add(cliente);
        }
    }

    // Função para adicionar uma aresta (distância entre bairros) ao grafo
    public void adicionarAresta(int origem, int destino, int peso) {
        adjacencias.get(origem).add(new Aresta(destino, peso));
    }
    public void addArestaNo(Cliente origem, Cliente destino, int peso){
        int ini = clientes.indexOf(origem);
        int fim = clientes.indexOf(destino);
        adjacencias.get(ini).add(new Aresta(fim, peso));
    }
    void printAdjacencias()
    {
        for (int i = 0; i < adjacencias.size(); i++) {
            System.out.println("Adjacências do Cliente " + clientes.get(i).nome +" | Bairro: "+clientes.get(i).bairro+" ("+i+")");
            for (int j = 0; j < adjacencias.get(i).size(); j++) {
                System.out.print("| Cliente: "+clientes.get(adjacencias.get(i).get(j).destino).nome+", Bairro: "+clientes.get(adjacencias.get(i).get(j).destino).bairro +", Distância: "+adjacencias.get(i).get(j).destino +"km"+" | ");
            }
            System.out.println();
        }
    }
    public boolean buscarCliente(){
        boolean existe = false;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o nome do bairro: ");
        String bairro = scanner.nextLine();
        for (Cliente cliente: clientes) {
            if(cliente.bairro.equals(bairro)&& cliente.nome.equals(nome)){
                System.out.println("sexo");
                existe= true;
            }
        }
        return existe;
    }
    public void removerAresta(int origem, int destino) {
        for (int i = 0; i < adjacencias.get(origem).size(); i++) {
            if (adjacencias.get(origem).get(i).destino == destino) {
                adjacencias.get(origem).remove(i);
                return;
            }
        }
        System.out.println("Aresta não encontrada. Nenhuma remoção realizada.");
    }

    // Função para encontrar a rota mais curta usando o algoritmo de Dijkstra
    public void dijkstra(String primeiro, String ultimo) {
        int[] distancias = new int[V];
        int[] predecessores = new int[V];
        int origem = -1;
        int destino= -1;
        int i;
        int j;
        boolean existe = false;
        boolean existe2= false;
        for (i = 0;i< clientes.size() &&!existe; i++) {
            Cliente cliente = clientes.get(i);
            if (cliente.nome.equals(primeiro)) {
                existe=true;
            }
        }
        for (j = 0;j< clientes.size()&&!existe2; j++) {
            Cliente cliente = clientes.get(j);
            if(cliente.nome.equals(ultimo)){
                existe2=true;
            }
        }
        if(existe && existe2){
            origem = i-1;
            destino = j-1;
        }
        if(origem != -1){
            System.out.println(origem );
            System.out.println(destino);
            Arrays.fill(distancias, Integer.MAX_VALUE);
            Arrays.fill(predecessores, -1);
            distancias[origem] = 0;
            PriorityQueue<Integer> filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(a -> distancias[a]));
            filaPrioridade.add(origem);

            while (!filaPrioridade.isEmpty()) {
                int u = filaPrioridade.poll();

                for (Aresta aresta : adjacencias.get(u)) {
                    int v = aresta.destino;
                    int peso = aresta.peso;

                    if (distancias[u] + peso < distancias[v]) {
                        distancias[v] = distancias[u] + peso;
                        predecessores[v] = u;
                        filaPrioridade.add(v);
                    }
                }
            }

            imprimirRota(predecessores, destino);
            System.out.println("\nA distância total é: " + distancias[destino]);
        }else System.out.println("Não há origem ou destino come esse nome");
    }

    // Função para imprimir a rota a partir dos predecessores
    public void imprimirRota(int[] predecessores, int destino) {
        if (predecessores[destino] == -1) {
            System.out.print(destino + "->");
            return;
        }

        imprimirRota(predecessores, predecessores[destino]);
        System.out.print(destino + "->");
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo(3);
        grafo.adicionarCliente("a", "1");
        grafo.adicionarCliente("b", "2");
        grafo.adicionarCliente("c", "3");
        grafo.adicionarAresta(0,1,1);
        grafo.adicionarAresta(1,2,5);
        grafo.adicionarAresta(1,0,9);
//        grafo.buscarCliente();
//        grafo.dijkstra("a","b");
        grafo.printAdjacencias();
    }
}