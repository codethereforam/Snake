package priv.thinkam.snake.model;

import priv.thinkam.snake.common.AbstractSnake;
import priv.thinkam.snake.common.DirectionEnum;
import priv.thinkam.snake.common.SnakeSectionColorEnum;
import priv.thinkam.snake.view.GameView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 蛇
 *
 * @author thinkam
 * @date 2018/02/16
 */
public class Snake extends AbstractSnake {
	private static final int INIT_BODY_SECTION_NUM = 2;

	private List<SnakeSection> sections = new ArrayList<>();
	private int step;
	private DirectionEnum headDirection;
	private boolean headDirectionLocked;

	public Snake() {
		init();
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public boolean isHeadDirectionLocked() {
		return headDirectionLocked;
	}

	public void lockHeadDirection() {
		this.headDirectionLocked = true;
	}

	public void cancelLockHeadDirection() {
		this.headDirectionLocked = false;
	}

	private void init() {
		//创建蛇头
		SnakeSection head = new SnakeSection(400, 200, DirectionEnum.RIGHT, SnakeSectionColorEnum.HEAD_COLOR.getColor());
		this.addSection(head);
		this.setHeadDirection(head.getDirection());
		//创建蛇身
		for (int i = 0; i < INIT_BODY_SECTION_NUM; i++) {
			createTailSection();
		}
	}

	private void addSection(SnakeSection snakeSection) {
		sections.add(snakeSection);
	}

	public SnakeSection getHead() {
		return sections.get(0);
	}

	private SnakeSection getTail() {
		return sections.get(sections.size() - 1);
	}

	public DirectionEnum getHeadDirection() {
		return headDirection;
	}

	public void setHeadDirection(DirectionEnum headDirection) {
		this.headDirection = headDirection;
	}

	@Override
	public void move() {
		//蛇的每节移动一步
		sections.forEach(SnakeSection::move);
		step++;
	}

	/**
	 * 判断蛇是否死亡
	 *
	 * @return true: dead; false: alive
	 */
	public boolean isDead() {
		SnakeSection head = this.getHead();
		return isOutOfBoundary(head) || isHeadHitSelf();
	}

	/**
	 * 判断蛇的一节是否出界
	 *
	 * @param section 蛇的一节
	 * @return true: 出界
	 */
	private boolean isOutOfBoundary(SnakeSection section) {
		return section.getCoordinateX() < 0 ||
				section.getCoordinateY() < 0 ||
				(section.getCoordinateX() + SnakeSection.LENGTH) > GameView.WIDTH ||
				(section.getCoordinateY() + SnakeSection.LENGTH) > GameView.HEIGHT;
	}

	/**
	 * 判断蛇头是否撞击自己的身体
	 *
	 * @return true: 撞到
	 */
	private boolean isHeadHitSelf() {
		for (int i = 4; i < sections.size(); i++) {
			if (this.getHead().getRectangle().intersects(sections.get(i).getRectangle())) {
				return true;
			}
		}
		return false;
	}

	public void changeDirection() {
		/*
		 * 改变蛇身方向
		 * 除蛇头外，蛇的每节方向变为前一节的方向
		 */
		for (int i = sections.size() - 1; i > 0; i--) {
			sections.get(i).setDirection(sections.get(i - 1).getDirection());
		}
		//改变蛇头方向
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
	 * @return true: 吃到食物
	 */
	public boolean eatFood(Food food) {
		if (getHead().getRectangle().intersects(food.getRectangle())) {
			createTailSection();
			food.resetLocation();
			return true;
		}
		return false;
	}

	/**
	 * 在蛇的尾部创建一节，并且方向和前一节一致
	 */
	private void createTailSection() {
		SnakeSection tail = this.getTail();
		SnakeSection section = null;
		DirectionEnum tailDirection = tail.getDirection();
		Color tailColor = SnakeSectionColorEnum.BODY_COLOR.getColor();
		switch (tailDirection) {
			case UP:
				section = new SnakeSection(tail.getCoordinateX(), tail.getCoordinateY() + SnakeSection.LENGTH,
						tailDirection, tailColor);
				break;
			case DOWN:
				section = new SnakeSection(tail.getCoordinateX(), tail.getCoordinateY() - SnakeSection.LENGTH,
						tailDirection, tailColor);
				break;
			case LEFT:
				section = new SnakeSection(tail.getCoordinateX() + SnakeSection.LENGTH, tail.getCoordinateY(),
						tailDirection, tailColor);
				break;
			case RIGHT:
				section = new SnakeSection(tail.getCoordinateX() - SnakeSection.LENGTH, tail.getCoordinateY(),
						tailDirection, tailColor);
				break;
			default:
		}
		sections.add(section);
	}
}
