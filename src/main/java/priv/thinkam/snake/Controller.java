package priv.thinkam.snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author thinkam
 * @date 2018/02/16
 */
public class Controller {

	public static void main(String[] args) {
		new Controller().launch();
	}

	public void launch() {
		Food food = new Food();
		Snake snake = new Snake(food);

		GameView view = new GameView(snake, food);
		view.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				Direction headDirection = snake.getHead().getDirection();
				switch (e.getKeyCode()) {
					case KeyEvent.VK_UP:
						if (headDirection != Direction.DOWN && headDirection != Direction.UP) {
							snake.getHead().setDirection(Direction.UP);
						}
						break;
					case KeyEvent.VK_DOWN:
						if (headDirection != Direction.DOWN && headDirection != Direction.UP) {
							snake.getHead().setDirection(Direction.DOWN);
						}
						break;
					case KeyEvent.VK_LEFT:
						if (headDirection != Direction.LEFT && headDirection != Direction.RIGHT) {
							snake.getHead().setDirection(Direction.LEFT);
						}
						break;
					case KeyEvent.VK_RIGHT:
						if (headDirection != Direction.LEFT && headDirection != Direction.RIGHT) {
							snake.getHead().setDirection(Direction.RIGHT);
						}
						break;
					default:
				}
			}
		});
	}
}
