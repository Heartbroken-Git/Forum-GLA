package fr.acceis.forum.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


// BEWARE, probably awful implementation of the Singleton pattern, may fuck up in multi-threaded progs
public class ChaosDao {

    private static ChaosDao theInstance = null;


    private Connection conn;

    private ChaosDao() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("org.hsqldb.jdbcDriver").newInstance();
        conn = DriverManager.getConnection("jdbc:hsqldb:/root/Documents/TP/GLA/ForumGLA/forum/data/basejpa", "sa",  "");
    }

    public static ChaosDao getInstance() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        if (theInstance == null) {
            theInstance = new ChaosDao();
        }
        return theInstance;
    }

    // CRUD
    public void createUsers(String username, String password) throws IllegalArgumentException, SQLException {

        if (username.length() > 255||password.length() > 255) {
            throw new IllegalArgumentException("Login/password length exceeded");
        }

        String sqlQuery = "INSERT INTO Utilisateurs VALUES ('"+username+"','"+password+"');";
        Statement stmt = conn.createStatement();
        stmt.executeQuery(sqlQuery);
    }

    public String readPassword(String username) throws SQLException{
        String sqlQuery = "SELECT password FROM Utilisateurs WHERE login='"+username+"';";
        Statement stmt = conn.createStatement();
        stmt.executeQuery(sqlQuery);

        return stmt.getResultSet().getString(1); // (╯°□°）╯︵ ┻━┻ Y U NO START FROM 0 ?!?
    }

    public void updatePassword(String username, String newPwd) throws IllegalArgumentException, SQLException {

        if (newPwd.length() > 255) {
            throw new IllegalArgumentException("Password length exceeded");
        }

        String sqlQuery = "UPDATE Utilisateurs SET password='"+newPwd+"' WHERE login='"+username+"';";
        Statement stmt = conn.createStatement();
        stmt.executeQuery(sqlQuery);
    }

    public void deleteUsers(String username) throws SQLException {
        String sqlQuery = "DELETE FROM Utilisateurs WHERE login='"+username+"';";
        Statement stmt = conn.createStatement();
        stmt.executeQuery(sqlQuery);
    }
}
