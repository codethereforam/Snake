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
	public static final int LENGTH = SnakeSection.LENGTH;
	private static Color COLOR = Color.ORANGE;
	private int coordinateX = 80;
	private int coordinateY = 80;
	private Random random = new Random();

	public Food() {
		randomLocation();
	}

	public void resetLocation() {
		randomLocation();
	}

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

	public Rectangle getRectangle() {
		//TODO: whether singleton
		return new Rectangle(coordinateX, coordinateY, LENGTH, LENGTH);
	}

}
