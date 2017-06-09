package controller;

import database.DatabaseManager;

//import java.util.Random;
// import java.util.Scanner;

//import java.util.concurrent.ThreadLocalRandom;
import model.*; //<--- wiem, ¿e to s³abe rozwi¹zanie
//TODO: porobiæ pakiety w model, dziel¹ce klasy.
//TODO: stowrzyæ obiekt z fightResult, zamiast tablicy.

public class Main {

	public static void main(String[] args) {

		// Scanner reader = new Scanner(System.in); // Reading from System.in
		// System.out.println("Enter a number: ");
		// int n = reader.nextInt(); // Scans the next token of the input as an
		// int.
		// System.out.println(n);

		Sword sword = new Sword(4, "Miecz", 10, 1, 1.2);
		Sword sword2 = new Sword(5, "Lepszy miecz", 10, 1, 1.3);

		Warrior warrior = new Warrior(50, 5, "--Andrzej", 1.7, sword);

		Orc orc = new Orc("++Z³y ork", 50, 3, sword2);

		Arena arena = new Arena();

		// arena.fight(warrior, orc);

		//DatabaseManager db = DatabaseManager.getInstance();

		//System.out.println(db.getCreaturesCount());
		
		//System.out.println(warrior);

	}
}
