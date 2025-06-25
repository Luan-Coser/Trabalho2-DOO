package controller;

import dao.TransacaoDAO;
import model.Transacao;
import java.util.List;

public class TransacaoController {
    private final TransacaoDAO dao = new TransacaoDAO();
    public List<Transacao> listar() {
        return dao.listar();
    }
}
