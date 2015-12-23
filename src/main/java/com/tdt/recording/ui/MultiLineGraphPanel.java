/*
 * Music Visualizations: http:/github.com/michaelbrooks/music-visualization
 * Copyright 2012, Michael Brooks. BSD License.
 */

package com.tdt.recording.ui;

import graphics.GraphPanel;
import graphics.MultiLineGraph;

import java.util.ArrayList;

/**
 * @author michael
 */
public class MultiLineGraphPanel extends com.tdt.recording.ui.GraphPanel {
    MultiLineGraph graph;

    public MultiLineGraphPanel(int horizontalPoints, int numberOfLines, int pixelsBetween, String title, int width, int height) {
        super(title, width, height);

        graph = new MultiLineGraph(numberOfLines, pixelsBetween, horizontalPoints);
        panel.addPainter(graph);
    }

    public void graph(ArrayList<Double> values) {
        graph.graph(values);
    }

    public void repaint() {
        panel.repaint();
    }

    public MultiLineGraph getGraph() {
        return graph;
    }
}
