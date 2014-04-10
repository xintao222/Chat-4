package GUI;/*
 * Created by krantz on 2014-04-11.
 */

import client.ClientModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddToGroupButton extends JButton implements ActionListener {


    private ClientModel model;
    private JList list;

    public AddToGroupButton(ClientModel model, JList list) {
        super("Invite");
        this.model = model;
        this.list = list;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object[] selected =  list.getSelectedValues();
        ArrayList<String> group = new ArrayList<String>(selected.length);
        for (int i = 0; i < selected.length; i++) {
            group.add((String) selected[i]);
        }
        model.sendInvite(group);
    }
}
