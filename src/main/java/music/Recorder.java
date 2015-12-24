/*
 * Music Visualizations: http:/github.com/michaelbrooks/music-visualization
 * Copyright 2012, Michael Brooks. BSD License.
 */

package music;

import music.data.DataFrame;

import java.util.List;

/**
 *
 * @author michael
 */
public interface Recorder extends Component {

    void addFrame(DataFrame frame);

    DataFrame getLastFrame();

    List<DataFrame> popFrames();

}
