package model.items;

public abstract class Weapon extends Item {

	private int maxDamage;
	private int minDamage;
	private double speed;

	public Weapon(int weight, String name, int maxDamage, int minDamage, double speed) {
		super(weight, name);
		this.maxDamage = maxDamage;
		this.minDamage = minDamage;
		this.speed = speed;
	}

	public Weapon() {
		super();
	}

	public int getMaxDamage() {
		return maxDamage;
	}

	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	public int getMinDamage() {
		return minDamage;
	}

	public void setMinDamage(int minDamage) {
		this.minDamage = minDamage;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
