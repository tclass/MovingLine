package de.movingLine.game.holder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.movingLine.game.entities.Bonus;
import de.movingLine.game.entities.Snake;
/**
 * @author Royalclass
 * The BonusHolder can generate Boni and stores them in a List
 */
public class BonusHolder {

	// Randomness (Zufaelligkeit) for creating Boni
	private static final int RANDOMNESS = 10;
	
	// Time to catch the Boni before it will be destroyed
	private static final int timeToCatch = 10000;

	private List<Bonus> boni = new ArrayList<Bonus>();
	
	private int score = 0;
	
	public BonusHolder() {}

	
	public void catchedBonus(Bonus bonus) {
		boni.remove(bonus);
		score++;
		System.out.println("NOW HIGHSCORE: "+score);
	}
	
	/**
	 * The BonusHolder doesn't generate everytime a new Boni, sometimes he
	 * generates sometimes not
	 */
	public void generateBoni() {

		Random randomGenerator = new Random(System.currentTimeMillis());

		int shouldGenerate = randomGenerator.nextInt(RANDOMNESS);

		if (shouldGenerate == 1) {
			System.out.println("Generate Bonus");
			// check how to automate the 15 and 23 maybe from getMesuearHeight and Width
			int randomX = (randomGenerator.nextInt(15)*Snake.snakeBodyPart);
			int randomY = (randomGenerator.nextInt(23)*Snake.snakeBodyPart);

			Bonus c = new Bonus(randomX, randomY, System.currentTimeMillis());

			boni.add(c);
		}
	}

	public void checkTimeToCatch() {
		
		long millis = System.currentTimeMillis();
		
		for (int i = 0; i < boni.size(); i++) {
			if((millis - boni.get(i).getCreateTime() ) > timeToCatch)
				boni.remove(i);
			
			else{
				break;
			}
		}
	}
	
	public List<Bonus> getBoni() {
		return boni;
	}
}
