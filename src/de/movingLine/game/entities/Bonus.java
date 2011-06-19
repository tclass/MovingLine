package de.movingLine.game.entities;

public class Bonus extends Coordinate {
	
	long createTime;
	
	public Bonus(int x, int y, long createTime) {
		super(x,y);
		this.createTime = createTime;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
}
