package com.breakout;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Brick {
	
	public final int WIDTH = 50;
	public final int HEIGHT = 10;
	
	private int x;
	private int y;
	private Board board;
	private boolean broken = false;
	
	public Brick(int row, int column, Board board) {
		this.board = board;
		this.x = column * WIDTH;
		this.y  = row * HEIGHT;
	}
	
	public void paint(Graphics2D g2) {
		if(!hasBroken()){
			g2.setColor(board.getBackground());
			g2.drawRect(x, y, WIDTH, HEIGHT);
			g2.setColor(board.getForeground());
			g2.fillRect(x, y, WIDTH-2, HEIGHT-2);
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	public boolean hasBroken(){
		return broken;
	}
	
	public void setState(boolean broken){
		this.broken = broken;
	}

	public int getX() {
		return x;
	}
}
