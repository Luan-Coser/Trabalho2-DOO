package view;

import model.Despesa;

import javax.swing.*;
import java.awt.*;

public class DespesaForm extends JPanel {

    private final JTextField txtNome = new JTextField(20);
    private final JTextField txtDescricao = new JTextField(20);
    private final JTextField txtValor = new JTextField(10);
    private final JTextField txtData = new JTextField(10); // yyyy-MM-dd

    public DespesaForm(Despesa despesa) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,4,4,4);
        gbc.anchor = GridBagConstraints.WEST;

        // Linha 1
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        add(txtNome, gbc);

        // Linha 2
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Descrição:"), gbc);
        gbc.gridx = 1;
        add(txtDescricao, gbc);

        // Linha 3
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Valor:"), gbc);
        gbc.gridx = 1;
        add(txtValor, gbc);

        // Linha 4
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Data (yyyy-MM-dd):"), gbc);
        gbc.gridx = 1;
        add(txtData, gbc);

        if (despesa != null) {
            txtNome.setText(despesa.getNome());
            txtDescricao.setText(despesa.getDescricao());
            txtValor.setText(String.valueOf(despesa.getValor()));
            txtData.setText(despesa.getData());
        }
    }

    public Despesa getDespesa() {
        try {
            String nome = txtNome.getText().trim();
            String descricao = txtDescricao.getText().trim();
            double valor = Double.parseDouble(txtValor.getText().trim());
            String data = txtData.getText().trim();

            if (nome.isEmpty() || data.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome e Data são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            return new Despesa(0, nome, descricao, valor, data);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void setEditable(boolean editable) {
        txtNome.setEditable(editable);
        txtDescricao.setEditable(editable);
        txtValor.setEditable(editable);
        txtData.setEditable(editable);
    }
}
