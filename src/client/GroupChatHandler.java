package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by krantz on 2014-04-09.
 */
public class GroupChatHandler implements ActionListener{

    private ArrayList<String> inGroupChat;
    private ClientModel clientModel;

    public GroupChatHandler(ClientModel clientModel){
        this.clientModel = clientModel;
        inGroupChat = new ArrayList<String>();

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {



//        clientModel.sendMessage(new RequestMessage());
    }
}