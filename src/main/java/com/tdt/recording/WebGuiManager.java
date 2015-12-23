package com.tdt.recording;


import com.tdt.recording.ui.RecordPanel;
import graphics.ChartPanel;
import graphics.MultiLineGraphPanel;
import music.AbstractGUIManager;
import music.Recorder;
import music.data.DataFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WebGuiManager extends AbstractGUIManager {

    com.tdt.recording.ui.MultiLineGraphPanel pitchPanel;
    RecordPanel recordPanel = new RecordPanel();

    ChartPanel chartPanel;
    private Component[] coms;

    public Component[] getComs() {
        return coms;
    }

    public void setComs(Component[] coms) {
        this.coms = coms;
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public void setChartPanel(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }

    public com.tdt.recording.ui.MultiLineGraphPanel getPitchPanel() {
        return pitchPanel;
    }

    public RecordPanel getRecordPanel() {
        return recordPanel;
    }

    private ChartPanel showVisualization() {

//        coms = getPitchPanel().getWindow().getComponents();
        return this.pitchPanel.getPanel();
    }

    @Override
    public void initialize() {

        this.recordPanel = new RecordPanel(getAudioComponents());

        pitchPanel = new com.tdt.recording.ui.MultiLineGraphPanel(1000, 1, 50, "Pitch", 1000, 300);
        pitchPanel.getGraph().setColor(0, Color.green);
        pitchPanel.getGraph().setConnectPoints(0, false);
        pitchPanel.getGraph().setVerticalRange(0, 40, 110);
        pitchPanel.getGraph().plotMinValue(0, false);
        pitchPanel.getGraph().setLabel(0, "Pitch");

        setChartPanel(showVisualization());

        setupThread(new Runnable() {

            Recorder rec;
            long lastGraphedTime = 0;

            public void run() {

                rec = getAudioComponents().getRecorder();

                while (polling()) {

                    if (!paused()) {

                        java.util.List<DataFrame> latestFrames = rec.popFrames();
                        if (latestFrames.size() > 0) {

                            DataFrame frame = latestFrames.get(latestFrames.size() - 1);

                            if (frame.getTime() != lastGraphedTime) {

                                double pitch = 0;
                                double quality = 0;
                                double spectralCentroid = 0;
                                double[] flatPS = null;

                                for (int i = 0; i < latestFrames.size(); i++) {
                                    DataFrame subFrame = latestFrames.get(i);

                                    pitch = subFrame.getPitchInfo().getSmoothedPitch();
                                    quality = subFrame.getQualityInfo().getSmoothedCorrelation();

                                    if (subFrame.getSM().isSilence() || pitch <= 1 || quality < 0.2) {
                                        pitch = 0;
                                        spectralCentroid = 0;

                                    }
                                    double[] ps = subFrame.getPS().getRawData();

                                    flatPS = Arrays.copyOf(ps, ps.length);

                                    for (int j = 0; j < ps.length; j++) {
                                        flatPS[j] = Math.pow(flatPS[j], 0.3);
                                    }
                                }

                                if (pitchPanel.isVisible()) {
                                    ArrayList<Double> values = new ArrayList<Double>();
                                    values.add(pitch);
                                    pitchPanel.graph(values);
                                }
                            }

                            pitchPanel.repaint();

                            lastGraphedTime = frame.getTime();
                        }

                    }

                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }


        });
    }

    @Override
    public void start() {
//        this.pitchPanel.getWindow().dispose();
        this.pollingThread.start();
    }
}
