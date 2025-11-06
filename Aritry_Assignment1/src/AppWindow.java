import javax.swing.JFrame;
import java.awt.BorderLayout;


public class AppWindow {
    private JFrame frame;
    private GridPanel panel;

    public AppWindow() {
        frame = new JFrame("COMP2000 2025 — Terrain+Weather Grid (final)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 720);

        panel = new GridPanel(20, 20, 35, 10);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
    }

    public void showWindow() {
        frame.setVisible(true);
    }
}
