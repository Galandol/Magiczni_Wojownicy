package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.Human;
import model.Orc;
import model.Sword;
import model.Warrior;

import database.DatabaseManager;

public class CharacterPreparation {

	 DatabaseManager db = DatabaseManager.getInstance();

	public ArrayList<Sword> listOfSwords = db.getItems();
	public ArrayList<Human> listofHeros = db.getHeros();
	public ArrayList<Orc> listOfEnemies = db.getEnemies();

	public Warrior createHero() {

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
		for (Sword s : listOfSwords) {
			System.out.println("[" + weaponId + "] " + s);
			weaponId++;
		}
		weaponId = reader.nextInt();

		reader.close();

		Warrior createdWarrior = new Warrior(hitPoints, strength, name, speed, listOfSwords.get(weaponId - 1));

		listofHeros.add(createdWarrior);

		return createdWarrior;
	}

	// private ArrayList<Human> createListOfHeros() {
	// ArrayList<Human> listofHeros = new ArrayList<Human>();
	//
	// Warrior warrior = new Warrior(50, 5, "--Andrzej", 1.7,
	// listOfSwords.get(1));
	// listofHeros.add(warrior);
	// Warrior warrior2 = new Warrior(60, 6, "Karol", 1.3, listOfSwords.get(0));
	// listofHeros.add(warrior2);
	//
	// return listofHeros;
	// }

	// private ArrayList<Sword> createListOfWeapons() {
	//
	// ArrayList<Sword> listOfWeapons = new ArrayList<Sword>();
	//
	// Sword sword = new Sword(4, "Miecz", 10, 1, 1.2);
	// listOfWeapons.add(sword);
	// Sword sword2 = new Sword(5, "Lepszy miecz", 10, 1, 1.3);
	// listOfWeapons.add(sword2);
	//
	// return listOfWeapons;
	//
	// }

	// private ArrayList<Orc> createListOfEnemies() {
	// ArrayList<Orc> listOfEnemies = new ArrayList<Orc>();
	//
	// Orc orc = new Orc(50, 12, "Z³y ork", 1.2, listOfSwords.get(0));
	// listOfEnemies.add(orc);
	// Orc orc2 = new Orc(60, 15, "Ork szef", 1.2, listOfSwords.get(1));
	// listOfEnemies.add(orc2);
	//
	// return listOfEnemies;
	//
	// }

	public Human chooseHero() {
		int choice;
		Human chosenHero = null;
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
			for (Human h : listofHeros) {
				System.out.println("[" + heroId + "] " + h);
				heroId++;
			}
			heroId = reader.nextInt();

			chosenHero = listofHeros.get(heroId - 1);
			break;
		}

		return chosenHero;
	}

	public Orc chooseEnemy() {
		Orc chosenEnemy = null;

		System.out.println("Wybierz przeciwnika do walki!");

		int enemyId = 1;
		for (Orc o : listOfEnemies) {
			System.out.println("[" + enemyId + "] " + o);
			enemyId++;
		}
		Scanner reader = new Scanner(System.in);
		enemyId = reader.nextInt();

		chosenEnemy = listOfEnemies.get(enemyId - 1);

		return chosenEnemy;
	}

}
