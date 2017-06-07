package controller;

import java.util.Random;
// import java.util.Scanner;

//import java.util.concurrent.ThreadLocalRandom;
import model.*; //<--- wiem, �e to s�abe rozwi�zanie
//TODO: porobi� pakiety w model, dziel�ce klasy.
//TODO: przenie�� metod� walki do innej klasy, np. Arena i stworzy� jej obiekt na kt�rej b�dzie wywo�ywana walka 
//TODO: zmieni� metody na prywatne
//TODO: przypisa� do zminnej lokalnej w funkcjach, zmiast get�w().
//TODO: pomy�le�, �eby while iterowa� ka�dy atak, a nie rund� walki
//TESTTEST

public class Main {

	public static void main(String[] args) {

		// Scanner reader = new Scanner(System.in); // Reading from System.in
		// System.out.println("Enter a number: ");
		// int n = reader.nextInt(); // Scans the next token of the input as an
		// int.
		// System.out.println(n);

		Sword sword = new Sword(4, "Miecz", 10, 5, 1.7);
		Sword sword2 = new Sword(5, "Lepszy miecz", 12, 6, 1.5);

		Warrior warrior = new Warrior(50, 5, "Andrzej", 1.7, sword);

		Orc orc = new Orc("Z�y ork", 50, 3, sword2);

		fight(warrior, orc);

	}

	public static void fight(Human hero, Orc enemy) {

		int roundIndex = 0;
		double currentHeroSpeed = 0;
		double currentEnemySpeed = 0;
		int enemyHP = enemy.getHitPoints(); // <---- czy tak trzeba cachowa�?
		int heroHP = hero.getHitPoints(); // <---- czy tak trzeba cachowa�?

		while (heroHP > 0 && enemyHP > 0) {

			int heroRoundDamage = countRoundDamage(hero);
			int enemyRoundDamage = countRoundDamage(enemy);

			currentHeroSpeed += hero.getWeapon().getSpeed();
			currentEnemySpeed += enemy.getWeapon().getSpeed();

			roundIndex++;

			System.out.println("TURA: " + roundIndex);

			if (currentHeroSpeed >= currentEnemySpeed) {
				// atak bohatera
				Attack(hero.getName(), enemy.getName(), heroRoundDamage);
				currentHeroSpeed -= 1;
				enemyHP -= heroRoundDamage;

				if (enemyHP <= 0) {
					printWinSummary(hero.getName(), heroHP);
					break;
				}
				printEnemyHP(enemy.getName(), enemyHP);
				if (currentHeroSpeed >= 1) {
					ifExtraAttack(hero, enemy.getName());
					enemyHP -= heroRoundDamage;
					currentHeroSpeed -= 1;
					if (enemyHP <= 0) {
						printWinSummary(hero.getName(), heroHP);
						break;
					}
					printEnemyHP(enemy.getName(), enemyHP);
				}

				// atak przeciwnika
				Attack(enemy.getName(), hero.getName(), enemyRoundDamage);
				heroHP -= enemyRoundDamage;
				currentEnemySpeed -= 1;

				if (heroHP <= 0) {
					printLossSummary(hero.getName(), enemy.getName(), enemyHP);
					break;
				}
				printHeroHP(hero.getName(), heroHP);
				if (currentEnemySpeed >= 1) {
					ifExtraAttack(enemy, hero.getName());
					heroHP -= enemyRoundDamage;
					currentEnemySpeed -= 1;
					if (heroHP <= 0) {
						printLossSummary(hero.getName(), enemy.getName(), enemyHP);
						break;
					}
					printHeroHP(hero.getName(), heroHP);
				}

			} else {
				// atak przeciwnika
				Attack(enemy.getName(), hero.getName(), enemyRoundDamage);
				heroHP -= enemyRoundDamage;
				currentEnemySpeed -= 1;

				if (heroHP <= 0) {
					printLossSummary(hero.getName(), enemy.getName(), enemyHP);
					break;
				}
				printHeroHP(hero.getName(), heroHP);

				if (currentEnemySpeed >= 1) {
					ifExtraAttack(enemy, hero.getName());
					heroHP -= enemyRoundDamage;
					currentEnemySpeed -= 1;
					if (heroHP <= 0) {
						printLossSummary(hero.getName(), enemy.getName(), enemyHP);
						break;
					}
					printHeroHP(hero.getName(), heroHP);
				}
				// atak bohatera
				Attack(hero.getName(), enemy.getName(), heroRoundDamage);
				enemyHP -= heroRoundDamage;
				currentHeroSpeed = currentHeroSpeed - 1;

				if (enemyHP <= 0) {
					printWinSummary(hero.getName(), heroHP);
					break;
				}
				printEnemyHP(enemy.getName(), enemyHP);
				if (currentHeroSpeed >= 1) {
					ifExtraAttack(hero, enemy.getName());
					enemyHP -= heroRoundDamage;
					currentHeroSpeed -= 1;
					if (enemyHP <= 0) {
						printWinSummary(hero.getName(), heroHP);
						break;
					}
					printEnemyHP(enemy.getName(), enemyHP);
				}
			}
		}
	}

	// metoda do obliczania obra�e� zadanych przez bohatera w danej rundzie
	// walki
	public static int countRoundDamage(Human human) {
		Random ran = new Random();
		int randomNum = ran.nextInt(human.getWeapon().getMinDamage() + 1) + human.getWeapon().getMinDamage();
		// System.out.println(randomNum);
		return randomNum;
	}

	// to si� robi za pomoc�: /**
	/**
	 * metoda do obliczania obra�e� zadanych przez przeciwnika w danej rundzie
	 * walki
	 * 
	 * @param orc
	 *            obiekt Orka
	 * @return
	 * 
	 */
	public static int countRoundDamage(Orc orc) {
		Random ran = new Random();
		int randomNum = ran.nextInt(orc.getWeapon().getMinDamage() + 1) + orc.getWeapon().getMinDamage();
		// System.out.println(randomNum);
		return randomNum;
	}

	// metoda do wykonania ataku przez bohatera i przeciwnika
	public static void Attack(String characterName, String foeName, int damageDone) {
		System.out.println(characterName + " atakuje " + foeName + " - obra�enia: " + damageDone);
	}

	// metoda do wypisania dodatkowego ataku
	public static void printExtraAttack(String characterName) {
		System.out.println(characterName + " wyprowadza dodatkowy atak!");
	}

	// metoda do sprawdzenia czy wyst�puje dodatkowy atak u orka
	public static void ifExtraAttack(Human attacker, String foesName) {
		int attackerRoundDamage = countRoundDamage(attacker);
		printExtraAttack(attacker.getName());
		Attack(attacker.getName(), foesName, attackerRoundDamage);
	}

	// metoda do sprawdzenia czy wyst�puje dodatkowy atak u cz�owieka
	public static void ifExtraAttack(Orc attacker, String foesName) {
		int attackerRoundDamage = countRoundDamage(attacker);
		printExtraAttack(attacker.getName());
		Attack(attacker.getName(), foesName, attackerRoundDamage);
	}

	// metoda do wypisywania pozosta�ego HP przeciwnika.
	public static void printEnemyHP(String enemyName, int enemyHP) {
		System.out.println(enemyName + " wci�� ma " + enemyHP + " hp.");
	}

	// metoda do wypisywania pozosta�ego HP bohatera.
	public static void printHeroHP(String heroName, int heroHP) {
		System.out.println(heroName + " ma jeszcze " + heroHP + " hp.");
	}

	// metoda do wypisania podsumowania zwyci�stwa
	public static void printWinSummary(String heroName, int heroHP) {
		System.out.println(heroName + " wychodzi zwyci�sko ze starcia! Pozosta�o hp: " + heroHP);
	}

	// metoda do wypisania podsumowania pora�ki
	public static void printLossSummary(String heroName, String enemyName, int enemyHP) {
		System.out.println(heroName + " przegra� starcie, " + enemyName + " pozosta�o " + enemyHP + " hp.");
	}

}
