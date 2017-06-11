package model;

public class Sword extends Weapon {

	public Sword(int weight, String name, int maxDamage, int minDamage, double speed) {
		super(weight, name, maxDamage, minDamage, speed);
	}

	@Override
	public String toString() {
		return super.getName();
	}

}
