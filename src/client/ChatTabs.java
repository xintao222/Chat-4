package client;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import client.ClientModel;
import com.jidesoft.swing.JideTabbedPane;
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


    @Override
    public void removeTabAt(int index){
    //        int index = getSelectedIndex();
        if (index >= 0) {
            JScrollPane p = (JScrollPane) getSelectedComponent();
            JViewport viewport = p.getViewport();
            HistoryArea current = (HistoryArea) viewport.getView();
            if (!current.getName().equals("Forever alone")) {
                handler.remove(current.getName());
                super.removeTabAt(index);



            }


        }

//        super.removeTabAt(index);

    }

    public void setHandler(TabHandler handler) {
        this.handler = handler;
    }

    public TabHandler getHandler() {
        return handler;
    }
}
