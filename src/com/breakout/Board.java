package com.breakout;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;
	public final int WIDTH = 400;
	public final int HEIGHT = 300;
	private final int UPDATE_INTERVAL = 20;

	private Timer timer;
	public final Ball ball;
	public final Paddle paddle;
	private int totalBricks;
	private String score = "";

	public ArrayList<Brick> wallBricks = new ArrayList<>();

	public Board() {

		paddle = new Paddle(this);
		ball = new Ball(this);

		createWallBricks();

		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				paddle.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					starGame();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				paddle.keyReleased(e);
			}
		});

		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateBoard();
				updateScore();
				repaint();
			}
		};

		timer = new Timer(UPDATE_INTERVAL, action);

		setFocusable(true);
	}

	private void createWallBricks() {

		int maxColumnBricks = WIDTH / 50;
		int maxRowBricks = 5;
		
		wallBricks = new ArrayList<>();

		for (int r = 0; r < maxRowBricks; r++) {
			for (int c = 0; c < maxColumnBricks; c++) {
				Brick b = new Brick(r, c, this);
				wallBricks.add(b);
			}
		}
		totalBricks = wallBricks.size();
	}

	private void drawBricks(Graphics2D g2) {

		if (wallBricks.size() == 0) {
			endGame();
		} else {
			for (Brick b : wallBricks) {
				b.paint(g2);
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	private void endGame() {
		stopGame();
		JOptionPane.showMessageDialog(this, "You Win!");
		newGame();
		
	}

	private void updateBoard() {
		ball.move();
		paddle.move();
		repaint();
	}

	private void updateScore() {
		score = String.valueOf((totalBricks - wallBricks.size()) * 100);
		((GameMain) getTopLevelAncestor()).score.setText(score);
	}

	public void gameOver() {
		stopGame();
		JOptionPane.showMessageDialog(this, "Game Over");
		newGame();
	}

	public void starGame() {
		timer.start();
	}

	public void stopGame() {
		timer.stop();
	}

	public void newGame() {
		stopGame();
		paddle.reset();
		ball.reset();
		createWallBricks();
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2);
		paddle.paint(g2);
		drawBricks(g2);
	}
}
