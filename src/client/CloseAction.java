package client;/*
 * Created by krantz on 2014-04-12.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;


public class CloseAction implements Action {
    private final HashMap<String, HistoryArea> tabs;
    private final TabHandler tabHandler;
    private final ChatTabs chatTab;

    public CloseAction(HashMap<String, HistoryArea> tabs, TabHandler tabHandler, ChatTabs chatTab) {

        this.tabs = tabs;
        this.tabHandler = tabHandler;
        this.chatTab = chatTab;
    }


    @Override
    public Object getValue(String s) {
        return null;
    }

    @Override
    public void putValue(String s, Object o) {

    }

    @Override
    public void setEnabled(boolean b) {

    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {

    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        System.out.println("Close action called");
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
