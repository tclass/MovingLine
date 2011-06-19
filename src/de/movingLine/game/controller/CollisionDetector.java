package de.movingLine.game.controller;

import java.util.List;

import de.movingLine.game.entities.Bonus;
import de.movingLine.game.entities.Coordinate;
import de.movingLine.game.entities.Snake;
import de.movingLine.game.holder.BonusHolder;

/**
 * @author Royalclass This CollisionDetector detects various Collisions,snake
 *         with screen, snake with snake, snake with Bonus and so on
 */
public class CollisionDetector {

	private Snake snake;
	private BonusHolder bonusHolder;
	private int maxWidth, maxHeight;

	public CollisionDetector(int maxWidth, int maxHeight, Snake snake,BonusHolder bonusHolder) {
		this.maxHeight = maxHeight;
		this.maxWidth = maxWidth;
		this.snake = snake;
		this.bonusHolder = bonusHolder;
	}

	public boolean detect() {

		bonusCollision();
		return screenEndDetection();
	}

	/**
	 * If it is a bonus Collision you get a point and the snake grows
	 */
	private void bonusCollision() {
		List<Bonus> boni = bonusHolder.getBoni();
		List<Coordinate> snakeC = snake.getSnake();
		
		
		for (int i = 0; i < snakeC.size(); i++) {
			
			for (int j = 0; j < boni.size(); j++) {
				if(boni.get(j).getX() == snakeC.get(i).getX() && boni.get(j).getY() == snakeC.get(i).getY()){
					bonusHolder.catchedBonus(boni.get(j));
					snake.addTile();
					break;
				}
			}
		}
	}

	/**
	 * checks if the snake head is crashing into a wall(screen end)
	 */
	private boolean screenEndDetection() {

		if (snake.getSnake().get(0).getX() < 0) {
			System.out.println("left crash");
			return true;
		}

		if (snake.getSnake().get(0).getY() < 0) {
			System.out.println("up crash");
			return true;
		}

		if (snake.getSnake().get(0).getX() > (maxWidth - 30)) {
			System.out.println("right crash");
			return true;
		}

		if (snake.getSnake().get(0).getY() > (maxHeight - 30)) {
			System.out.println("crash down");
			return true;
		}
		return false;
	}
}
