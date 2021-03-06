package model.creatures;

import model.items.Armor;
import model.items.Weapon;

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

	public String toString() {
		return super.toString() + ", Bro�: " + weapon.getName();
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
