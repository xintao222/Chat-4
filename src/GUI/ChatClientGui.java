package GUI;

import client.ClientModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

@SuppressWarnings("serial")
public class ChatClientGui extends JFrame {
    private CardLayoutHandler cardHandler;
    public ChatClientGui() {

    }

    /* Creates a login window GUI, when the user clicked connect it reads the input and returns a clientModel with that information.*/
    public ClientModel init() {
        setResizable(false);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CardLayout layout = new CardLayout();
        cardHandler = new CardLayoutHandler(layout);
        LoginPanel loginPanel = cardHandler.getLoginPanel();
        cardHandler.setLayout(layout);
        cardHandler.add(loginPanel, "1");
        cardHandler.setPreferredSize(new Dimension(400, 500));
        getContentPane().add(cardHandler);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        while (!loginPanel.isFinished()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int port = loginPanel.getPort();
        String ip = loginPanel.getIp();
        String loginName = loginPanel.getLoginName();


        final ClientModel model = new ClientModel(loginName, ip, port);

        MainWindow mainGui = new MainWindow(model, this);
        cardHandler.add(mainGui, "2");

        cardHandler.nextCard();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        cardHandler.setPreferredSize(new Dimension(555, 390));
        getContentPane().setPreferredSize(new Dimension(695, 325));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                try {
                    model.leaveAllGroups();

                    model.exterminate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
        pack();
        return model;

    }

    public CardLayoutHandler getLayoutHandler() {
        return cardHandler;
    }
}
