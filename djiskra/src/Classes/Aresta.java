package Classes;

class Aresta {
    int destino;
    int peso;

    public Aresta(int destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "{ Destino:" + destino +
                ", Distância: " + peso +
                '}';
    }
}