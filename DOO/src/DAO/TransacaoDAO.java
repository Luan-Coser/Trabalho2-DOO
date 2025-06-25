package dao;

import DAO.db;
import model.Transacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {

    /** Retorna todas as transações (já vem da VIEW SQL). */
    public List<Transacao> listar() {
        List<Transacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacao ORDER BY data DESC";

        try (Connection c = db.conectar();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Transacao(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        rs.getString("data"),
                        rs.getString("tipo")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
