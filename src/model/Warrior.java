package model;

public class Warrior extends Human {

	public Warrior(int hitPoints, int strength, String name, double speed, Weapon weapon, Armor armor) {
		super(hitPoints, strength, name, speed, weapon, armor);
	}

	public Warrior(int hitPoints, int strength, String name, double speed, Weapon weapon) {
		super(hitPoints, strength, name, speed, weapon);
	}

}
