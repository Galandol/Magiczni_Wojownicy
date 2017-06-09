package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

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
}
