/*
 * Music Visualizations: http:/github.com/michaelbrooks/music-visualization
 * Copyright 2012, Michael Brooks. BSD License.
 */

package graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author michael
 */
public class ChartPanel extends JPanel {

    ArrayList<Painter> painters = new ArrayList<Painter>();
    private boolean willClear = true;

    public ChartPanel() {
        this.setBackground(Color.black);
        this.setIgnoreRepaint(true);
    }

    public synchronized void addPainter(Painter p) {
        painters.add(p);
    }

    public synchronized void removePainter(Painter p) {
        painters.remove(p);
    }

    public void setClearsOnPaint(boolean value) {
        willClear = value;
    }

    public boolean clearsOnPaint() {
        return willClear;
    }

    @Override
    public synchronized void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;

        if (willClear) {
            graphics.setColor(getBackground());
            graphics.fillRect(0, 0, getWidth(), getHeight());
        }

        for (Painter p : painters) {
            p.paint(graphics);
        }

    }
}
