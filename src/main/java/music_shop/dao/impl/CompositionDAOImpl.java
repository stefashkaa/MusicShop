package music_shop.dao.impl;

import music_shop.dao.CompositionDAO;
import music_shop.dao.DAO;
import music_shop.entities.Composition;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CompositionDAOImpl implements CompositionDAO {
    private DAO dao;

    public CompositionDAOImpl(DAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Composition> selectCompositions() {
        List<Composition> compositions = new LinkedList<>();
        String query = "SELECT * FROM COMPOSITION";
        selectCompositions(compositions, query);
        return compositions;
    }

    @Override
    public void addComposition(Composition c) {
        String query = "INSERT INTO COMPOSITION VALUES(?,?,?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, c.getId());
            preparedStatement.setString(2, c.getName());
            preparedStatement.setInt(3, c.getLength());
            preparedStatement.setLong(4,c.getAlbumId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteComposition(Long id) {
        String query = "DELETE FROM COMPOSITION WHERE ID=?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateComposition(Composition composition1, Composition composition2) {
        String query ="UPDATE COMPOSITION SET NAME=?, LENGTH=?, ALBUM_ID=? WHERE ID=?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, composition2.getName());
            preparedStatement.setLong(2, composition2.getLength());
            preparedStatement.setLong(3, composition2.getAlbumId());
            preparedStatement.setLong(4, composition1.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Composition> selectSortedCompositions() {
        List<Composition> all = new LinkedList<Composition>();
        String query = "SELECT ID, NAME, LENGTH, ALBUM_ID FROM COMPOSITION WHERE LENGTH NOT BETWEEN 5 AND 10 ORDER BY LENGTH DESC";
        selectCompositions(all, query);
        return all;
    }

    @Override
    public String getAllColumnNames() {
        StringBuilder message = new StringBuilder("Column names from table 'Composition':");
        String query = "SELECT * FROM COMPOSITION";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData meta = resultSet.getMetaData();
            int count = meta.getColumnCount();
            for(int i = 0; i < count; i++){
                message.append("\n").append(meta.getColumnLabel(i + 1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return message.toString();
    }

    @Override
    public String getAllTableNames() {
        StringBuilder message = new StringBuilder("All tables from 'music_shop' database:");
        try (Connection connection = dao.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, null, new String[]{"TABLE"});
            while (resultSet.next()) {
                message.append("\n").append(resultSet.getString(3));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return message.toString();
    }

    private void selectCompositions(List<Composition> all, String query) {
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                long id = resultSet.getInt(CompositionDAO.ID);
                String name = resultSet.getString(CompositionDAO.NAME);
                int length = resultSet.getInt(CompositionDAO.LENGTH);
                long albumId = resultSet.getInt(CompositionDAO.ALBUM_ID);
                Composition c = new Composition(id, name, length, albumId);
                all.add(c);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
