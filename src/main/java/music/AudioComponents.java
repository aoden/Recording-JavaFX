/*
 * Music Visualizations: http:/github.com/michaelbrooks/music-visualization
 * Copyright 2012, Michael Brooks. BSD License.
 */
package music;

import javax.sound.sampled.AudioFormat;

/**
 * @author michael
 */
public class AudioComponents {

    private static AudioComponents instance;
    private Recorder recorder;
    private AudioOutput audioOut;
    private GUIManager guiManager;
    private Engine engine;
    private AudioInput audioInput;
    private NoiseFilter noiseFilter;
    private AudioFormat audioInputFormat;
    private AudioFormat audioOutputFormat;
    private int audioChunkSize;

    public AudioComponents() {
        instance = this;
    }

    public static AudioComponents getInstance() {
        return instance;
    }

    public Recorder getRecorder() {
        return recorder;
    }

    public void setRecorder(Recorder recorder) {
        this.recorder = recorder;
        this.recorder.setAudioComponents(this);
    }

    AudioOutput getAudioOutput() {
        return audioOut;
    }

    public void setAudioOutput(AudioOutput audioOut) {
        this.audioOut = audioOut;
        this.audioOut.setAudioComponents(this);
    }

    public GUIManager getGUIManager() {
        return guiManager;
    }

    public void setGUIManager(GUIManager gui) {
        this.guiManager = gui;
        this.guiManager.setAudioComponents(this);
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
        this.engine.setAudioComponents(this);
    }

    public AudioInput getAudioInput() {
        return audioInput;
    }

    public void setAudioInput(AudioInput audioInput) {
        this.audioInput = audioInput;
        this.audioInput.setAudioComponents(this);
    }

    NoiseFilter getNoiseFilter() {
        return noiseFilter;
    }

    public void setNoiseFilter(NoiseFilter noiseFilter) {
        this.noiseFilter = noiseFilter;
        this.noiseFilter.setAudioComponents(this);
    }

    public void initialize() {
        this.recorder.initialize();
        this.audioInput.initialize();
        this.engine.initialize();
        this.guiManager.initialize();
        this.audioOut.initialize();
        this.noiseFilter.initialize();

        this.audioInput.addListener(engine);
    }

    public void destroy() {
        this.audioInput.destroy();
        this.engine.destroy();
        this.guiManager.destroy();
        this.audioOut.destroy();
        this.recorder.destroy();
        this.noiseFilter.destroy();
    }

    public void start() {
        getRecorder().start();
        getAudioInput().start();
        getEngine().start();
        getGUIManager().start();
        getAudioOutput().start();
        getNoiseFilter().destroy();
    }

    public void stop() {
        this.audioInput.stop();
        this.engine.stop();
        this.guiManager.stop();
        this.audioOut.stop();
        this.recorder.stop();
        this.noiseFilter.stop();
    }

    public AudioFormat getAudioInputFormat() {
        return audioInputFormat;
    }

    public void setAudioInputFormat(AudioFormat audioInputFormat) {
        this.audioInputFormat = audioInputFormat;
    }

    public int getAudioChunkSize() {
        return audioChunkSize;
    }

    public void setAudioChunkSize(int size) {
        this.audioChunkSize = size;
    }

    public AudioFormat getAudioOutputFormat() {
        return audioOutputFormat;
    }

    public void setAudioOutputFormat(AudioFormat audioOutputFormat) {
        this.audioOutputFormat = audioOutputFormat;
    }
}
