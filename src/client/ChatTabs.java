package client;

import com.jidesoft.dialog.JideOptionPane;
import com.jidesoft.swing.JideTabbedPane;

import javax.swing.*;
import java.awt.*;
//import client.CloseTabListener;

@SuppressWarnings("serial")
public class ChatTabs extends JideTabbedPane {
    ClientModel clientModel;
    private TabHandler handler;

    public ChatTabs(ClientModel clientModel) {
        this.clientModel = clientModel;
        setShowCloseButtonOnTab(true);
        setShowCloseButtonOnSelectedTab(true);

    }

    @Override
    public void addTab(String title, Component component) {
        JScrollPane scroll = new JScrollPane(component);
        scroll.setBounds(17, 19, 490, 219);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        super.addTab(title, scroll);

    }


    public void changeCurrent(String chatTo) {
        for (int i = 0; i < getComponentCount(); i++) {
            if (getComponent(i) instanceof JScrollPane) {
                JScrollPane p = (JScrollPane) getComponent(i);
                JViewport viewport = p.getViewport();
                HistoryArea current = (HistoryArea) viewport.getView();
                if (current.getName().equals(chatTo)) {
                    setSelectedComponent(p);
                }
            }
        }

//        System.out.println("Selected ChatTabs: " + getSelectedComponent().getClass());
    }

    @Override
    public void removeTabAt(int index) {
        //        int index = getSelectedIndex();
        if (index >= 0) {
            JScrollPane p = (JScrollPane) getSelectedComponent();
            JViewport viewport = p.getViewport();
            HistoryArea current = (HistoryArea) viewport.getView();
            if (current.getName().contains("Group: ")) {
                int closeIndex = getSelectedIndex();
                String title = getTitleAt(closeIndex);
                if (JideOptionPane.showConfirmDialog(null, "You are about to leave: " + title, "Lave Group", JOptionPane.YES_NO_OPTION) == JideOptionPane.YES_OPTION) {
                    clientModel.leaveGroup(title);
                    super.removeTabAt(closeIndex);
                }

            } else if (!current.getName().equals("Forever alone")) {
                handler.remove(current.getName());
                super.removeTabAt(index);
            }


        }

//        super.removeTabAt(index);

    }

    public void setHandler(TabHandler handler) {
        this.handler = handler;
    }
}
