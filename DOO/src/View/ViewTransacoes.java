
package View;
import controller.TransacaoController;
import model.Transacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
public class ViewTransacoes extends javax.swing.JPanel {

    private final TransacaoController controller = new TransacaoController();
    private final DefaultTableModel modelo;
    private final JTable tabela;
    private final JComboBox<String> cbFiltro;   // Todos | Receitas | Despesas

    public ViewTransacoes() {
        setLayout(new BorderLayout());

        // ---- Tabela ------------------------------------------------------
        String[] colunas = {"ID", "Tipo", "Nome", "Descrição", "Valor", "Data"};
        modelo = new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tabela = new JTable(modelo);
        tabela.setAutoCreateRowSorter(true); // ordenação clicável

        add(new JScrollPane(tabela), BorderLayout.CENTER);

        // ---- Painel de topo (filtro) -------------------------------------
        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topo.add(new JLabel("Mostrar:"));
        cbFiltro = new JComboBox<>(new String[]{"Todas", "RECEITA", "DESPESA"});
        topo.add(cbFiltro);
        add(topo, BorderLayout.NORTH);

        cbFiltro.addActionListener(e -> carregarTabela());

        carregarTabela();
    }

    private void carregarTabela() {
        modelo.setRowCount(0);

        List<Transacao> dados = controller.listar();
        String filtro = (String) cbFiltro.getSelectedItem();
        if (!"Todas".equals(filtro)) {
            dados = dados.stream()
                         .filter(t -> t.getTipo().equals(filtro))
                         .collect(Collectors.toList());
        }

        for (Transacao t : dados) {
            modelo.addRow(new Object[]{
                    t.getId(),
                    t.getTipo(),
                    t.getNome(),
                    t.getDescricao(),
                    t.getValor(),
                    t.getData()
            });
        }
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
