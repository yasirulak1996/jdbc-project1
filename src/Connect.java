

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class Connect {

    static Connection con;

    public Connect() {
        connect();
    }

    // attempt to connect to MySQL database
    public static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/news1", "root",
                    "Hashan1996@");
            //
            System.out.println("Successful Connection");
            // Set values for the placeholders (?)


        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe);
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }
    public static void insertItem(String category, String name, String description) {
        String sql = "INSERT INTO items (category, name, description) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, category);
            pstmt.setString(2, name);
            pstmt.setString(3, description);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Item inserted successfully!");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }

    public static void getItems() {
        String sql = "SELECT * FROM items";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nItems in Database:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String category = rs.getString("category");
                String name = rs.getString("name");
                String description = rs.getString("description");

                System.out.println("ID: " + id + ", Category: " + category +
                        ", Name: " + name + ", Description: " + description);
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            if (con != null) {
                con.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }


}
