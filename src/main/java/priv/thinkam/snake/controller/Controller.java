package priv.thinkam.snake.controller;

import priv.thinkam.snake.common.DirectionEnum;
import priv.thinkam.snake.model.Food;
import priv.thinkam.snake.model.Snake;
import priv.thinkam.snake.view.GameView;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

	/**
	 * 启动游戏
	 */
	private void launch() {
		Food food = new Food();
		Snake snake = new Snake(food);

		GameView view = new GameView(snake, food);
		view.startRepaintThread();
		view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		view.addKeyListener(new KeyMonitor(snake));
	}

	private static class KeyMonitor extends KeyAdapter {
		private Snake snake;

		KeyMonitor(Snake snake) {
			this.snake = snake;
		}

		@Override
		public void keyPressed(KeyEvent e) {
			DirectionEnum headDirection = snake.getHeadDirection();
			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					if (headDirection != DirectionEnum.DOWN && headDirection != DirectionEnum.UP) {
						snake.setHeadDirection(DirectionEnum.UP);
					}
					break;
				case KeyEvent.VK_DOWN:
					if (headDirection != DirectionEnum.DOWN && headDirection != DirectionEnum.UP) {
						snake.setHeadDirection(DirectionEnum.DOWN);
					}
					break;
				case KeyEvent.VK_LEFT:
					if (headDirection != DirectionEnum.LEFT && headDirection != DirectionEnum.RIGHT) {
						snake.setHeadDirection(DirectionEnum.LEFT);
					}
					break;
				case KeyEvent.VK_RIGHT:
					if (headDirection != DirectionEnum.LEFT && headDirection != DirectionEnum.RIGHT) {
						snake.setHeadDirection(DirectionEnum.RIGHT);
					}
					break;
				default:
			}
		}
	}
}
