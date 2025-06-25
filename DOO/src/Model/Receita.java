package model;

public class Receita {
    private int id;
    private String nome;
    private String descricao;
    private double valor;
    private String data; // formato yyyy-MM-dd

    public Receita() {}

    public Receita(int id, String nome, String descricao, double valor, String data) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
