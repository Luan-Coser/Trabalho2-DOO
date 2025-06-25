package DAO;

import java.sql.Connection;
import java.sql.Statement;

public class dbInit {

    public static void criarTabelas() {
        try (Connection conn = db.conectar();
             Statement stmt = conn.createStatement()) {

            // Tabela de Receitas
            String sqlReceita = """
                CREATE TABLE IF NOT EXISTS receita (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    descricao TEXT,
                    valor REAL NOT NULL,
                    data TEXT NOT NULL
                );
            """;

            // Tabela de Despesas
            String sqlDespesa = """
                CREATE TABLE IF NOT EXISTS despesa (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    descricao TEXT,
                    valor REAL NOT NULL,
                    data TEXT NOT NULL
                );
            """;

            // View de transações (opcional)
            String sqlView = """
                CREATE VIEW IF NOT EXISTS transacao AS
                SELECT id, nome, descricao, valor, data, 'RECEITA' AS tipo FROM receita
                UNION ALL
                SELECT id, nome, descricao, valor, data, 'DESPESA' AS tipo FROM despesa;
            """;

            stmt.execute(sqlReceita);
            stmt.execute(sqlDespesa);
            stmt.execute(sqlView);

            System.out.println("Tabelas criadas com sucesso.");

        } catch (Exception e) {
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}