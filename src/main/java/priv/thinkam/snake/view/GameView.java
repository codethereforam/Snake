package priv.thinkam.snake.view;

import priv.thinkam.snake.model.Food;
import priv.thinkam.snake.model.Snake;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.TimeUnit;

/**
 * 游戏视图
 *
 * @author thinkam
 * @date 2018/02/16
 */
public class GameView extends Frame {
	private static final int COORDINATE_X = 200;
	private static final int COORDINATE_Y = 100;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private static final Color BACKGROUND_COLOR = Color.CYAN;
	private static final String TITLE = "Snake - by thinkam";
	private static final int TIMEOUT = 5;

	private Image offScreenImage = null;

	private Snake snake;
	private Food food;

	public GameView(Snake snake, Food food) throws HeadlessException {
		this.snake = snake;
		this.food = food;

		init();
	}


	@Override
	public void paint(Graphics g) {
		snake.draw(g);
		snake.move();

		food.draw(g);
	}

	private void startRepaintThread() {
		new Thread(() -> {
			while (true) {
				repaint();
				try {
					TimeUnit.MILLISECONDS.sleep(TIMEOUT);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void init() {
		setBounds(COORDINATE_X, COORDINATE_Y, WIDTH, HEIGHT);
		setBackground(BACKGROUND_COLOR);
		setTitle(TITLE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setResizable(false);
		setVisible(true);

		startRepaintThread();
	}

	/**
	 * 双缓冲消除闪烁
	 *
	 * @param g 画笔
	 */
	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(WIDTH, HEIGHT);
		} else {
			Graphics gOffScreen = offScreenImage.getGraphics();
			Color color = gOffScreen.getColor();
			gOffScreen.setColor(BACKGROUND_COLOR);
			gOffScreen.fillRect(0, 0, WIDTH, HEIGHT);
			gOffScreen.setColor(color);
			paint(gOffScreen);
			g.drawImage(offScreenImage, 0, 0, null);
		}
	}

}
