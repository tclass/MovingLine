package de.movingLine.game.holder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import de.movingLine.game.entities.Coordinate;

public class BonusHolder {

	
	private final int RANDOMNESS = 5;
	
	private int maxHeight;
	private int maxWidth;

	private List<Coordinate> boni = new ArrayList<Coordinate>();

	// We need the MaxHeight and maxWidth so that we're not generate Boni that
	// no one can see
	public BonusHolder(int maxHeight, int maxWidth) {

		this.maxHeight = maxHeight;
		this.maxWidth = maxWidth;
	}

	/**
	 * The BonusHolder doesn't generate everytime a new Boni, sometimes here
	 * generates sometimes not
	 */
	public void generateBoni() {

		Random randomGenerator = new Random(System.currentTimeMillis());

		int shouldGenerate = randomGenerator.nextInt(RANDOMNESS);

		if (shouldGenerate == 1) {
			System.out.println("Generate Bonus");
			int randomY = randomGenerator.nextInt(maxHeight-30);
			int randomX = randomGenerator.nextInt(maxWidth-30);

			Coordinate c = new Coordinate(randomX, randomY);

			boni.add(c);
		}
	}

	public Iterator<Coordinate> getIterator() {
		
		return boni.iterator();
	}
}
