package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by krantz on 2014-04-10.
 */
public class CardLayoutHandler extends JPanel {
    private CardLayout layout;
    private LoginPanel loginPanel;

    public CardLayoutHandler(CardLayout layout) {
        super(layout);
        this.layout = layout;

        loginPanel = new LoginPanel();
        setLayout(layout);
        add(loginPanel, "1");
        setPreferredSize(new Dimension(400, 500));

    }

    public void nextCard() {
        layout.next(this);

    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }
}
