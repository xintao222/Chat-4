package client;/*
 * Created by krantz on 2014-04-17.
 */

import GUI.CardLayoutHandler;
import GUI.CreateGroupSingleInvitePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InviteToGroupListener implements ActionListener {
    private final ClientModel clientModel;
    private final ChatTabs chatTabs;
    private CardLayoutHandler layoutHandler;

    public InviteToGroupListener(ClientModel clientModel, ChatTabs chatTabs, CardLayoutHandler layoutHandler) {
        this.clientModel = clientModel;
        this.chatTabs = chatTabs;
        this.layoutHandler = layoutHandler;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int tabCount = chatTabs.getTabCount();
        ArrayList<String> options = new ArrayList<String>();


        for (int i = 0; i < tabCount; i++) {
            if (chatTabs.getTitleAt(i).contains("Group: ")) {
                options.add(chatTabs.getTitleAt(i));
            }
        }
        CreateGroupSingleInvitePanel groupPanel = new CreateGroupSingleInvitePanel(layoutHandler,clientModel, options);
        layoutHandler.add(groupPanel, "4");

        layoutHandler.nextCard(); //TODO Fixing this, doesn't always work.
        layoutHandler.nextCard();

    }
}
