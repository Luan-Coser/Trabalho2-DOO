package model;

public class Transacao {
    private int    id;
    private String nome;
    private String descricao;
    private double valor;
    private String data;   // yyyy-MM-dd
    private String tipo;   // "RECEITA" ou "DESPESA"

    public Transacao(int id, String nome, String descricao,
                     double valor, String data, String tipo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
    }

    /* getters */
    public int    getId()        { return id; }
    public String getNome()      { return nome; }
    public String getDescricao() { return descricao; }
    public double getValor()     { return valor; }
    public String getData()      { return data; }
    public String getTipo()      { return tipo; }
}
