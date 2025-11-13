package rvb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class AppFrame extends JFrame {

    private Stage stage;
    private final DrawPanel panel = new DrawPanel();

    public AppFrame() {
        super("RVB — Weather Grid");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1024, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        installKeyBindings();
    }

    public void setStage(Stage s) {
        this.stage = s;
        panel.setStage(s);
    }

    private void installKeyBindings() {
        InputMap im = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke("RIGHT"), "mvRight");
        im.put(KeyStroke.getKeyStroke("LEFT"),  "mvLeft");
        im.put(KeyStroke.getKeyStroke("UP"),    "mvUp");
        im.put(KeyStroke.getKeyStroke("DOWN"),  "mvDown");
        im.put(KeyStroke.getKeyStroke("TAB"),   "cycleSel");
        im.put(KeyStroke.getKeyStroke('M'),     "toggleAI");

        am.put("mvRight", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                if (stage != null) stage.moveSelected(1, 0);
                panel.repaint();
            }
        });
        am.put("mvLeft", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                if (stage != null) stage.moveSelected(-1, 0);
                panel.repaint();
            }
        });
        am.put("mvUp", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                if (stage != null) stage.moveSelected(0, -1);
                panel.repaint();
            }
        });
        am.put("mvDown", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                if (stage != null) stage.moveSelected(0, 1);
                panel.repaint();
            }
        });
        am.put("cycleSel", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                if (stage != null) stage.cycleSelected();
                panel.repaint();
            }
        });
        am.put("toggleAI", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                if (stage != null) stage.toggleAI();
                panel.repaint();
            }
        });
    }

    private static class DrawPanel extends JPanel implements MouseMotionListener {
        private Stage stage;
        private Point mouse;

        DrawPanel() {
            setBackground(Color.BLACK);
            addMouseMotionListener(this);
        }

        void setStage(Stage s) { this.stage = s; }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (stage != null) {
                stage.paint((Graphics2D) g, mouse, getSize());
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) { mouseMoved(e); }

        @Override
        public void mouseMoved(MouseEvent e) {
            mouse = e.getPoint();
            repaint();
        }
    }
}