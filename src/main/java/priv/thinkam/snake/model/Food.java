package priv.thinkam.snake.model;

import priv.thinkam.snake.common.Drawable;
import priv.thinkam.snake.view.GameView;

import java.awt.*;
import java.util.Random;

/**
 * 食物
 *
 * @author thinkam
 * @date 2018/02/16
 */
public class Food implements Drawable {
	private static final int LENGTH = SnakeSection.LENGTH;
	private static final Color COLOR = Color.ORANGE;

	private int coordinateX;
	private int coordinateY;

	private Random random = new Random();
	private Rectangle rectangle = new Rectangle(0, 0, LENGTH, LENGTH);

	public Food() {
		randomLocation();
	}

	/**
	 * 重置位置
	 */
	public void resetLocation() {
		randomLocation();
	}

	/**
	 * 随机设置食物位置
	 */
	private void randomLocation() {
		coordinateX = random.nextInt(GameView.WIDTH / LENGTH) * LENGTH;
		coordinateY = random.nextInt(GameView.HEIGHT / LENGTH) * LENGTH;
	}

	@Override
	public void draw(Graphics g) {
		Color originalColor = g.getColor();
		g.setColor(COLOR);
		g.fillRect(coordinateX, coordinateY, LENGTH, LENGTH);
		g.setColor(originalColor);
	}

	/**
	 * 获取一个矩形
	 *
	 * @return 食物的外切矩形
	 */
	public Rectangle getRectangle() {
		rectangle.setLocation(coordinateX, coordinateY);
		return rectangle;
	}

}
