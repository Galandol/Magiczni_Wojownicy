package model;

public abstract class Human extends Creature {

	private Weapon weapon;
	private Armor armor;

	public Human(int hitPoints, int strength, String name, double speed, Weapon weapon, Armor armor) {
		super(hitPoints, strength, name, speed);
		this.weapon = weapon;
		this.armor = armor;
	}

	public Human(int hitPoints, int strength, String name, double speed, Weapon weapon) {
		super(hitPoints, strength, name, speed);
		this.weapon = weapon;
	}
	
	public Human(){
		
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

	public void setArmor(Armor armor) {
		this.armor = armor;
	}
}
