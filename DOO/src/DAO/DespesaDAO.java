package dao;

import DAO.db;
import model.Despesa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DespesaDAO {

    public List<Despesa> listar() {
        List<Despesa> lista = new ArrayList<>();
        String sql = "SELECT * FROM despesa ORDER BY data DESC";

        try (Connection conn = db.conectar();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Despesa d = new Despesa(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getDouble("valor"),
                    rs.getString("data")
                );
                lista.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean inserir(Despesa despesa) {
        String sql = "INSERT INTO despesa (nome, descricao, valor, data) VALUES (?, ?, ?, ?)";

        try (Connection conn = db.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, despesa.getNome());
            pst.setString(2, despesa.getDescricao());
            pst.setDouble(3, despesa.getValor());
            pst.setString(4, despesa.getData());

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Despesa despesa) {
        String sql = "UPDATE despesa SET nome=?, descricao=?, valor=?, data=? WHERE id=?";

        try (Connection conn = db.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, despesa.getNome());
            pst.setString(2, despesa.getDescricao());
            pst.setDouble(3, despesa.getValor());
            pst.setString(4, despesa.getData());
            pst.setInt(5, despesa.getId());

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean remover(int id) {
        String sql = "DELETE FROM despesa WHERE id=?";

        try (Connection conn = db.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
