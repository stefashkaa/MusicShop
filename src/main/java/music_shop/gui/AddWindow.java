package music_shop.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWindow extends JDialog {

    private int status = 0;
    private JLabel nameLabel1;
    private JTextField nameFild1;
    private JLabel nameLabel2;
    private JTextField nameFild2;
    private JLabel nameLabel3;
    private JTextField nameFild3;
    private JButton ok;
    private JButton cancel;

    public AddWindow(JFrame parentFrame){
        super(parentFrame, true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setModal(true);

        nameLabel1 = new JLabel("Enter composition name:");
        add(nameLabel1);
        nameLabel1.setBounds(10, 10, 190, 30);


        nameFild1 = new JTextField();
        add(nameFild1);
        nameFild1.setBounds(200, 13, 120, 20);

        nameLabel2 = new JLabel("Enter composition length:");
        add(nameLabel2);
        nameLabel2.setBounds(10, 50, 190, 30);


        nameFild3 = new JTextField();
        add(nameFild3);
        nameFild3.setBounds(200, 53, 120, 20);

        nameLabel3 = new JLabel("Enter album id:");
        add(nameLabel3);
        nameLabel3.setBounds(10, 90, 190, 30);


        nameFild2 = new JTextField();
        add(nameFild2);
        nameFild2.setBounds(200, 93, 120, 20);

        ok = new JButton("OK");
        add(ok);
        ok.setBounds(165, 130, 150, 20);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                status = 1;
                setVisible(false);
            }
        });

        cancel = new JButton("Cancel");
        add(cancel);
        cancel.setBounds(10, 130, 150, 20);

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                status = 0;
                setVisible(false);
            }
        });
        setSize(340, 200);
        setVisible(false);
    }

    public int initDialog(){
        nameFild1.setText("");
        nameFild2.setText("");
        nameFild3.setText("");
        setVisible(true);
        return status;
    }
    public  int IsEditDialog(){
        setVisible(true);
        return status;
    }
    public String getCompositionName(){
        return nameFild1.getText();
    }

    public void setCompositionName(String name){
        this.nameFild1.setText(name);
    }
    public int getCompositionLength(){
        return Integer.parseInt(nameFild3.getText());
    }

    public void setCompositionLength(String len){
        this.nameFild3.setText(len);
    }
    public int getAlbId(){
        return Integer.parseInt(nameFild2.getText());
    }

    public void setAlbId(String id){
        this.nameFild2.setText(id);
    }

}