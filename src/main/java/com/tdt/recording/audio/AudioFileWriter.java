/*
 * Music Visualizations: http:/github.com/michaelbrooks/music-visualization
 * Copyright 2012, Michael Brooks. BSD License.
 */

package com.tdt.recording.audio;

import buffer.DataReference;
import dispatch.AudioAvailableEvent;
import music.AbstractComponent;
import music.AudioConverter;
import music.AudioOutput;
import music.ErrorHandler;
import music.data.Wav;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author michael
 */
public class AudioFileWriter extends AbstractComponent implements CustomAudioOutput {

    private AudioFormat format;
    private ByteArrayOutputStream tempStream;
    private AudioFileFormat.Type fileFormat;
    private int numBytesRead;

    @Override
    public void initialize() {

        tempStream = new ByteArrayOutputStream();

        format = getAudioComponents().getAudioOutputFormat();
        fileFormat = AudioFileFormat.Type.WAVE;

        super.initialize();
    }


    @Override
    public void destroy() {

        //Close the temp stream
        try {
            tempStream.close();
        } catch (IOException e) {
            ErrorHandler.pushWarning(e, "Could not close the temporary file.");
        }

        //Read from the temp stream
        BufferedInputStream tempInput = null;

        //Convert to an audio input stream
        AudioInputStream audioIn = new AudioInputStream(tempInput, format, numBytesRead / format.getFrameSize());

    }

    public void play(Wav wav) {
        byte[] data = new byte[AudioConverter.getBytes(format, wav.size())];
        AudioConverter.convertToBytes(format, wav.getRawData(), data);
        tempStream.write(data, 0, 3 * data.length / 4);
        numBytesRead += data.length;
    }

    public void eventFired(AudioAvailableEvent event, Object userData) {
        DataReference<Double> ref = event.getAudioData();

        byte[] data = new byte[AudioConverter.getBytes(format, ref.size())];
        AudioConverter.convertToBytes(format, ref, data);
        tempStream.write(data, 0, 3 * data.length / 4);
        numBytesRead += data.length;

        ref.returnInstance();
    }

    public OutputStream getData() {
        return tempStream;
    }
}
