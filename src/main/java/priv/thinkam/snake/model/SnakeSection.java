package priv.thinkam.snake.model;

import priv.thinkam.snake.common.AbstractSnake;
import priv.thinkam.snake.common.DirectionEnum;
import priv.thinkam.snake.common.SnakeSectionColorEnum;

import java.awt.*;

/**
 * 蛇的一节
 *
 * @author thinkam
 * @date 2018/02/16
 */
public class SnakeSection extends AbstractSnake {
	public static final int LENGTH = 40;
	public static final int STEP_LENGTH = 1;

	private int coordinateX;
	private int coordinateY;
	private DirectionEnum direction;
	private Color color;

	private Rectangle rectangle = new Rectangle(0, 0, LENGTH, LENGTH);

	private SnakeSection(Builder builder) {
		this.coordinateX = builder.coordinateX;
		this.coordinateY = builder.coordinateY;
		this.direction = builder.direction;
		this.color = builder.color;
	}

	public int getCoordinateX() {
		return coordinateX;
	}

	public int getCoordinateY() {
		return coordinateY;
	}

	public DirectionEnum getDirection() {
		return direction;
	}

	public void setDirection(DirectionEnum direction) {
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

	/**
	 * 获取一个矩形
	 *
	 * @return 蛇的一节外切矩形
	 */
	public Rectangle getRectangle() {
		rectangle.setLocation(coordinateX, coordinateY);
		return this.rectangle;
	}

	static class Builder {
		private int coordinateX;
		private int coordinateY;
		private DirectionEnum direction;
		private Color color;

		public Builder setLocation(int coordinateX, int coordinateY) {
			this.coordinateX = coordinateX;
			this.coordinateY = coordinateY;
			return this;
		}

		public Builder setDirection(DirectionEnum direction) {
			this.direction = direction;
			return this;
		}

		public Builder setColor(SnakeSectionColorEnum colorEnum) {
			this.color = colorEnum.getColor();
			return this;
		}

		public SnakeSection build() {
			return new SnakeSection(this);
		}
	}
}
