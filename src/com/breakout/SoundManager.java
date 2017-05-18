package com.breakout;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {

	public static final int BALL_POING = 0;
	public static final int BRICK_CRASH = 1;
	public static final int LEVEL_UP = 2;
	public static final int GAME_OVER = 3;

	public static synchronized void playSound(int sound) {

		new Thread(() -> {

			try {

				Clip clip = AudioSystem.getClip();
				String audioPath = "";

				switch (sound) {
				case BALL_POING:
					audioPath = "ball_poing.wav";
					break;
				case BRICK_CRASH:
					audioPath = "brick_crash.wav";
					break;
				case LEVEL_UP:
					audioPath = "level_up.wav";
					break;
				case GAME_OVER:
					audioPath = "game_over.wav";
					break;
				default:
					throw new IllegalArgumentException("Invalid sound.");
				}
				InputStream audioSrc = new BufferedInputStream(
						GameMain.class.getResourceAsStream("/com/breakout/res/" + audioPath));
				AudioInputStream inputStream = AudioSystem.getAudioInputStream(audioSrc);
				clip.open(inputStream);
				clip.start();
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
		}).start();
		;
	}
}
