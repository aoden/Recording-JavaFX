/*
 * Music Visualizations: http:/github.com/michaelbrooks/music-visualization
 * Copyright 2012, Michael Brooks. BSD License.
 */

package music;

/**
 * @author michael
 */
public abstract class AbstractComponent implements Component {

    private AudioComponents components;
    private boolean initialized = false;

    public AbstractComponent() {
    }

    public AudioComponents getAudioComponents() {
        return components;
    }

    public void setAudioComponents(AudioComponents components) {
        this.components = components;
    }

    public void initialize() {

    }

    public void start() {

    }

    public void stop() {

    }

    public void destroy() {

    }

    public final boolean isInitialized() {
        return this.initialized;
    }

    public final void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

}
