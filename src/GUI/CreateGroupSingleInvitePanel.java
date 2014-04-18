package GUI;/*
 * Created by krantz on 2014-04-17.
 */

import client.ClientModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreateGroupSingleInvitePanel extends JPanel {


    public CreateGroupSingleInvitePanel(CardLayoutHandler layoutHandler, ClientModel model, ArrayList<String> nameOptions) {
        GridLayout layout = new GridLayout(2, 3);
        setLayout(layout);


        JList groups = new JList(nameOptions.toArray());
        JList connected = new JList(model.getConnectedClients().toArray());
        add(groups);
        add(connected);
        CancelGroupButton cancelButton = new CancelGroupButton(layoutHandler);
        InviteSingleButton inviteSingle = new InviteSingleButton(model, connected, groups);
        add(inviteSingle);
        add(cancelButton);
    }
}
