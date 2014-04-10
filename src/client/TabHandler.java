package client;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class TabHandler implements Observer, ChangeListener, ActionListener {
    HashMap<String, HistoryArea> tabs;
    private ClientModel clientModel;
    private ChatTabs chatTab;
    private JList list;

    @SuppressWarnings("static-access")
    public TabHandler(ClientModel clientModel, ChatTabs chatTab, JList list) {
        this.clientModel = clientModel;
        this.chatTab = chatTab;
        this.list = list;
        clientModel.addObserver(this);
        tabs = new HashMap<String, HistoryArea>();
        createHistoryArea(clientModel.mainChatName);
        chatTab.addChangeListener(this);
        //sdd
    }

    @SuppressWarnings("static-access")
    @Override
    public void update(Observable arg0, Object arg1) {
        String chatWith = clientModel.chatsWith();
        String rec = clientModel.received();

        if (!rec.equals("null")) {
            if (tabs.containsKey(rec)) {
                String history = clientModel.getHistory(rec);
                tabs.get(rec).setText(history);
            } else {
                HistoryArea area = createHistoryArea(rec);
                area.setText(clientModel.getHistory(rec));

            }
        } else {
            if (tabs.containsKey(chatWith)) {
                String history = clientModel.getHistory(chatWith);
                tabs.get(chatWith).setText(history);
            } else {
                HistoryArea area = createHistoryArea(chatWith);
                area.setText(clientModel.getHistory(chatWith));
            }
        }

        ArrayList<String> clients = clientModel.getConnectedClients();
        System.out.println("Com count: " + chatTab.getComponentCount());
        for (int i = 0; i < chatTab.getComponentCount(); i++) {
            JScrollPane p = (JScrollPane) chatTab.getComponent(i);
            JViewport viewport = p.getViewport();
            HistoryArea current = (HistoryArea) viewport.getView();

            String name = current.getName();
            if (!clients.contains(name) && !name.equals("Forever alone")) {
                chatTab.remove(i);
            }

        }
    }

    private HistoryArea createHistoryArea(String chatWith) {
        HistoryArea ha = new HistoryArea(chatWith);
        ha.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        ha.setEditable(false);
        ha.setBounds(6, 6, 542, 257);
        tabs.put(chatWith, ha);
        chatTab.addTab(chatWith, ha);
        int length = chatTab.getComponentCount();
        for (int j = 0; j < length; j++) {
            JScrollPane p = (JScrollPane) chatTab.getComponentAt(j);
            JViewport viewport = p.getViewport();
            HistoryArea current = (HistoryArea) viewport.getView();
            if (current.equals(ha)) {
                chatTab.setSelectedIndex(j);
            }
        }
        return ha;

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JScrollPane p = (JScrollPane) chatTab.getSelectedComponent();
        JViewport viewport = p.getViewport();
        HistoryArea current = (HistoryArea) viewport.getView();

        // System.out.println("Changes tab to: " + current.getName());

        clientModel.setChatWith(current.getName());
        if (current.getName().equals("Forever alone")) {
            list.clearSelection();
        } else {
            list.setSelectedValue(current.getName(), true);

        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (tabs.size() > 1) {
            int index = chatTab.getSelectedIndex();
            if (index >= 0) {
                JScrollPane p = (JScrollPane) chatTab.getSelectedComponent();
                JViewport viewport = p.getViewport();
                HistoryArea current = (HistoryArea) viewport.getView();
                if (!current.getName().equals("Forever alone")) {
                    tabs.remove(current.getName());
                    chatTab.removeTabAt(index);

                }


            }
        }
    }
}
