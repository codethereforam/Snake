package priv.thinkam.snake;

import java.awt.*;
import java.util.LinkedList;

/**
 * @author thinkam
 * @date 2018/02/16
 */
public class Snake extends AbstractSnake {
	private LinkedList<SnakeSection> sections = new LinkedList<>();

	private Food food;

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
		createTailSection();
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public void addSection(SnakeSection snakeSection) {
		sections.add(snakeSection);
	}

	public LinkedList<SnakeSection> getSections() {
		return sections;
	}

	public SnakeSection getHead() {
		return sections.peekFirst();
	}

	@Override
	void move() {
		sections.forEach(SnakeSection::move);
		eatFood(food);

		for (int i = sections.size() - 1; i > 0; i--) {
			sections.get(i).setDirection(sections.get(i - 1).getDirection());
		}
	}

	@Override
	public void draw(Graphics g) {
		for (SnakeSection section : sections) {
			section.draw(g);
		}
	}

	private boolean eatFood(Food food) {
		if (getHead().getRectangle().intersects(food.getRectangle())) {
			createTailSection();
			return true;
		}
		return true;
	}

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
