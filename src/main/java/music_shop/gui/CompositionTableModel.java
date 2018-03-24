package music_shop.gui;

import music_shop.dao.DAO;
import music_shop.dao.impl.CompositionDAOImpl;
import music_shop.entities.Composition;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CompositionTableModel extends DefaultTableModel {

    private CompositionDAOImpl dao;
    private List<Composition> all;

    public CompositionTableModel(DAO dao){
        super();
        this.dao = new CompositionDAOImpl(dao);
        all = this.dao.selectCompositions();
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:return "ID";
            case 1:return "Name";
            case 2:return "Length";
            case 3:return "Album ID";
        }
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return super.isCellEditable(row, column);
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        if(all!=null){
            return all.size();
        }
        return super.getRowCount();
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column){
            case 0:return all.get(row).getId();
            case 1:return all.get(row).getName();
            case 2:return all.get(row).getLength();
            case 3:return all.get(row).getAlbumId();
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public void removeRow(int row) {
        Composition removed = all.get(row);
        all.remove(removed);
        dao.deleteComposition(removed.getId());
    }

    public void addNewRow(Composition a){
        all.add(a);
        dao.addComposition(a);
    }

    public void updateRow(int row,Composition a, Composition c){

        Composition update = all.get(row);
        all.remove(update);
        all.add(c);
        dao.updateComposition(a, c);
    }

    public long getNextId(){
        long max  = 0;
        for(Composition a:all){
            long val = a.getId();
            if(val>max){
                max=val;
            }
        }
        return max+1;
    }

    public void setAll(List<Composition> data){
        this.all = data;
    }

    public List<Composition> getSortedList(){
        return dao.selectSortedCompositions();
    }

    public List<Composition> all(){
        return dao.selectCompositions();
    }
}