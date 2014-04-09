package GUI;

import client.ClientModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

@SuppressWarnings("serial")
public class ChatClientGui extends JFrame {

    public ChatClientGui() {

    }

    public ClientModel init() {
        setResizable(false);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CardLayout layout = new CardLayout();
        LoginPanel loginPanel = new LoginPanel();
        JPanel cardPanel = new JPanel(layout); // Might not need, depends on menus, => use the frame instead
        cardPanel.setLayout(layout);
        cardPanel.add(loginPanel, "1");
        getContentPane().add(cardPanel);
        cardPanel.setPreferredSize(new Dimension(400, 500));
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
        MainWindow mainGui = new MainWindow(model);
        cardPanel.add(mainGui, "2");

        layout.next(cardPanel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        cardPanel.setPreferredSize(new Dimension(555, 390));
        getContentPane().setPreferredSize(new Dimension(695, 325));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                try {
                    model.exterminate();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
        pack();
        return model;

    }
}
