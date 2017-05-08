package com.game;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class Game {

	Board boardGame;
	JFrame frame;

	public void initGui() {
		frame = new JFrame("Tennis Game");

		frame.setJMenuBar(getMenu());

		this.boardGame = new Board();
		frame.getContentPane().add(boardGame);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public JMenuBar getMenu() {

		JMenuBar bar = new JMenuBar();

		JMenu menu = new JMenu("Game");

		JMenuItem newGame = new JMenuItem("New...");
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		newGame.addActionListener(e -> {
			int op = JOptionPane.showConfirmDialog(null, "Start a new Game?", "New Game", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);

			if (op == JOptionPane.OK_OPTION) {
				boardGame.newGame();
			}
		});

		JMenuItem startGame = new JMenuItem("Start...");
		startGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		startGame.addActionListener(e -> {
			boardGame.starGame();
		});

		menu.add(newGame);
		menu.add(startGame);

		JMenu menu2 = new JMenu("Options");

		JMenuItem speedBall = new JMenuItem("Set Speed...");
		speedBall.addActionListener(e -> {

			JSlider sl = getSpeedSlider();
			JOptionPane.showConfirmDialog(null, sl, "Select a speed", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE);
			boardGame.setSpeed(sl.getValue());

		});

		menu2.add(speedBall);

		bar.add(menu);
		bar.add(menu2);

		return bar;
	}

	public JSlider getSpeedSlider() {
		final int FPS_MIN = 0;
		final int FPS_MAX = 3;
		final int FPS_INIT = 0;

		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, FPS_MIN, FPS_MAX, FPS_INIT);

		speedSlider.setMajorTickSpacing(3);
		speedSlider.setMinorTickSpacing(1);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);

		return speedSlider;
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			new Game().initGui();
		});
	}
}
