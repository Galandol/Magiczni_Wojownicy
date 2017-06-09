package model;

public abstract class Creature {

	private int hitPoints;
	private int strength;
	private String name;
	private double speed;

	public Creature() {
		super();
	}

	public Creature(int hitPoints, int strength, String name, double speed) {
		this.hitPoints = hitPoints;
		this.strength = strength;
		this.name = name;
		this.speed = speed;
	}

	@Override
	public String toString() {
		return "Nazwa: " + name + ", HP: " + hitPoints;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

}
