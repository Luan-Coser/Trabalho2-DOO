
package View;

import controller.DespesaController;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.Despesa;
import view.DespesaForm;

public class ViewDespesas extends javax.swing.JPanel {
private final DespesaController controller = new DespesaController();
    private final JTable tabela;
    private final DefaultTableModel modelo;

    public ViewDespesas() {
        setLayout(new BorderLayout());

        // Colunas da tabela
        String[] colunas = {"ID", "Nome", "Descrição", "Valor", "Data"};
        modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tabela só leitura
            }
        };
        tabela = new JTable(modelo);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);

        // Painel dos botões
        JPanel painelBotoes = new JPanel();

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnRemover = new JButton("Remover");
        JButton btnEditar = new JButton("Editar");
        JButton btnVer = new JButton("Ver");

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnVer);

        add(painelBotoes, BorderLayout.SOUTH);

        carregarTabela();

        // Ações dos botões
        btnAdicionar.addActionListener(e -> abrirDialogo(null));
        btnRemover.addActionListener(e -> removerSelecionado());
        btnEditar.addActionListener(e -> editarSelecionado());
        btnVer.addActionListener(e -> verSelecionado());
    }

    private void carregarTabela() {
        List<Despesa> lista = controller.listarDespesas();
        modelo.setRowCount(0);
        for (Despesa d : lista) {
            modelo.addRow(new Object[]{
                    d.getId(),
                    d.getNome(),
                    d.getDescricao(),
                    d.getValor(),
                    d.getData()
            });
        }
    }

    private Despesa pegarDespesaSelecionada() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma despesa na tabela.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        int id = (int) modelo.getValueAt(linha, 0);
        String nome = (String) modelo.getValueAt(linha, 1);
        String descricao = (String) modelo.getValueAt(linha, 2);
        double valor = (double) modelo.getValueAt(linha, 3);
        String data = (String) modelo.getValueAt(linha, 4);

        return new Despesa(id, nome, descricao, valor, data);
    }

    private void abrirDialogo(Despesa despesa) {
        // Usar um dialog modal para adicionar ou editar
        // Se despesa == null, é adição; senão edição/visualização
        DespesaForm form = new DespesaForm(despesa);
        int resultado = JOptionPane.showConfirmDialog(this, form, 
                despesa == null ? "Adicionar Despesa" : "Editar Despesa", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultado == JOptionPane.OK_OPTION) {
            Despesa d = form.getDespesa();
            if (d != null) {
                boolean sucesso;
                if (despesa == null) {
                    sucesso = controller.adicionarDespesa(d);
                    if (sucesso) {
                        JOptionPane.showMessageDialog(this, "Despesa adicionada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao adicionar despesa.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    d.setId(despesa.getId()); // mantém id
                    sucesso = controller.atualizarDespesa(d);
                    if (sucesso) {
                        JOptionPane.showMessageDialog(this, "Despesa atualizada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao atualizar despesa.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                carregarTabela();
            }
        }
    }

    private void removerSelecionado() {
        Despesa selecionada = pegarDespesaSelecionada();
        if (selecionada == null) return;

        int confirmar = JOptionPane.showConfirmDialog(this,
                "Deseja realmente remover a despesa '" + selecionada.getNome() + "'?",
                "Confirmar remoção", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            boolean sucesso = controller.removerDespesa(selecionada.getId());
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Despesa removida.");
                carregarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao remover despesa.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarSelecionado() {
        Despesa selecionada = pegarDespesaSelecionada();
        if (selecionada == null) return;
        abrirDialogo(selecionada);
    }

    private void verSelecionado() {
        Despesa selecionada = pegarDespesaSelecionada();
        if (selecionada == null) return;

        DespesaForm form = new DespesaForm(selecionada);
        form.setEditable(false);

        JOptionPane.showMessageDialog(this, form, "Detalhes da Despesa", JOptionPane.PLAIN_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 366, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
