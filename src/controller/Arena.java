package controller;

import java.util.concurrent.ThreadLocalRandom;
import model.*;

public class Arena {

	public void fight(Humanoid attacker, Humanoid attacked) {
		int roundIndex = 0;
		double attackersCurrentSpeed = 0;
		double attackedCurrentSpeed = 0;
		int attackerHP = attacker.getHitPoints();
		int attackedHP = attacked.getHitPoints();
		boolean ifAttackerAttacked = false;

		if (attacker.getWeapon().getSpeed() < attacked.getWeapon().getSpeed()) {
			ifAttackerAttacked = true;
		}

		while (attackerHP > 0 && attackedHP > 0) {
			// System.out.println("Tura nr: " + roundIndex);
			if (ifAttackerAttacked)
				attackedCurrentSpeed += attacked.getWeapon().getSpeed();
			else
				attackersCurrentSpeed += attacker.getWeapon().getSpeed();

			System.out.println("// Szybkosc przed atakiem: " + attackersCurrentSpeed + " || " + attackedCurrentSpeed);
			if (attackersCurrentSpeed >= attackedCurrentSpeed) {
				double[] result = attack(attacker, attacked, attackedHP, attackersCurrentSpeed);
				System.out.println("WYNIK RUNDY: " + attacker.getName() + " -- " + result[0] + " // " + result[1]);
				attackersCurrentSpeed = result[0];
				attackedHP = (int) result[1];
				ifAttackerAttacked = true;
			} else {
				double[] result = attack(attacked, attacker, attackerHP, attackedCurrentSpeed);
				System.out.println("WYNIK RUNDY: " + result[0] + " // " + result[1]);
				attackedCurrentSpeed = result[0];
				attackerHP = (int) result[1];
				ifAttackerAttacked = false;
			}
			roundIndex++;
		}
		if (attackerHP <= 0) {
			System.out.println(attacker.getName() + " przegra³ starcie, " + attacked.getName() + " pozosta³o "
					+ attackedHP + " hp.");
		} else {
			System.out.println(attacker.getName() + " wychodzi zwyciêsko ze starcia! Pozosta³o hp: " + attackerHP);
		}
	}

	private double[] attack(Humanoid attacker, Humanoid attacked, int attackedHP, double attackersCurrentSpeed) {
		// TODO: pomyœleæ nad systemem pancerzy i uników.

		// w przysz³oœci dodaæ jeszcze parametr si³y.
		int minDamage = attacker.getWeapon().getMinDamage();
		// w przysz³oœci dodaæ jeszcze parametr si³y.
		int maxDamage = attacker.getWeapon().getMaxDamage();

		int damageDone = ThreadLocalRandom.current().nextInt(minDamage, maxDamage + 1);

		int HP = attackedHP; // trzeba bo inaczej rekurencja nie dzia³a
								// poprawnie
		double[] result = { 0, 0 };

		System.out.println(attacker.getName() + " atakuje " + attacked.getName() + " - obra¿enia: " + damageDone);

		HP -= damageDone;

		System.out.println(attacked.getName() + " wci¹¿ ma " + HP + " hp.");

		attackersCurrentSpeed -= 1;
		if (attackersCurrentSpeed > 1 && HP > 0) {
			System.out.println(attacker.getName() + " wyprowadza dodatkowy atak!");
			//attackersCurrentSpeed -= 1;
			result = attack(attacker, attacked, HP, attackersCurrentSpeed);

		} else {

			result[0] = attackersCurrentSpeed;
			result[1] = (double) HP;
		}

		return result;
	}

}
