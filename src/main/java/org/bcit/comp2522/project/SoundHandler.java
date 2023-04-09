package org.bcit.comp2522.project;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Sound handler class, handles audio playback.
 */
public class SoundHandler {

  private static SoundHandler singleInstance;
  /**
   * The Audio file.
   */
  File audioFile;
  /**
   * The Audio stream.
   */
  AudioInputStream audioStream;
  /**
   * The Clip.
   */
  Clip clip;

  private SoundHandler() {
    try {
      audioFile = new File(Window.userDir+"music.wav");
      audioStream = AudioSystem.getAudioInputStream(audioFile);

      // Create a Clip object
      clip = AudioSystem.getClip();

      // Open the clip
      clip.open(audioStream);

      // Start playing the clip
      clip.start();

      // Keep the program running to allow the clip to play
      Thread.sleep(clip.getMicrosecondLength() / 1000);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


  /**
   * Get instance sound handler.
   *
   * @return sound handler single instance
   */
  public static SoundHandler getInstance() {
    if (singleInstance == null) {
      singleInstance = new SoundHandler();
    }
    return singleInstance;
  }
}
