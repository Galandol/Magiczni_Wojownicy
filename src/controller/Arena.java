package controller;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import model.*;

public class Arena {

	public boolean fight(Humanoid attacker, Humanoid attacked) {
		boolean didWon = true;
		;
		int roundIndex = 1;
		double attackersCurrentSpeed = 0;
		double attackedCurrentSpeed = 0;
		int attackerHP = attacker.getHitPoints();
		int attackedHP = attacked.getHitPoints();
		boolean ifAttackerAttacked = false;

		if (attacker.getWeapon().getSpeed() < attacked.getWeapon().getSpeed()) {
			ifAttackerAttacked = true;
		}

		while (attackerHP > 0 && attackedHP > 0) {

			if (ifAttackerAttacked) {
				attackedCurrentSpeed += attacked.getWeapon().getSpeed();
				System.out.println("--------------//--------------");
				System.out.println("Tura nr: " + roundIndex);
				roundIndex++;
			} else {
				attackersCurrentSpeed += attacker.getWeapon().getSpeed();

			}
			System.out.println("");
			if (attackersCurrentSpeed >= attackedCurrentSpeed) {
				roundResult result = attack(attacker, attacked, attackedHP, attackersCurrentSpeed);
				attackersCurrentSpeed = result.getAttackersCurrentSpeed();
				attackedHP = result.getEnemyHP();
				ifAttackerAttacked = true;
			} else {
				roundResult result = attack(attacked, attacker, attackerHP, attackedCurrentSpeed);
				attackedCurrentSpeed = result.getAttackersCurrentSpeed();
				attackerHP = result.getEnemyHP();
				ifAttackerAttacked = false;
			}
		}
		if (attackerHP <= 0) {
			System.out.println(attacker.getName() + " przegra³ starcie, " + attacked.getName() + " pozosta³o "
					+ attackedHP + " hp.");
			didWon = false;
		} else {
			System.out.println(attacker.getName() + " wychodzi zwyciêsko ze starcia! Pozosta³o hp: " + attackerHP);
		}
		return didWon;
	}

	private roundResult attack(Humanoid attacker, Humanoid attacked, int attackedHP, double attackersCurrentSpeed) {
		// TODO: pomyœleæ nad systemem pancerzy i uników.

		// w przysz³oœci dodaæ jeszcze parametr si³y.
		int minDamage = attacker.getWeapon().getMinDamage();
		// w przysz³oœci dodaæ jeszcze parametr si³y.
		int maxDamage = attacker.getWeapon().getMaxDamage();

		int damageDone = ThreadLocalRandom.current().nextInt(minDamage, maxDamage + 1);

		int HP = attackedHP; // trzeba bo inaczej rekurencja nie dzia³a
								// poprawnie
		roundResult result = new roundResult();

		System.out.println(attacker.getName() + " atakuje " + attacked.getName() + " - obra¿enia: " + damageDone);

		HP -= damageDone;

		System.out.println(attacked.getName() + " wci¹¿ ma " + HP + " hp.");

		attackersCurrentSpeed -= 1;
		if (attackersCurrentSpeed > 1 && HP > 0) {
			System.out.println(attacker.getName() + " wyprowadza dodatkowy atak!");
			// attackersCurrentSpeed -= 1;
			result = attack(attacker, attacked, HP, attackersCurrentSpeed);

		} else {

			result.setAttackersCurrentSpeed(attackersCurrentSpeed);
			result.setEnemyHP(HP);
		}

		return result;
	}

	public void start() {
		System.out.println("Witaj w grze Magiczni Wojownicy!");
		CharacterPreparation start = new CharacterPreparation();

		Human chosenHero = start.chooseHero();

		boolean didWon = fight(chosenHero, start.chooseEnemy());

		int winsAmount = 0;
		int lossesAmount = 0;

		while (didWon) {
			int choice;
			winsAmount++;
			System.out.println("");
			System.out.println("Liczba zwyciêstw: " + winsAmount + "!");
			System.out.println("Co dalej? ");
			System.out.println("[1] Wybierz kolejnego przeciwnika");
			System.out.println("[2] Zmieñ bohatera");
			System.out.println("[3] WyjdŸ z gry");

			Scanner reader = new Scanner(System.in);
			choice = reader.nextInt();
			switch (choice) {
			case 1:
				didWon = fight(chosenHero, start.chooseEnemy());
				break;
			case 2:
				chosenHero = start.chooseHero();
				didWon = fight(chosenHero, start.chooseEnemy());
				break;
			case 3:
				didWon = false;
				break;
			}
		}

		// co jeœli pora¿ka
		System.out.println("===========++============");
		System.out.println("To ju¿ koniec twojej przygody, iloœæ zwyciêstw: " + winsAmount);

	}

}
