package music_shop;

import music_shop.dao.DAO;
import music_shop.dao.impl.DAOPostgre;
import music_shop.gui.CompositionTableModel;
import music_shop.gui.CompositionWindow;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class Main{

    public static void main(String[] args) {
        final DAO dao = new DAOPostgre();
        dao.setURL(DAOPostgre.DEFAULT_HOST, DAOPostgre.DEFAULT_DATABASE, DAOPostgre.DEFAULT_PORT);
        dao.connect(DAOPostgre.DEFAULT_LOGIN, DAOPostgre.DEFAULT_PASSWORD);

        CompositionTableModel tableModel = new CompositionTableModel(dao);
        CompositionWindow windowPerformer = new CompositionWindow(tableModel);

        windowPerformer.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    dao.getConnection().close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
                System.exit(0);
            }
        });
    }
}
