package model;

public abstract class Human extends Humanoid {

	
	public Human(int hitPoints, int strength, String name, double speed, Weapon weapon, Armor armor) {
		super(hitPoints, strength, name, speed, weapon, armor);
	}
	
	public Human(int hitPoints, int strength, String name, double speed, Weapon weapon) {
		super(hitPoints, strength, name, speed, weapon);
	}
	
	public Human(){
		
	}
	
	

}
