package com.tdt.recording.audio;


import music.AudioOutput;

import java.io.OutputStream;

public interface CustomAudioOutput extends AudioOutput {

    OutputStream getData();
}
