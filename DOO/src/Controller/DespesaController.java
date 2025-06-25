package controller;

import dao.DespesaDAO;
import model.Despesa;

import java.util.List;

public class DespesaController {
    private final DespesaDAO dao = new DespesaDAO();

    public List<Despesa> listarDespesas() {
        return dao.listar();
    }

    public boolean adicionarDespesa(Despesa d) {
        // Validação simples (exemplo)
        if (d.getNome() == null || d.getNome().trim().isEmpty()) return false;
        if (d.getValor() <= 0) return false;
        return dao.inserir(d);
    }

    public boolean atualizarDespesa(Despesa d) {
        if (d.getId() <= 0) return false;
        return dao.atualizar(d);
    }

    public boolean removerDespesa(int id) {
        if (id <= 0) return false;
        return dao.remover(id);
    }
}
