package view;

import model.Receita;

import javax.swing.*;
import java.awt.*;

public class ReceitaForm extends JPanel {

    private final JTextField txtNome = new JTextField(20);
    private final JTextField txtDescricao = new JTextField(20);
    private final JTextField txtValor = new JTextField(10);
    private final JTextField txtData = new JTextField(10); // yyyy-MM-dd

    public ReceitaForm(Receita receita) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,4,4,4);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Descrição:"), gbc);
        gbc.gridx = 1;
        add(txtDescricao, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Valor:"), gbc);
        gbc.gridx = 1;
        add(txtValor, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Data (yyyy-MM-dd):"), gbc);
        gbc.gridx = 1;
        add(txtData, gbc);

        if (receita != null) {
            txtNome.setText(receita.getNome());
            txtDescricao.setText(receita.getDescricao());
            txtValor.setText(String.valueOf(receita.getValor()));
            txtData.setText(receita.getData());
        }
    }

    public Receita getReceita() {
        try {
            String nome = txtNome.getText().trim();
            String descricao = txtDescricao.getText().trim();
            double valor = Double.parseDouble(txtValor.getText().trim());
            String data = txtData.getText().trim();

            if (nome.isEmpty() || data.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome e Data são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            return new Receita(0, nome, descricao, valor, data);

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
