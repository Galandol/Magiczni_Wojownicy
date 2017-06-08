package model;

public abstract class Humanoid extends Creature {

	private Weapon weapon;
	private Armor armor;

	public Humanoid() {
		super();
	}

	public Humanoid(int hitPoints, int strength, String name, double speed, Weapon weapon, Armor armor) {
		super(hitPoints, strength, name, speed);
		this.weapon = weapon;
		this.armor = armor;

	}
	public Humanoid(int hitPoints, int strength, String name, double speed, Weapon weapon) {
		super(hitPoints, strength, name, speed);
		this.weapon = weapon;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Armor getArmor() {
		return armor;
	}

	public void serArmor(Armor armor) {
		this.armor = armor;
	}
}
