/*
 * Music Visualizations: http:/github.com/michaelbrooks/music-visualization
 * Copyright 2012, Michael Brooks. BSD License.
 */

package graphics;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author michael
 */
public class GraphPanel {
    
    Window window;
    ChartPanel panel;

    public GraphPanel(String title, int width, int height) {
        panel = new ChartPanel();
        panel.setPreferredSize(new Dimension(width, height));

        window = new JDialog((Frame)null, title);
        window.add(panel);
        window.pack();
    }

    public Window getWindow() {
        return window;
    }

    public ChartPanel getPanel() {
        return panel;
    }

    public boolean isVisible() {
        return window.isVisible();
    }

    public void destroy() {
        window.dispose();
    }

    public Window getFrame() {
        return window;
    }

    public void start() {
        window.setVisible(true);
    }

    public void stop() {
        window.setVisible(false);
    }

}
