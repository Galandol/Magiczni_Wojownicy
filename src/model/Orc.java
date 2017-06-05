package model;

public class Orc extends Monster {
	
	private Weapon weapon;

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Orc(String name, int hitPoints, int strength, Weapon weapon){
		setName(name);
		setHitPoints(hitPoints);
		setStrength(strength);
		setWeapon(weapon);
		
	}
}
