

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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root",
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
    public static void signup(String user_name, String password) {
        String sql = "INSERT INTO login (user_name,password) VALUES ( ?, ?)";




        try (PreparedStatement pstmt = con.prepareStatement(sql)) {


            pstmt.setString(1, user_name);
            pstmt.setString(2, password);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Item inserted successfully!");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }

    public boolean getItems(String username, String pass) {
        String sql = "SELECT * FROM login WHERE user_name = ? AND password = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, pass);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // If we find a match, return true
                return true;
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }

        // Return false if no matching user was found
        return false;
    }
    public static void setcontact(String name,String contact) {
        String sql = "INSERT INTO contact (name,contact) VALUES ( ?, ?)";


        try (PreparedStatement pstmt = con.prepareStatement(sql)) {


            pstmt.setString(1, name);
            pstmt.setString(2, contact);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Item inserted successfully!");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }



    public static void getcontacts() {
        String sql = "SELECT * FROM contact";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nItems in Database:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String contact = rs.getString("contact");

                System.out.println("ID: " + id + ", : " + name +
                        ", Name: " + contact);
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
