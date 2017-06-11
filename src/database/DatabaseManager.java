package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Human;
import model.Sword;
import model.Warrior;
import model.Orc;

import controller.CharacterPreparation;

public class DatabaseManager {

	CharacterPreparation cp = new CharacterPreparation();

	private final String url = "jdbc:postgresql://localhost/magiczni_wojownicy";
	private final String user = "Kris";
	private final String password = "kris";

	private static DatabaseManager instance;
	private Connection connection;

	private DatabaseManager() {
		connect();
	}

	public static DatabaseManager getInstance() {
		if (instance == null) {
			instance = new DatabaseManager();
		}
		return instance;
	}

	private void connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to the PostgreSQL server successfully.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		connection = conn;
	}

	public int getCreaturesCount() {
		String SQL = "SELECT count(*) FROM creatures";
		int count = 0;

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return count;
	}

	public ArrayList<Sword> getItems() {
		ArrayList<Sword> listOfWeapons = new ArrayList<Sword>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM items";
			ResultSet rs = stmt.executeQuery(sql);
			// STEP 5: Extract data from result set

			while (rs.next()) {

				Sword sword = new Sword(rs.getInt("weight"), rs.getString("name"), rs.getInt("maxdamage"),
						rs.getInt("mindamage"), rs.getDouble("speed"));
				listOfWeapons.add(sword);
			}
			rs.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return listOfWeapons;
	}

	public ArrayList<Human> getHeros() {
		ArrayList<Human> listOfHeros = new ArrayList<Human>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM creatures";
			ResultSet rs = stmt.executeQuery(sql);
			// STEP 5: Extract data from result set
			while (rs.next()) {
				if (rs.getString("ctype") == "Human") {
					Warrior warrior = new Warrior(rs.getInt("hitpoints"), rs.getInt("strength"), rs.getString("name"),
							rs.getDouble("speed"), cp.listOfSwords.get(rs.getInt("weapon_id"))) {
					};
					listOfHeros.add(warrior);
				}
			}
			rs.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return listOfHeros;
	}

	public ArrayList<Orc> getEnemies() {
		ArrayList<Orc> listOfEnemies = new ArrayList<Orc>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM creatures";
			ResultSet rs = stmt.executeQuery(sql);
			// STEP 5: Extract data from result set
			while (rs.next()) {
				if (rs.getString("ctype") == "Orc") {
					Orc orc = new Orc(rs.getInt("hitpoints"), rs.getInt("strength"), rs.getString("name"),
							rs.getDouble("speed"), cp.listOfSwords.get(rs.getInt("weapon_id")));
					listOfEnemies.add(orc);
				}
			}
			rs.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return listOfEnemies;
	}

}
