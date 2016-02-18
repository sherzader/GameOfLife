import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Utils {

  public static JFrame launchFrame(final JComponent content) {
    JFrame ret = new JFrame("Sheri says, 'Hi :)'");

    content.setOpaque(false);

    JPanel wrapper = new JPanel(new GridLayout(1, 1));
    wrapper.add(content);
    wrapper.setBackground(Color.white);

    ret.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ret.setContentPane(wrapper);
    ret.setSize(800, 600);
    ret.setLocationRelativeTo(null);
    ret.setVisible(true);

    Thread t = new Thread(new Runnable(){
      @Override
      public void run(){
        while(true){
          content.repaint();
          try {
            Thread.sleep(30);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });

    t.setDaemon(true);
    t.start();

    return ret;
  }


}
