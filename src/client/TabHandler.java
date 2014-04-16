package client;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class TabHandler implements Observer, ChangeListener {
    HashMap<String, HistoryArea> tabs;
    private ClientModel clientModel;
    private ChatTabs chatTab;
    private JList list;
    private WriteArea writeArea;
    private TabColorHandler colorHandler;

    @SuppressWarnings("static-access")
    public TabHandler(ClientModel clientModel, ChatTabs chatTab, JList list, WriteArea writeArea) {
        this.clientModel = clientModel;
        this.chatTab = chatTab;
        this.list = list;
        this.writeArea = writeArea;
        clientModel.addObserver(this);
        tabs = new HashMap<String, HistoryArea>();
        createHistoryArea(clientModel.mainChatName);
        chatTab.addChangeListener(this);
        chatTab.setHandler(this);
        colorHandler = new TabColorHandler();
        chatTab.setTabShape(ChatTabs.SHAPE_WINDOWS);


        chatTab.setTabColorProvider(colorHandler);

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
//                System.out.println("fel: " + rec);

            } else {
//                System.out.println("New historyArea: " + rec);
                HistoryArea area = createHistoryArea(rec);
                area.setText(clientModel.getHistory(rec));

            }
            System.out.println(rec);

            if (!rec.equals("Forever alone")) {
                changeTabColor(rec);
                chatTab.repaintTabAreaAndContentBorder();

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
//        ArrayList<String> clients = clientModel.getConnectedClients();
//		System.out.println("Com count: " + chatTab.getComponentCount());
//		for (int i = 0; i < chatTab.getComponentCount(); i++) {
//			JScrollPane p = (JScrollPane) chatTab.getComponent(i);
//			JViewport viewport = p.getViewport();
//			HistoryArea current = (HistoryArea) viewport.getView();
//			String name = current.getName();
//			if (!clients.contains(name) && name != "Forever alone") {
//				System.out.println("Removed from list: " + chatTab.getComponent(i).toString());
//				chatTab.remove(i);
//			}
//
//		}
    }


    public void changeTabColor(String from) {
        int count = chatTab.getTabCount();

        for (int i = 0; i < count; i++) {
            if (chatTab.getTitleAt(i).equals(from) && (i != chatTab.getSelectedIndex())) {
                colorHandler.changeColorAtIndex(i);
            }
        }
    }

    public void changeTab(String chatTo) {
//        colorHandler.resetColorAtIndex(get);

        chatTab.changeCurrent(chatTo);

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

            if (chatTab.getComponent(j) instanceof JScrollBar) {
                JScrollPane p = (JScrollPane) chatTab.getComponentAt(j);
                JViewport viewport = p.getViewport();
                HistoryArea current = (HistoryArea) viewport.getView();
                if (current.equals(ha)) {
                    chatTab.setSelectedIndex(j);
                }
            }
        }
        return ha;

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JScrollPane p = (JScrollPane) chatTab.getSelectedComponent();
        JViewport viewport = p.getViewport();
        HistoryArea current = (HistoryArea) viewport.getView();


        clientModel.setChatWith(current.getName());
        if (current.getName().equals("Forever alone")) {
            list.clearSelection();
        } else if (current.getName().contains("Group: ")) {
            list.clearSelection();
        } else {
            list.setSelectedValue(current.getName(), true);
        }
//        writeArea.requestFocus();
//        writeArea.transferFocus();
        int i = chatTab.getSelectedIndex();
        colorHandler.resetColorAtIndex(i);
        chatTab.repaint();
        chatTab.repaintTabAreaAndContentBorder();

    }


    public void remove(String name) {
        tabs.remove(name);

    }
}
