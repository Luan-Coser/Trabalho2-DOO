
package View;

import controller.ReceitaController;
import model.Receita;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import view.ReceitaForm;
public class ViewReceitas extends javax.swing.JPanel {

    private final ReceitaController controller = new ReceitaController();
    private final JTable tabela;
    private final DefaultTableModel modelo;

    public ViewReceitas() {
        setLayout(new BorderLayout());

        String[] colunas = {"ID", "Nome", "Descrição", "Valor", "Data"};
        modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela = new JTable(modelo);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel botoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnRemover = new JButton("Remover");
        JButton btnEditar = new JButton("Editar");
        JButton btnVer = new JButton("Ver");

        botoes.add(btnAdicionar);
        botoes.add(btnRemover);
        botoes.add(btnEditar);
        botoes.add(btnVer);
        add(botoes, BorderLayout.SOUTH);

        carregarTabela();

        // Ações
        btnAdicionar.addActionListener(e -> abrirFormulario(null));
        btnEditar.addActionListener(e -> {
            Receita r = getReceitaSelecionada();
            if (r != null) abrirFormulario(r);
        });
        btnRemover.addActionListener(e -> removerReceita());
        btnVer.addActionListener(e -> verReceita());
    }

    private void carregarTabela() {
        modelo.setRowCount(0);
        List<Receita> lista = controller.listarReceitas();
        for (Receita r : lista) {
            modelo.addRow(new Object[]{
                r.getId(), r.getNome(), r.getDescricao(), r.getValor(), r.getData()
            });
        }
    }

    private Receita getReceitaSelecionada() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma receita.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return new Receita(
            (int) modelo.getValueAt(linha, 0),
            (String) modelo.getValueAt(linha, 1),
            (String) modelo.getValueAt(linha, 2),
            (double) modelo.getValueAt(linha, 3),
            (String) modelo.getValueAt(linha, 4)
        );
    }

    private void abrirFormulario(Receita receitaOriginal) {
        ReceitaForm form = new ReceitaForm(receitaOriginal);
        int res = JOptionPane.showConfirmDialog(this, form,
                receitaOriginal == null ? "Nova Receita" : "Editar Receita",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (res == JOptionPane.OK_OPTION) {
            Receita nova = form.getReceita();
            if (nova != null) {
                boolean sucesso;
                if (receitaOriginal == null) {
                    sucesso = controller.adicionarReceita(nova);
                } else {
                    nova.setId(receitaOriginal.getId());
                    sucesso = controller.atualizarReceita(nova);
                }

                if (sucesso) {
                    carregarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar receita.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void removerReceita() {
        Receita selecionada = getReceitaSelecionada();
        if (selecionada == null) return;

        int confirmar = JOptionPane.showConfirmDialog(this,
                "Deseja remover a receita '" + selecionada.getNome() + "'?",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            if (controller.removerReceita(selecionada.getId())) {
                carregarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao remover.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void verReceita() {
        Receita r = getReceitaSelecionada();
        if (r == null) return;
        ReceitaForm form = new ReceitaForm(r);
        form.setEditable(false);
        JOptionPane.showMessageDialog(this, form, "Detalhes da Receita", JOptionPane.PLAIN_MESSAGE);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
