package com.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;
	private final int CANVAS_WIDTH = 400;
	private final int CANVAS_HEIGHT = 300;
	private final int UPDATE_INTERVAL = 10;

	private Timer timer;
	public Ball ball;
	public Racquet racquet;
	
	private JLabel scoreLabel,score;

	public Board() {

		ball = new Ball(this);
		racquet = new Racquet(this);

		setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
		scoreLabel = new JLabel("Score: ");
		scoreLabel.setFont(getFont().deriveFont(12f));
		score = new JLabel();
		score.setFont(getFont().deriveFont(12f));
		this.add(scoreLabel);
		this.add(score);
		
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
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

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);
	}

	private void updateBoard() {
		ball.move();
		racquet.move();
		repaint();
	}
	
	public void gameOver(){
		JOptionPane.showMessageDialog(this, "Game Over");
		newGame();
	}

	public void starGame() {
		timer.start();
	}
	
	public void newGame(){
		timer.stop();
		ball = new Ball(this);
		racquet = new Racquet(this);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2);
		racquet.paint(g2);
	}
}
