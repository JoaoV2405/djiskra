package Classes;

public class Cliente {
    String nome;
    public String bairro;

    public Cliente(String nome, String bairro) {
        this.nome = nome;
        this.bairro = bairro;
    }

    @Override
    public String toString() {
        return "Bairro: "+bairro;
    }
}