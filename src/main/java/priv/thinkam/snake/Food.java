package priv.thinkam.snake;

import java.awt.*;

/**
 * @author thinkam
 * @date 2018/02/16
 */
public class Food implements Drawable {
	public static final int LENGTH = SnakeSection.LENGTH;
	private int coordinateX = 80;
	private int coordinateY = 80;
	private Color color = Color.ORANGE;

	@Override
	public void draw(Graphics g) {
		Color originalColor = g.getColor();
		g.setColor(color);
		g.fillOval(coordinateX, coordinateY, LENGTH, LENGTH);
		g.setColor(originalColor);
	}

	public Rectangle getRectangle() {
		//TODO: whether singleton
		return new Rectangle(coordinateX, coordinateY, LENGTH, LENGTH);
	}

}
