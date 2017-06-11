package controller;

import java.util.List;
import java.util.Scanner;

import model.creatures.HumanoidType;
import model.creatures.Humanoid;
import model.creatures.Warrior;

import model.items.Weapon;
import database.DatabaseManager;

public class CharacterPreparation {

	DatabaseManager db = DatabaseManager.getInstance();

	Scanner reader = new Scanner(System.in);

	public List<Weapon> listOfSwords = db.getItems();
	public List<Humanoid> listofHeros = db.getHumanoids(HumanoidType.Human);
	public List<Humanoid> listOfEnemies = db.getHumanoids(HumanoidType.Orc);

	public Humanoid createHero() {

		Scanner reader = new Scanner(System.in);
		System.out.println("Enter your character name ");
		String name = reader.nextLine();
		System.out.println("Enter your character hit points ");
		int hitPoints = reader.nextInt();
		System.out.println("Enter your character strength ");
		int strength = reader.nextInt();
		System.out.println("Enter your character speed ");
		int speed = reader.nextInt();
		System.out.println("Choose your character weapon ");
		int weaponId = 1;
		for (Weapon w : listOfSwords) {
			System.out.println("[" + weaponId + "] " + w);
			weaponId++;
		}
		weaponId = reader.nextInt();

		Humanoid createdWarrior = new Warrior(hitPoints, strength, name, speed, listOfSwords.get(weaponId - 1));

		listofHeros.add(createdWarrior);

		return createdWarrior;
	}

	public Humanoid chooseHero() {
		int choice;
		Humanoid chosenHero = null;
		System.out.println("Twoja postaæ: ");
		System.out.println("[1] Tworzê now¹ postaæ ");
		System.out.println("[2] Wybieram gotow¹ postaæ ");
		Scanner reader = new Scanner(System.in);
		choice = reader.nextInt();
		switch (choice) {
		case 1:
			chosenHero = createHero();
			break;
		case 2:
			System.out.println("Dostêpni bohaterowie: ");
			int heroId = 1;
			for (Humanoid h : listofHeros) {
				System.out.println("[" + heroId + "] " + h);
				heroId++;
			}
			heroId = reader.nextInt();

			chosenHero = listofHeros.get(heroId - 1);
			break;
		}
		return chosenHero;
	}

	public Humanoid chooseEnemy() {
		Humanoid chosenEnemy = null;

		System.out.println("Wybierz przeciwnika do walki!");

		int enemyId = 1;
		for (Humanoid o : listOfEnemies) {
			System.out.println("[" + enemyId + "] " + o);
			enemyId++;
		}
		Scanner reader = new Scanner(System.in);
		enemyId = reader.nextInt();
		chosenEnemy = listOfEnemies.get(enemyId - 1);
		// chosenEnemy = listOfEnemies.get(0);

		return chosenEnemy;
	}

}
