package priv.thinkam.snake;

import java.awt.*;

/**
 * @author thinkam
 * @date 2018/02/16
 */
public class SnakeSection extends AbstractSnake {
	public static final int LENGTH = 40;
	private int coordinateX = 0;
	private int coordinateY = 0;
	private Direction direction = Direction.RIGHT;
	private Color color = Color.BLACK;
	private static final int STEP = 40;

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
	void move() {
		switch (direction) {
			case UP:
				coordinateY -= STEP;
				break;
			case DOWN:
				coordinateY += STEP;
				break;
			case LEFT:
				coordinateX -= STEP;
				break;
			case RIGHT:
				coordinateX += STEP;
				break;
			default:
		}
	}

	@Override
	public void draw(Graphics g) {
		Color originalColor = g.getColor();
		g.setColor(color);
		g.fillRect(coordinateX, coordinateY, LENGTH, LENGTH);
		g.setColor(originalColor);
	}

	public Rectangle getRectangle() {
		return new Rectangle(coordinateX, coordinateY, LENGTH, LENGTH);
	}
}
