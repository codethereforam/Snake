package priv.thinkam.snake.model;

import priv.thinkam.snake.common.AbstractSnake;
import priv.thinkam.snake.common.Direction;

import java.awt.*;

/**
 * 蛇的一节
 *
 * @author thinkam
 * @date 2018/02/16
 */
public class SnakeSection extends AbstractSnake {
	public static final int LENGTH = 40;
	private int coordinateX = 0;
	private int coordinateY = 0;
	private Direction direction = Direction.RIGHT;
	private Color color = Color.BLACK;
	public static final int STEP_LENGTH = 1;

	public SnakeSection() {
	}

	public SnakeSection(int coordinateX, int coordinateY, Direction direction) {

		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.direction = direction;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}

	public int getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public void move() {
		switch (direction) {
			case UP:
				coordinateY -= STEP_LENGTH;
				break;
			case DOWN:
				coordinateY += STEP_LENGTH;
				break;
			case LEFT:
				coordinateX -= STEP_LENGTH;
				break;
			case RIGHT:
				coordinateX += STEP_LENGTH;
				break;
			default:
		}
	}

	@Override
	public void draw(Graphics g) {
		Color originalColor = g.getColor();
		g.setColor(color);
		g.fillOval(coordinateX, coordinateY, LENGTH, LENGTH);
		g.setColor(originalColor);
	}

	public Rectangle getRectangle() {
		return new Rectangle(coordinateX, coordinateY, LENGTH, LENGTH);
	}
}
