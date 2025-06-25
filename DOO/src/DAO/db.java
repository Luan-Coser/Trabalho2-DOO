package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
    private static final String URL = "jdbc:sqlite:financas.db";

    /**
     * Retorna uma conexão com o banco SQLite.
     */
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}