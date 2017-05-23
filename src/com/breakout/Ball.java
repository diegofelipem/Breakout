package com.breakout;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {

	private int x = 0;
	private int y = 0;
	private final int DIAMETER = 30;
	private int xSpeed = 1;
	private int ySpeed = 1;

	private final Board board;
	private final Paddle paddle;

	public Ball(Board board) {
		this.board = board;
		this.paddle = board.paddle;
		y = paddle.getTopY() - DIAMETER;
		x = board.WIDTH / 2 - DIAMETER / 2;
	}

	public void move() {

		if (board.wallBricks.isEmpty()) {
			board.nextLevel();
		}

		if (x > board.getPreferredSize().width - DIAMETER || x < 0) {
			xSpeed = -xSpeed;
			SoundManager.playSound(SoundManager.BALL_POING);
		} else if (y < 0) {
			ySpeed = -ySpeed;
			SoundManager.playSound(SoundManager.BALL_POING);

		} else if (y + DIAMETER > paddle.getTopY() + paddle.HEIGHT) {
			board.gameOver();

		} else if (paddleCollision()) {

			float paddleCenter = paddle.getX() + (paddle.WIDTH / 2);
			float paddleRelativePos = (this.x + (DIAMETER / 2) - paddleCenter) / (paddle.WIDTH / 2);

			if ((paddleRelativePos > 0 && xSpeed < 0) || (paddleRelativePos < 0 && xSpeed > 0)) {
				xSpeed = -xSpeed;
			}

//			if (paddle.isMoving()) {
//				int moveShift = 0;
//				if (xSpeed > 0) {
//					moveShift = (int) Math.ceil(paddle.getDirection());
//				} else if (xSpeed < 0) {
//					moveShift = (int) Math.ceil(paddle.getDirection()) * (-1);
//				}
//				x += moveShift;
//			}

			ySpeed = -ySpeed;
			y = paddle.getTopY() - DIAMETER;
			SoundManager.playSound(SoundManager.BALL_POING);
		}

		for (Brick brick : board.wallBricks) {

			if (brickCollision(brick)) {

				float brickCenter = brick.getX() + (brick.WIDTH / 2);
				float brickRelativePos = (this.x + (DIAMETER / 2) - brickCenter) / (brick.WIDTH / 2);

				if ((brickRelativePos > 0 && xSpeed < 0) || (brickRelativePos < 0 && xSpeed > 0)) {
					xSpeed = -xSpeed;
				}

				brick.setState(true);
				ySpeed = -ySpeed;
				SoundManager.playSound(SoundManager.BRICK_CRASH);
				break;
			}
		}

		x += xSpeed;
		y += ySpeed;
	}

	public void paint(Graphics2D g2) {
		g2.fillOval(x, y, DIAMETER, DIAMETER);
	}

	public void changeSpeed(int speed) {
		xSpeed = ySpeed = speed;
	}

	public boolean paddleCollision() {
		return paddle.getBounds().intersects(this.getBounds());
	}

	public boolean brickCollision(Brick b) {
		return this.getBounds().intersects(b.getBounds());
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}

	public void reset() {
		x = board.WIDTH / 2 - DIAMETER / 2;
		y = paddle.getTopY() - DIAMETER;
		xSpeed = ySpeed = 1;
	}
}
