package Testing;/*
 * Created by krantz on 2014-04-12.
 */

import com.jidesoft.swing.JideTabbedPane;

import javax.swing.*;


public class TestTabs {

    public TestTabs() {
        JFrame frame = new JFrame("TestTabs");
        JideTabbedPane tab = new JideTabbedPane();
        tab.setShowCloseButton(true);
//        tab.setShowCloseButtonOnSelectedTab(true);
        tab.setShowCloseButtonOnTab(true);
//        tab.setShowCloseButton(true);
        JPanel p = new JPanel();
        p.add(new JLabel("lolakjsflkzghfnsmz"));
        tab.add("test1", new JPanel());
        tab.add("heydnms", new JPanel());
        tab.add("heya", p);

        frame.add(tab);
        frame.setVisible(true);
        frame.pack();

    }

    public static void main(String[] args) {
        new TestTabs();
    }
}
