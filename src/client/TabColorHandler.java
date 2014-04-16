package client;/*
 * Created by krantz on 2014-04-15.
 */

import com.jidesoft.swing.JideTabbedPane;

import java.awt.*;
import java.util.ArrayList;

public class TabColorHandler implements JideTabbedPane.ColorProvider {
    ArrayList<Integer> whichColor;

    public TabColorHandler() {
        whichColor = new ArrayList<Integer>();
    }


    @Override
    public Color getBackgroundAt(int i) {
        if (whichColor.contains(new Integer(i))) {
            return Color.RED;
        } else {
            return Color.WHITE;
        }
    }


    @Override
    public Color getForegroundAt(int i) {
        return null;
    }

    @Override
    public float getGradientRatio(int i) {
        return 1;
    }

    public void changeColorAtIndex(int i) {
        System.out.println("Changed color at index: " + i);
        if (!whichColor.contains(new Integer(i))) {
            whichColor.add(i);
        }

    }


    public void resetColorAtIndex(int i) {
        System.out.println("Resetting at index: " + i);
        whichColor.contains(i);
        whichColor.remove(new Integer(i));

    }

    public void resetAllcolors() {
        whichColor.clear();
    }


}
