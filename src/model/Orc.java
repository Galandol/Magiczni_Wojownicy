package model;

public class Orc extends Humanoid {

	private Weapon weapon;

	public Orc(String name, int hitPoints, int strength, Weapon weapon) {
		setName(name);
		setHitPoints(hitPoints);
		setStrength(strength);
		setWeapon(weapon);
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

}
