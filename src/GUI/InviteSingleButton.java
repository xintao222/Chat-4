package GUI;/*
 * Created by krantz on 2014-04-17.
 */

import client.ClientModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InviteSingleButton extends JButton implements ActionListener {


    private ClientModel model;
    private final JList connected;
    private final JList groups;

    public InviteSingleButton(ClientModel model, JList connected, JList groups) {
        super("Invite client");
        this.model = model;
        this.connected = connected;
        this.groups = groups;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        Object[] selectedGroup = groups.getSelectedValues();
        ArrayList<String> group = new ArrayList<String>(selectedGroup.length);
        for (int i = 0; i < selectedGroup.length; i++) {
            group.add((String) selectedGroup[i]);
        }

        Object[] selectedInvite = connected.getSelectedValues();
        ArrayList<String> whoInv = new ArrayList<String>(selectedInvite.length);
        for (int i = 0; i < selectedInvite.length; i++) {
            whoInv.add((String) selectedInvite[i]);
        }

        model.sendSingleInvite(whoInv, group.get(0));

    }
}
