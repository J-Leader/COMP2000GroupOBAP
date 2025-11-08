package rvb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class StagePanel extends JPanel {
    private Stage stage;
    private Point mouse;

    public StagePanel() {
        setBackground(Color.BLACK);
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouse = e.getPoint();
                repaint();
            }
        });

        Timer timer = new Timer(1000 / 30, e -> repaint());
        timer.start();
    }

    public void setStage(Stage s) {
        this.stage = s;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (stage != null)
            stage.paint((Graphics2D) g, mouse, getSize());
    }
}
