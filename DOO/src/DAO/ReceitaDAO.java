package dao;

import DAO.db;
import model.Receita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO {

    public List<Receita> listar() {
        List<Receita> lista = new ArrayList<>();
        String sql = "SELECT * FROM receita ORDER BY data DESC";

        try (Connection conn = db.conectar();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Receita r = new Receita(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getDouble("valor"),
                    rs.getString("data")
                );
                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean inserir(Receita receita) {
        String sql = "INSERT INTO receita (nome, descricao, valor, data) VALUES (?, ?, ?, ?)";

        try (Connection conn = db.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, receita.getNome());
            pst.setString(2, receita.getDescricao());
            pst.setDouble(3, receita.getValor());
            pst.setString(4, receita.getData());

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Receita receita) {
        String sql = "UPDATE receita SET nome=?, descricao=?, valor=?, data=? WHERE id=?";

        try (Connection conn = db.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, receita.getNome());
            pst.setString(2, receita.getDescricao());
            pst.setDouble(3, receita.getValor());
            pst.setString(4, receita.getData());
            pst.setInt(5, receita.getId());

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean remover(int id) {
        String sql = "DELETE FROM receita WHERE id=?";

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
