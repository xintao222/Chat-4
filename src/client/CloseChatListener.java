package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by krantz on 2014-04-09.
 */
public class CloseChatListener implements ActionListener {
    ClientModel model;

    public CloseChatListener(ClientModel model) {
        this.model = model;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        // TODO Leave groups before terminating

        try {
            model.exterminate();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
