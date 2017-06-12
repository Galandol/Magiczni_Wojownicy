package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.items.Axe;
import model.items.WeaponType;
import model.items.Sword;
import model.items.Weapon;

import model.creatures.Humanoid;
import model.creatures.HumanoidType;
import model.creatures.Orc;
import model.creatures.Warrior;

public class DatabaseManager {

	private final String url = "jdbc:postgresql://localhost/magiczni_wojownicy";
	private final String user = "postgres";
	private final String password = "user";

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
	/**
	 * bez parametrów zwraca listê wszystkich broni z bazy
	 * @return
	 */
	public List<Weapon> getItems(){
		List<Weapon> listOfWeapons = new ArrayList<Weapon>();
		try {

			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM items";
			ResultSet rs = stmt.executeQuery(sql);
			// STEP 5: Extract data from result set

			while (rs.next()) {
				Weapon weapon = null;

				if (rs.getString("itype").equals(WeaponType.Sword.toString())) {
					weapon = new Sword(rs.getInt("weight"), rs.getString("name"), rs.getInt("maxdamage"),
							rs.getInt("mindamage"), rs.getDouble("speed"));

				} else if (rs.getString("itype").equals(WeaponType.Axe.toString())) {
					weapon = new Axe(rs.getInt("weight"), rs.getString("name"), rs.getInt("maxdamage"),
							rs.getInt("mindamage"), rs.getDouble("speed"));
				}
				listOfWeapons.add(weapon);
			}

			rs.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return listOfWeapons;
	}
/**
 * podaj jakiego typu broni chcesz listê
 * @param weaponType
 * @return
 */
	public List<Weapon> getItems(WeaponType weaponType) {
		List<Weapon> listOfWeapons = new ArrayList<Weapon>();
		try {

			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM items WHERE itype = ?");
			stmt.setString(1, weaponType.toString());
			ResultSet rs = stmt.executeQuery();
			// STEP 5: Extract data from result set

			while (rs.next()) {
				Weapon weapon = null;

				if (weaponType == WeaponType.Sword) {
					weapon = new Sword(rs.getInt("weight"), rs.getString("name"), rs.getInt("maxdamage"),
							rs.getInt("mindamage"), rs.getDouble("speed"));

				} else if (weaponType == WeaponType.Axe) {
					weapon = new Axe(rs.getInt("weight"), rs.getString("name"), rs.getInt("maxdamage"),
							rs.getInt("mindamage"), rs.getDouble("speed"));
				}
				listOfWeapons.add(weapon);
			}

			rs.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return listOfWeapons;
	}
/**
 * podaj jakiego typu osobników chcesz listê
 * @param humanoidType
 * @return
 */
	public List<Humanoid> getHumanoids(HumanoidType humanoidType) {
		List<Humanoid> listOfHumanoids = new ArrayList<>();

		try {

			PreparedStatement stmt = connection.prepareStatement("SELECT * from creatures WHERE ctype = ?");
			stmt.setString(1, humanoidType.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Humanoid humanoid = null;

				if (humanoidType == HumanoidType.Human) {
					humanoid = new Warrior(rs.getInt("hitpoints"), rs.getInt("strength"), rs.getString("name"),
							rs.getDouble("speed"), getWeapon(rs.getInt("weapon_id")));

				} else if (humanoidType == HumanoidType.Orc) {
					humanoid = new Orc(rs.getInt("hitpoints"), rs.getInt("strength"), rs.getString("name"),
							rs.getDouble("speed"), getWeapon(rs.getInt("weapon_id")));
				}
				listOfHumanoids.add(humanoid);
			}
			rs.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return listOfHumanoids;
	}
/**
 * metoda do przypisywania konkretnej postaci jej broni
 * @param id
 * @return
 */
	public Weapon getWeapon(int id) {

		Weapon weapon = null;
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * from items WHERE  id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("itype").equals("Sword")) {
					weapon = new Sword(rs.getInt("weight"), rs.getString("name"), rs.getInt("maxdamage"),
							rs.getInt("mindamage"), rs.getDouble("speed"));
				} else if (rs.getString("itype").equals("Axe")) {
					weapon = new Axe(rs.getInt("weight"), rs.getString("name"), rs.getInt("maxdamage"),
							rs.getInt("mindamage"), rs.getDouble("speed"));
				}
			}
			rs.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return weapon;
	}
}
