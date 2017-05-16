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
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;
	public final int WIDTH = 450;
	public final int HEIGHT = 300;
	private final int UPDATE_INTERVAL = 10;

	private Timer timer;
	public final Ball ball;
	public final Paddle paddle;
	public int bricksBroken = 0;
	private int score = 0;
	private int level = 1;

	public ArrayList<Brick> wallBricks = new ArrayList<Brick>();

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
				repaint();
			}
		};

		timer = new Timer(UPDATE_INTERVAL, action);

		setFocusable(true);
	}

	private void createWallBricks() {

		int maxColumnBricks = (int) (WIDTH / 50);
		int maxRowBricks = 3;

		wallBricks = new ArrayList<>();

		maxRowBricks += level / 2;
		maxColumnBricks = 1;

		for (int r = 0; r < maxRowBricks; r++) {
			for (int c = 0; c < maxColumnBricks; c++) {
				Brick b = new Brick(r, c, this);
				wallBricks.add(b);
			}
		}
	}

	private void drawBricks(Graphics2D g2) {

		for (Iterator<Brick> it = wallBricks.iterator(); it.hasNext();) {
			Brick b = it.next();
			if (b.hasBroken()) {
				it.remove();
				bricksBroken++;
			} else {
				b.paint(g2);
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	public void nextLevel() {
		stopGame();
		JOptionPane.showMessageDialog(this, "Level " + level + " wins!");
		level++;
		paddle.reset();
		ball.reset();
		ball.increaseSpeed(level);
		createWallBricks();
		updateLevel();
		repaint();
	}

	private void updateLevel() {
		((GameMain) getTopLevelAncestor()).level.setText(String.valueOf(level));
	}

	private void updateBoard() {
		ball.move();
		paddle.move();
		updateScore();
		updateLevel();
		repaint();
	}

	private void updateScore() {
		score = bricksBroken * 10;
		((GameMain) getTopLevelAncestor()).score.setText(String.valueOf(score));
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
		level = 1;
		score = 0;
		bricksBroken = 0;
		paddle.reset();
		ball.reset();
		createWallBricks();
		updateBoard();
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
