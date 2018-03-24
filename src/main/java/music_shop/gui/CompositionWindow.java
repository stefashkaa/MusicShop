package music_shop.gui;

import music_shop.entities.Composition;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompositionWindow extends JFrame {

    private CompositionTableModel model;
    private AddWindow dialog;
    private int currentChoiceRow;
    private JTable table;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton selectButton;
    private JButton backButton;

    public CompositionWindow(CompositionTableModel compositionModel){

        super();
        model = compositionModel;
        dialog = new AddWindow(this);

        this.setTitle("Compositions");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        table = new JTable();
        table.setModel(model);
        setLayout(null);
        JScrollPane sc = new JScrollPane(table);
        sc.setBounds(10, 60, 450, 100);
        add(sc);

        addButton = new JButton("Add");
        add(addButton);
        addButton.setBounds(10, 10, 145, 30);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setTitle("Add composition");
                if (dialog.initDialog() == 1) {
                    long id = model.getNextId();
                    String name = dialog.getCompositionName();
                    int length = dialog.getCompositionLength();
                    long albumId = dialog.getAlbId();
                    Composition c = new Composition(id, name, length, albumId);
                    model.addNewRow(c);
                    table.revalidate();
                    table.repaint();
                }
            }
        });

        editButton = new JButton("Edit");
        add(editButton);
        editButton.setBounds(160, 10, 145, 30);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentChoiceRow = table.getSelectedRow();
                dialog.setTitle("Edit composition");
                dialog.setCompositionName(model.getValueAt(currentChoiceRow, 1).toString());
                dialog.setCompositionLength(model.getValueAt(currentChoiceRow, 2).toString());
                dialog.setAlbId(model.getValueAt(currentChoiceRow, 3).toString());
                if (dialog.IsEditDialog() == 1) {
                    Composition old = model.all().get(currentChoiceRow);
                    long newId = old.getId();
                    String newName = dialog.getCompositionName();
                    int newLength = dialog.getCompositionLength();
                    long newAlbumId = dialog.getAlbId();
                    Composition newComposition = new Composition(newId, newName, newLength, newAlbumId);
                    model.updateRow(currentChoiceRow, old, newComposition);
                    table.revalidate();
                    table.repaint();
                }
            }
        });

        deleteButton = new JButton("Remove");
        add(deleteButton);
        deleteButton.setBounds(310, 10, 145, 30);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    currentChoiceRow = table.getSelectedRow();
                    model.removeRow(currentChoiceRow);
                    table.revalidate();
                    table.repaint();
                } catch (IndexOutOfBoundsException ex) {
                    ex.printStackTrace();
                }
            }
        });

        selectButton = new JButton("Sorted");
        add(selectButton);
        selectButton.setBounds(10, 180, 220, 30);

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setAll(model.getSortedList());
                table.revalidate();
                table.repaint();
            }
        });

        backButton = new JButton("All");
        add(backButton);
        backButton.setBounds(240, 180, 220, 30);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setAll(model.all());
                table.revalidate();
                table.repaint();
            }
        });

        setVisible(true);
        setSize(485,265);
    }

}