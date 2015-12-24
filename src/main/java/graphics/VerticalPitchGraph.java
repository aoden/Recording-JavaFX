/*
 * Music Visualizations: http:/github.com/michaelbrooks/music-visualization
 * Copyright 2012, Michael Brooks. BSD License.
 */

package graphics;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author michael
 */
public class VerticalPitchGraph extends Graph1D implements Painter {

    double lowNote;
    double highNote;
    boolean displayGridLines = false;

    Color backgroundColor = new Color(100, 100, 100);
    Color gridLineColor = new Color(120, 120, 120);
    Color noteLineColor = Color.yellow;
    int noteLineWidth = 1;

    double confidence = 1;
    double currentNote;
    ArrayList<Double> notesList = null;

    public int getNoteLineWidth() {
        return noteLineWidth;
    }

    public void setNoteLineWidth(int noteLineWidth) {
        this.noteLineWidth = noteLineWidth;
    }

    public void setBackgroundColor(Color c) {
        backgroundColor = c;
    }

    public Color getGridLineColor() {
        return gridLineColor;
    }

    public void setGridLineColor(Color gridLineColor) {
        this.gridLineColor = gridLineColor;
    }

    public Color getNoteLineColor() {
        return noteLineColor;
    }

    public void setNoteLineColor(Color noteLineColor) {
        this.noteLineColor = noteLineColor;
    }

    public double getLowNote() {
        return lowNote;
    }

    public void setLowNote(double note) {
        lowNote = note;
    }

    public double getHighNote() {
        return highNote;
    }

    public void setHighNote(double note) {
        highNote = note;
    }

    public void setHighLowNote(double high, double low) {
        lowNote = low;
        highNote = high;
    }

    public boolean getDisplayGridLines() {
        return displayGridLines;
    }

    public void setDisplayGridLines(boolean value) {
        displayGridLines = value;
    }

    /**
     * Given a note on the MIDI scale, graphs.
     *
     * @param note
     */
    @Override
    public void graph(double note) {
        currentNote = scaleNote(note);
    }

    protected double scaleNote(double note) {
        note = note - lowNote;
        note /= (highNote - lowNote);
        return note;
    }

    public void paint(Graphics2D g) {

        Rectangle clip = g.getClipBounds();
        double windowWidth = clip.getWidth();
        double windowHeight = clip.getHeight();

        g.setColor(backgroundColor);
        g.fillRect(0, 0, (int) windowWidth, (int) windowHeight);

        if (displayGridLines) {
            ArrayList<Double> notes = getNotes();

            g.setColor(gridLineColor);

            for (int i = 0; i < notes.size(); i++) {
                double note = notes.get(i);

                int noteHeight = (int) Math.round((1 - note) * windowHeight);
                g.drawLine(0, noteHeight, (int) windowWidth, noteHeight);
            }
        }

        Color lineColor = noteLineColor;
        if (confidence != 1) {
            lineColor = new Color(noteLineColor.getRed(),
                    noteLineColor.getGreen(),
                    noteLineColor.getBlue(),
                    (int) Math.round(255 * confidence));
        }
        g.setColor(lineColor);

        if (noteLineWidth != 1) {
            Stroke s = new BasicStroke(noteLineWidth);
            g.setStroke(s);
        }

        int noteHeight = (int) Math.round((1 - currentNote) * windowHeight);
        g.drawLine(0, noteHeight, (int) windowWidth, noteHeight);
    }

    public ArrayList<Double> getNotes() {
        if (notesList == null) {

            notesList = new ArrayList<Double>();

            double note = Math.ceil(lowNote);

            while (note < highNote) {
                notesList.add(scaleNote(note));
                note++;
            }
        }

        return notesList;
    }

    void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}
