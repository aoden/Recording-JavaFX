package com.tdt.recording;


import com.tdt.recording.ui.RecordingApp;
import music.*;
import music.AudioOutput;

import javax.sound.sampled.AudioFormat;
import javax.swing.*;
import java.awt.*;

public class Main extends RecordingApp {

    public static void main(String[] args) {

        //Create all the components
        Recorder recorder = new QueueRecorder();
        Engine engine = new BasicEngine();
        AudioInput audioSource = new BasicAudioInput();
        AudioOutput audioOut = new com.tdt.recording.audio.AudioFileWriter();
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

        JPanel frame = new JPanel();
        frame.setPreferredSize(new Dimension(800, 300));
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.add(gui.getRecordPanel());
        frame.add(gui.getPitchPanel().getPanel());

        components.start();
        RecordingApp recordingApp = new RecordingApp(frame);
        recordingApp.launch();
    }
}
