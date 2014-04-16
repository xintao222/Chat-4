package Testing;/*
 * Created by krantz on 2014-04-12.
 */

import com.jidesoft.dialog.JideOptionPane;
import com.jidesoft.swing.JideTabbedPane;

import javax.swing.*;


public class TestTabs {

    public TestTabs() {
        if(JideOptionPane.showConfirmDialog(null,"Lave Group", "You are about to leave: " ,JOptionPane.YES_NO_OPTION) == JideOptionPane.YES_OPTION){

        }


    }

    public static void main(String[] args) {
        new TestTabs();
    }
}
