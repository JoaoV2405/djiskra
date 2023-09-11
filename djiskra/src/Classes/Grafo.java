package Classes;

import java.util.*;

public class Grafo {
    int V; // Número de vértices (clientes)
    public List<Cliente> clientes; // Lista de clientes
    public List<List<Aresta>> adjacencias; // Lista de adjacências

    public Grafo(int V) {
        this.V = V;
        clientes = new ArrayList<>();
        adjacencias = new ArrayList<>(V);

        for (int i = 0; i < V; i++) {
            adjacencias.add(new ArrayList<>());
        }
    }
    public void adicionarCliente(String nome, String bairro){
        boolean clienteIgual=false;
        if(nome!=null && bairro!=null){
                for (Cliente cliente1 : clientes) {
                    if (cliente1.nome.equals(nome)||cliente1.bairro.equals(bairro)) {
                        clienteIgual = true;
                        break;
                    }
                }
                if (!clienteIgual) {
                    Cliente cliente = new Cliente(nome, bairro);
                    clientes.add(cliente);
                    V++;
                    adjacencias.add(new ArrayList<>(V));
                }else{
                    System.out.println("Não pode haver cliente ou bairro com o mesmo nome");
                }
        }
    }

    // Função para adicionar uma aresta (distância entre bairros) ao grafo
    public void adicionarAresta(int origem, int destino, int peso) {
        adjacencias.get(origem).add(new Aresta(destino, peso));
    }
    public void addArestaNo(String origem, String destino, int peso){
        int i;
        int j;
        boolean existe = false;
        boolean existe2= false;
        int ini;
        int fim;
        for (i = 0;i< clientes.size() &&!existe; i++) {
            Cliente cliente = clientes.get(i);
            if (cliente.nome.equals(origem)||cliente.bairro.equals(origem)) {
                existe=true;
            }
        }
        for (j = 0;j< clientes.size()&&!existe2; j++) {
            Cliente cliente = clientes.get(j);
            if(cliente.nome.equals(destino)||cliente.bairro.equals(destino)){
                existe2=true;
            }
        }
        if(existe && existe2){
            ini = i-1;
            fim = j-1;
            boolean existe3=true;
            if(adjacencias.get(ini).size()>0) {
                for (int k = 0; k < adjacencias.get(ini).size() && existe3; k++) {
                    if (adjacencias.get(ini).get(k).destino == fim) {
                        System.out.println("Rota já existente!");
                        break;
                    } else existe3 = false;
                }
            }else existe3 = false;
            if(!existe3){
                    System.out.println("Adição concluída!");
                adjacencias.get(ini).add(new Aresta(fim, peso));
            }
        }else System.out.println("Origem ou destino inválido");
    }
    public void printAdjacencias()
    {
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println("Adjacências do Cliente " + clientes.get(i).nome +" | Bairro: "+clientes.get(i).bairro+" ("+i+")");
            for (int j = 0; j < adjacencias.get(i).size(); j++) {
                System.out.print(j +"| Cliente: "+clientes.get(adjacencias.get(i).get(j).destino).nome+", Bairro: "+clientes.get(adjacencias.get(i).get(j).destino).bairro +", Distância: "+adjacencias.get(i).get(j).peso +"km"+" | ");
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
            if (cliente.bairro.equals(bairro) && cliente.nome.equals(nome)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public void removerCliente(){
        int i;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o nome do bairro: ");
        String bairro = scanner.nextLine();
        for (Cliente cliente : clientes) {
            removerAresta(cliente.bairro, bairro);
        }
        for ( i = 0; i < clientes.size(); i++){
            if (clientes.get(i).bairro.equals(bairro)) {
                clientes.remove(clientes.get(i));
                adjacencias.remove(i);
                System.out.println(clientes.size());
                break;
            }
        }
        for (int k = 0; k < clientes.size(); k ++){
            for (int j = 0; j <adjacencias.get(k).size(); j++){
                if(adjacencias.get(k).get(j).destino>i){
                    adjacencias.get(k).get(j).destino-=1;
                }
            }
        }
    }

    public void removerAresta(String origem, String destino) {
        int i;
        int j;
        boolean existe = false;
        boolean existe2= false;
        int ini;
        for (i = 0;i< clientes.size() &&!existe; i++) {
            Cliente cliente = clientes.get(i);
            if (cliente.nome.equals(origem)||cliente.bairro.equals(origem)) {
                existe=true;
            }
        }
        for (j = 0;j< clientes.size()&&!existe2; j++) {
            Cliente cliente = clientes.get(j);
            if(cliente.nome.equals(destino)||cliente.bairro.equals(destino)){
                existe2=true;
            }
        }
        if(existe && existe2) {
            ini = i - 1;
            for (int k = 0; k < adjacencias.get(ini).size(); k++) {
                if(clientes.get(adjacencias.get(ini).get(k).destino).nome.equals(destino)){
                    adjacencias.get(ini).remove(k);
                    System.out.println("Remoção concluída!");
                    break;
                }
            }
        }else System.out.println("Não existe aresta!");
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
        for (i = 0;i< clientes.size() &&!existe; i++){
            Cliente cliente = clientes.get(i);
            if (cliente.bairro.equals(primeiro)|| cliente.nome.equals(ultimo)) {
                existe=true;
            }
        }
        for (j = 0;j< clientes.size()&&!existe2; j++) {
            Cliente cliente = clientes.get(j);
            if(cliente.bairro.equals(ultimo)|| cliente.nome.equals(ultimo)){
                existe2=true;
            }
        }
        if(existe && existe2){
            origem = i-1;
            destino = j-1;
        }
        if(origem != -1){
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
            if(distancias[destino]!=2147483647) {
            imprimirRota(predecessores, destino);

                System.out.println("\nA distância total é: " + distancias[destino] + "km");
            }else System.out.println("Não há rota disponível");
        }else System.out.println("Não há origem ou destino com esse nome");
    }

    // Função para imprimir a rota a partir dos predecessores
    public void imprimirRota(int[] predecessores, int destino) {
        if (predecessores[destino] == -1) {
            System.out.print(clientes.get(destino));
            return;
        }

        imprimirRota(predecessores, predecessores[destino]);
        System.out.print( "-> " + clientes.get(destino));
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo(3);
        grafo.adicionarCliente("a", "1");
        grafo.adicionarCliente("b", "2");
        grafo.adicionarCliente("c", "3");
        grafo.addArestaNo("a", "b",1);
//        grafo.adicionarAresta(0,1,1);
        grafo.adicionarAresta(1,2,5);
        grafo.adicionarAresta(1,0,9);
        grafo.buscarCliente();
        grafo.dijkstra("a","b");
        grafo.printAdjacencias();
    }
}
