package com.tdt.recording;


import music.*;

import javax.sound.sampled.AudioFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.Component;

public class Main {

    public static void main(String[] args) {

        //Create all the components
        Recorder recorder = new QueueRecorder();
        Engine engine = new BasicEngine();
        AudioInput audioSource = new BasicAudioInput();
        AudioOutput audioOut = new AudioFileWriter();
        WebGuiManager gui = new WebGuiManager();
        NoiseFilter noise = new SimpleNoiseFilter();

        //Configure the components
        AudioFormat audioInputFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                44100.0F, 16, 1, 2, 44100.0F, false);
        AudioFormat audioOutputFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                44100.0F, 16, 1, 2, 44100.0F, false);

        AudioComponents components = new AudioComponents();
        components.setAudioChunkSize(1024);
        components.setAudioInputFormat(audioInputFormat);
        components.setAudioOutputFormat(audioOutputFormat);
        components.setRecorder(recorder);
        components.setAudioInput(audioSource);
        components.setEngine(engine);
        components.setGUIManager(gui);
        components.setAudioOutput(audioOut);
        components.setNoiseFilter(noise);

        noise.setEnabled(true);

        //Run the components

        components.initialize();

        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(800, 300));
        frame.add(gui.getRecordPanel());
        frame.add(gui.getChartPanel());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

        components.start();
    }
}
