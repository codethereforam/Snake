package priv.thinkam.snake.common;

import java.awt.*;

/**
 * @author thinkam
 * @date 2018/02/18
 */
public enum SnakeSectionColorEnum {
	/**
	 * HEAD_COLOR: 蛇头颜色
	 * BODY_COLOR: 蛇身颜色
	 */
	HEAD_COLOR(Color.RED), BODY_COLOR(Color.BLACK);
	private Color color;

	SnakeSectionColorEnum(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
}
