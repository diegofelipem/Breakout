package com.breakout;

import java.io.IOException;

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
					audioPath = "res/ball_poing.wav";
					break;
				case BRICK_CRASH:
					audioPath = "res/brick_crash.wav";
					break;
				case LEVEL_UP:
					audioPath = "res/level_up.wav";
					break;
				case GAME_OVER:
					audioPath = "res/game_over.wav";
					break;
				default:
					throw new IllegalArgumentException("Invalid sound.");
				}

				AudioInputStream inputStream = AudioSystem
						.getAudioInputStream(GameMain.class.getResourceAsStream(audioPath));
				clip.open(inputStream);
				clip.start();
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
		}).start();
		;
	}
}
