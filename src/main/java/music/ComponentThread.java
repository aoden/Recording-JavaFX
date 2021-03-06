/*
 * Music Visualizations: http:/github.com/michaelbrooks/music-visualization
 * Copyright 2012, Michael Brooks. BSD License.
 */

package music;

/**
 *
 * @author michael
 */
public class ComponentThread extends Thread {

    private boolean running = false;

    public ComponentThread(String name) {
        super(name);
    }

    @Override
    public synchronized void start() {
        this.setRunning(true);

        super.start();
    }

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized void setRunning(boolean running) {
        this.running = running;
    }

}
