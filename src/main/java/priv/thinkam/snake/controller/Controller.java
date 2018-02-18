package priv.thinkam.snake.controller;

import priv.thinkam.snake.common.DirectionEnum;
import priv.thinkam.snake.model.Food;
import priv.thinkam.snake.model.Snake;
import priv.thinkam.snake.model.SnakeSection;
import priv.thinkam.snake.view.GameView;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 控制器
 *
 * @author thinkam
 * @date 2018/02/16
 */
public class Controller {
	private static final int TIMEOUT = 5;

	private Food food;
	private Snake snake;
	private GameView view;
	private boolean running;

	public static void main(String[] args) {
		new Controller().launch();
	}

	/**
	 * 结束游戏
	 */
	private void terminate() {
		running = false;
		int option = JOptionPane.showConfirmDialog(view, "重新游戏", "结束", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			view.setVisible(false);
			this.launch();
		} else {
			System.exit(0);
		}
	}

	/**
	 * 启动游戏
	 */
	private void launch() {
		food = new Food();
		snake = new Snake();
		view = new GameView(snake, food);
		view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		view.addKeyListener(new KeyMonitor());
	}

	/**
	 * 开启重画线程
	 */
	private void startRepaintThread() {
		Executors.newSingleThreadExecutor().execute(() -> {
			while (running) {
				snake.move();
				//当蛇移动一个身位时，判断死亡，吃食物，改变方向， 取消蛇头方向锁定
				if (snake.getStep() % (SnakeSection.LENGTH / SnakeSection.STEP_LENGTH) == 0) {
					if (snake.isDead()) {
						this.terminate();
					}
					snake.eatFood(food);
					snake.changeDirection();
					snake.cancelLockHeadDirection();
					snake.setStep(0);
				}
				view.repaint();

				try {
					TimeUnit.MILLISECONDS.sleep(TIMEOUT);
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}
		});
	}

	/**
	 * 按键监听器
	 */
	private class KeyMonitor extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			DirectionEnum headDirection = snake.getHeadDirection();
			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					if (!snake.isHeadDirectionLocked() && headDirection != DirectionEnum.DOWN && headDirection !=
							DirectionEnum.UP) {
						snake.setHeadDirection(DirectionEnum.UP);
						snake.lockHeadDirection();
					}
					break;
				case KeyEvent.VK_DOWN:
					if (!snake.isHeadDirectionLocked() && headDirection != DirectionEnum.DOWN && headDirection !=
							DirectionEnum.UP) {
						snake.setHeadDirection(DirectionEnum.DOWN);
						snake.lockHeadDirection();
					}
					break;
				case KeyEvent.VK_LEFT:
					if (!snake.isHeadDirectionLocked() && headDirection != DirectionEnum.LEFT && headDirection !=
							DirectionEnum.RIGHT) {
						snake.setHeadDirection(DirectionEnum.LEFT);
						snake.lockHeadDirection();
					}
					break;
				case KeyEvent.VK_RIGHT:
					if (!snake.isHeadDirectionLocked() && headDirection != DirectionEnum.LEFT && headDirection !=
							DirectionEnum.RIGHT) {
						snake.setHeadDirection(DirectionEnum.RIGHT);
						snake.lockHeadDirection();
					}
					break;
				default:
			}

			if (!running) {
				snake.getHead().setDirection(snake.getHeadDirection());
				startRepaintThread();
				running = true;
			}
		}
	}
}
