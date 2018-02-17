package priv.thinkam.snake.controller;

import priv.thinkam.snake.common.Direction;
import priv.thinkam.snake.model.Food;
import priv.thinkam.snake.model.Snake;
import priv.thinkam.snake.view.GameView;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 控制器
 *
 * @author thinkam
 * @date 2018/02/16
 */
public class Controller {

	public static void main(String[] args) {
		new Controller().launch();
	}

	private void launch() {
		Food food = new Food();
		Snake snake = new Snake(food);

		GameView view = new GameView(snake, food);
		view.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				Direction headDirection = snake.getHeadDirection();
				switch (e.getKeyCode()) {
					case KeyEvent.VK_UP:
						if (headDirection != Direction.DOWN && headDirection != Direction.UP) {
							snake.setHeadDirection(Direction.UP);
						}
						break;
					case KeyEvent.VK_DOWN:
						if (headDirection != Direction.DOWN && headDirection != Direction.UP) {
							snake.setHeadDirection(Direction.DOWN);
						}
						break;
					case KeyEvent.VK_LEFT:
						if (headDirection != Direction.LEFT && headDirection != Direction.RIGHT) {
							snake.setHeadDirection(Direction.LEFT);
						}
						break;
					case KeyEvent.VK_RIGHT:
						if (headDirection != Direction.LEFT && headDirection != Direction.RIGHT) {
							snake.setHeadDirection(Direction.RIGHT);
						}
						break;
					default:
				}
			}
		});
	}
}
