package model.items;

public class Axe extends Weapon {

	public Axe(int weight, String name, int maxDamage, int minDamage, double speed) {
		super(weight, name, maxDamage, minDamage, speed);
	}

	@Override
	public String toString() {
		return super.getName();
	}
}
