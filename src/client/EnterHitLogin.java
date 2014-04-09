
/**
 * Created by krantz on 2014-04-09.
 */
package client;

        import java.awt.event.KeyEvent;
        import java.awt.event.KeyListener;

        import GUI.ConnectButton;

public class EnterHitLogin implements KeyListener {
    private ConnectButton button;

    public EnterHitLogin(ConnectButton button) {
        this.button = button;

    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
            button.actionPerformed(null);
        }

    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }


}

