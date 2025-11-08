package rvb;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    private final StagePanel panel;

    public AppFrame() {
        super("Grid / Weather Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1024, 720);
        setResizable(false);

        panel = new StagePanel();
        add(panel, BorderLayout.CENTER);
    }

    public void setStage(Stage s) {
        panel.setStage(s);
    }
}
