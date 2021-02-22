import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import jdk.internal.org.jline.utils.Colors;

public class ProgressBar extends JFrame implements Runnable {

    private boolean start = false;
    private boolean kill_trhead = false;
    private Thread thread = new Thread(this);
    private JProgressBar bar;

    public ProgressBar(JPanel panel, int position_x, int position_y, int size_x, int size_y) {
        this.bar = new JProgressBar();
        this.bar.setLayout(null);
        this.bar.setBounds(position_x, position_y, size_x, size_y);
        this.bar.setStringPainted(true);
        this.panel.add(bar);
        this.thread.start();

    }

    public void start() {
        start = true;
    }

    public void stop() {
        start = false;
    }

    public void kill() {
        kill_trhead = true;
    }

    @Override
    public void run() {

        while (!kill_trhead) {// starting thread
            while (start) {
                for (int i = 0; i < 100; i++) {
                    my_bar.setValue(i + 1);
                    my_bar.setString(String.valueOf(i + 1) + "%");
                    try {
                        Thread.sleep(100);// speed
                    } catch (Exception e) {
                    }
                }
            }

        }

    }

    public static void main(String[] args) {

        // creating a new frame
        JFrame fame = new JFrame();
        fame.setSize(700, 400);
        fame.setLocationRelativeTo(null);
        fame.setResizable(false);
        fame.setVisible(true);
        fame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // creating a new panel
        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 700, 400);
        //frame.getContentPane().add(panel);
        

        // creating 1 bar

        ProgressBar bar1 = new ProgressBar(panel, 0, 0, 100, 50);
        ProgressBar bar2 = new ProgressBar(panel, 0, 110, 50, 10);

    }

}