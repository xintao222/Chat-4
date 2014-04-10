package client;

import GUI.CardLayoutHandler;
import GUI.CreateGroupPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * Created by krantz on 2014-04-09.
 */
public class GroupChatHandler implements ActionListener {

    private ArrayList<String> inGroupChat;
    private ClientModel clientModel;
    private CardLayoutHandler layoutHandler;

    public GroupChatHandler(ClientModel clientModel, CardLayoutHandler layoutHandler) {
        this.clientModel = clientModel;
        this.layoutHandler = layoutHandler;
        inGroupChat = new ArrayList<String>();

    }


    /* Called when user clicked on new group */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        CreateGroupPanel groupPanel = new CreateGroupPanel(layoutHandler,clientModel);
        layoutHandler.add(groupPanel, "3");
        layoutHandler.nextCard();
    }
}