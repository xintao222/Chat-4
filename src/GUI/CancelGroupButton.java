package GUI;/*
 * Created by krantz on 2014-04-10.
 */

import GUI.CardLayoutHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelGroupButton extends JButton implements ActionListener{

    private CardLayoutHandler cardHandler;

    public CancelGroupButton(CardLayoutHandler cardHandler){
        super("Cancel");
        this.cardHandler = cardHandler;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
                cardHandler.showMainWindow();

    }
}
