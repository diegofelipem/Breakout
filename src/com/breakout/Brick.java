package com.breakout;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Brick {
	
	public final int WIDTH = 50;
	public final int HEIGHT = 10;
	
	private int x;
	private int y;
	private Board board;
	
	public Brick(int x, int y, Board board) {
		this.x = x;
		this.y  = y + 15;
		this.board = board;
	}
	
	public void paint(Graphics2D g2) {
		g2.setStroke(new BasicStroke(3));
		g2.setColor(board.getBackground());
		g2.drawRect(x, y, WIDTH, HEIGHT);
		g2.setColor(board.getForeground());
		g2.fillRect(x, y, WIDTH, HEIGHT);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
}
