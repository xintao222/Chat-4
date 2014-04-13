package GUI;

import client.ClientModel;
import client.TabHandler;
import client.WriteArea;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListListener implements ListSelectionListener {
    //	private String[] listEntries;
//	private JList list = new JList(listEntries);
    private String selection = "troll";
    private ClientModel clientModel;
    private TabHandler tabHandler;
    private WriteArea writeArea;

    public ListListener(ClientModel clientModel, TabHandler tabHandler, WriteArea writeArea) {
        this.clientModel = clientModel;
        this.tabHandler = tabHandler;
        this.writeArea = writeArea;
    }

    public String getSelected() {
        return selection;
    }

    @Override
    public void valueChanged(ListSelectionEvent arg0) {
        boolean adjust = arg0.getValueIsAdjusting();
        if (!adjust) {
            JList list = (JList) arg0.getSource();
            int selections[] = list.getSelectedIndices();
            Object selectionValues[] = list.getSelectedValues();
            for (int i = 0, n = selections.length; i < n; i++) {
                if (i == 0) {
                }
                clientModel.setChatWith(selectionValues[i].toString());
                tabHandler.changeTab(selectionValues[i].toString());
            }
        }
        writeArea.requestFocus();
    }
}