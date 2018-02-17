package priv.thinkam.snake.model;

import priv.thinkam.snake.common.AbstractSnake;
import priv.thinkam.snake.common.Direction;
import priv.thinkam.snake.view.GameView;

import java.awt.*;
import java.util.LinkedList;

/**
 * 蛇
 *
 * @author thinkam
 * @date 2018/02/16
 */
public class Snake extends AbstractSnake {
	private LinkedList<SnakeSection> sections = new LinkedList<>();
	private Food food;
	private int step = 0;
	private Direction headDirection;

	public Snake() {
		init();
	}

	public Snake(Food food) {
		this();
		this.food = food;
	}

	private void init() {
		SnakeSection head = new SnakeSection();
		head.setColor(Color.RED);
		this.addSection(head);
		this.setHeadDirection(head.getDirection());

		createTailSection();
		createTailSection();
	}


	private void addSection(SnakeSection snakeSection) {
		sections.add(snakeSection);
	}

	private SnakeSection getHead() {
		return sections.peekFirst();
	}

	public Direction getHeadDirection() {
		return headDirection;
	}

	public void setHeadDirection(Direction headDirection) {
		this.headDirection = headDirection;
	}

	@Override
	public void move() {
		if ((step++) % (SnakeSection.LENGTH / SnakeSection.STEP_LENGTH) == 0) {
			if (isDead()) {
				System.exit(0);
			}
			eatFood(food);
			changeDirection();
		}
		sections.forEach(SnakeSection::move);
	}

	private boolean isDead() {
		SnakeSection head = this.getHead();
		return isOutOfBoundary(head) || isHitSelf();
	}

	private boolean isOutOfBoundary(SnakeSection section) {
		return section.getCoordinateX() < 0 ||
				section.getCoordinateY() < 0 ||
				(section.getCoordinateX() + SnakeSection.LENGTH) > GameView.WIDTH ||
				(section.getCoordinateY() + SnakeSection.LENGTH) > GameView.HEIGHT;
	}

	private boolean isHitSelf() {
		for (int i = 4; i < sections.size(); i++) {
			if (this.getHead().getRectangle().intersects(sections.get(i).getRectangle())) {
				return true;
			}
		}
		return false;
	}

	private void changeDirection() {
		//change body direction
		for (int i = sections.size() - 1; i > 0; i--) {
			sections.get(i).setDirection(sections.get(i - 1).getDirection());
		}
		//change head direction
		this.getHead().setDirection(headDirection);
	}

	@Override
	public void draw(Graphics g) {
		for (SnakeSection section : sections) {
			section.draw(g);
		}
	}

	/**
	 * 吃食物
	 *
	 * @param food 食物
	 */
	private void eatFood(Food food) {
		if (getHead().getRectangle().intersects(food.getRectangle())) {
			createTailSection();
			food.resetLocation();
		}
	}

	/**
	 * 在蛇的尾部创建一节
	 */
	private void createTailSection() {
		SnakeSection tail = sections.peekLast();
		SnakeSection section = new SnakeSection();
		Direction tailDirection = tail.getDirection();
		switch (tailDirection) {
			case UP:
				section = new SnakeSection(tail.getCoordinateX(), tail.getCoordinateY() + SnakeSection.LENGTH,
						tailDirection);
				break;
			case DOWN:
				section = new SnakeSection(tail.getCoordinateX(), tail.getCoordinateY() - SnakeSection.LENGTH,
						tailDirection);
				break;
			case LEFT:
				section = new SnakeSection(tail.getCoordinateX() + SnakeSection.LENGTH, tail.getCoordinateY(),
						tailDirection);
				break;
			case RIGHT:
				section = new SnakeSection(tail.getCoordinateX() - SnakeSection.LENGTH, tail.getCoordinateY(),
						tailDirection);
				break;
			default:
		}
		sections.add(section);
	}
}
