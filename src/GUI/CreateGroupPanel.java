package GUI;

/*
 * Created by krantz on 2014-04-10.
 */

import client.ClientModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreateGroupPanel extends JPanel {


    private CardLayoutHandler layoutHandler;
    private ClientModel model;

    public CreateGroupPanel(CardLayoutHandler layoutHandler, ClientModel model) {
        this.layoutHandler = layoutHandler;
        this.model = model;
        GridLayout layout = new GridLayout(2, 3);
        setLayout(layout);


        ArrayList<String> clients = model.getConnectedClients();
        JList list = new JList(clients.toArray());
        add(list);
        CancelGroupButton cancelButton = new CancelGroupButton(layoutHandler);
        AddToGroupButton addGroupButton = new AddToGroupButton(model, list);
        add(addGroupButton);
        add(cancelButton);
    }


}
