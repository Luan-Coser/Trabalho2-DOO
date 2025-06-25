package doo;

import DAO.dbInit;
import View.ViewMenu;
import javax.swing.SwingUtilities;

public class DOO {

    public static void main(String[] args) {
        // 1. Criar banco e tabelas
        dbInit.criarTabelas();

        // 2. Iniciar interface gráfica (na EDT)
        SwingUtilities.invokeLater(() -> {
            ViewMenu menu = new ViewMenu();
            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
        });
    }
}
