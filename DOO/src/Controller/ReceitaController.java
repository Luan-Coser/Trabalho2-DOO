package controller;

import dao.ReceitaDAO;
import model.Receita;

import java.util.List;

public class ReceitaController {
    private final ReceitaDAO dao = new ReceitaDAO();

    public List<Receita> listarReceitas() {
        return dao.listar();
    }

    public boolean adicionarReceita(Receita r) {
        if (r.getNome() == null || r.getNome().trim().isEmpty()) return false;
        if (r.getValor() <= 0) return false;
        return dao.inserir(r);
    }

    public boolean atualizarReceita(Receita r) {
        if (r.getId() <= 0) return false;
        return dao.atualizar(r);
    }

    public boolean removerReceita(int id) {
        if (id <= 0) return false;
        return dao.remover(id);
    }
}
