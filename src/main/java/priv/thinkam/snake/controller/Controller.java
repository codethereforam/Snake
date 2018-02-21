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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 控制器
 *
 * @author thinkam
 * @date 2018/02/16
 */
public class Controller {
	/**
	 * 重画线程睡眠时间(ms)
	 */
	private static final int TIMEOUT = 3;
	/**
	 * 每吃一个食物增加的分数
	 */
	private static final int SCORE_PER_FOOD = 10;

	private ExecutorService threadPool = Executors.newSingleThreadExecutor();

	private Food food;
	private Snake snake;
	private GameView view;
	private boolean running;
	private int score;

	private Controller() {
		initView();
	}

	public static void main(String[] args) {
		new Controller().launch();
	}

	private void initView() {
		view = new GameView();
		view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		view.addKeyListener(new KeyMonitor());
	}

	/**
	 * 结束游戏
	 */
	private void gameOver() {
		running = false;
		int option = JOptionPane.showConfirmDialog(view, "重新游戏", "得分：" + score, JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			this.restart();
		} else {
			System.exit(0);
		}
	}

	private void restart() {
		resetScore();
		this.launch();
		view.repaint();
	}

	/**
	 * 启动游戏
	 */
	private void launch() {
		food = new Food();
		snake = new Snake();
		makeFoodNotIntersectSnake();
		view.setSnakeAndFood(snake, food);
	}

	/**
	 * 保证蛇和食物不相交
	 */
	private void makeFoodNotIntersectSnake() {
		do {
			food.randomLocation();
		} while (snake.intersects(food));
	}

	/**
	 * 开启重画线程
	 */
	private void startRepaintThread() {
		threadPool.execute(() -> {
			while (running) {
				snake.move();
				//当蛇移动一个身位时，判断死亡，吃食物，改变方向， 取消蛇头方向锁定
				if (snake.getStep() % (SnakeSection.LENGTH / SnakeSection.STEP_LENGTH) == 0) {
					if (snake.isDead()) {
						this.gameOver();
						continue;
					}
					boolean success = snake.canEatFood(food);
					if (success) {
						snake.createTailSection();
						makeFoodNotIntersectSnake();
						addScore();
					}
					snake.changeDirection();
					snake.cancelLockHeadDirection();
					snake.resetStepToZero();
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

	private void addScore() {
		this.score += SCORE_PER_FOOD;
		view.setTitle(GameView.BASE_TITLE + score);
	}

	private void resetScore() {
		this.score = 0;
		view.setTitle(GameView.BASE_TITLE + score);
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
