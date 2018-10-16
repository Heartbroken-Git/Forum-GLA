package fr.acceis.forum.dao;

import fr.acceis.forum.classes.Sujet;

import java.sql.*;
import java.util.ArrayList;


// BEWARE, probably awful implementation of the Singleton pattern, may fuck up in multi-threaded progs
public class ImplDao {

    private static ImplDao theInstance = null;


    private Connection conn;

    private ImplDao() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("org.hsqldb.jdbcDriver").newInstance();
        conn = DriverManager.getConnection("jdbc:hsqldb:/root/Documents/TP/GLA/ForumGLA/forum/data/basejpa", "sa",  "");
    }

    public static ImplDao getInstance() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        if (theInstance == null) {
            theInstance = new ImplDao();
        }
        return theInstance;
    }

    // CRUD

    // C

    // Messages

    // Sujets
    public void createThreads(String titre, int idAut) throws IllegalArgumentException, SQLException {

        if (titre.length() > 255) {
            throw new IllegalArgumentException("Title length exceeded");
        }

        String sqlQuery = "INSERT INTO Sujets VALUES (?,?);";
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        stmt.setString(1, titre);
        stmt.setInt(2, idAut);
        stmt.executeQuery();
    }

    // Utilisateurs
    public void createUsers(String username, String password) throws IllegalArgumentException, SQLException {

        if (username.length() > 255||password.length() > 255) {
            throw new IllegalArgumentException("Login/password length exceeded");
        }

        String sqlQuery = "INSERT INTO Utilisateurs VALUES (?,?);";
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        stmt.setString(1, username);
        stmt.setString(2, password);
        stmt.executeQuery();
    }

    // R

    // Messages

    // Sujets
    public Sujet readThread(int idSujet) {
        String sqlQuery = "SELECT * FROM Sujets WHERE idSujet=?;";
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        stmt.setString(1, idSujet); // (╯°□°）╯︵ ┻━┻ Y U NO START FROM 0 ?!?
        ResultSet res = stmt.executeQuery();
        res.next();

        int resIdThread = res.getInt(1);
        String resTitle = res.getString(2);
        int resIdAut = res.getInt(3);
        int resNbMsg = res.getInt(4);

        stmt.close();
        res.close();

        return new Sujet(resIdThread, resTitle, resIdAut, resNbMsg);
    }

    // Utilisateurs
    public String readPassword(String username) throws SQLException{
        String sqlQuery = "SELECT password FROM Utilisateurs WHERE login=?;";
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        stmt.setString(1, username); // (╯°□°）╯︵ ┻━┻ Y U NO START FROM 0 ?!?
        ResultSet res = stmt.executeQuery();
        res.next();

        String resString = res.getString(1);

        stmt.close();
        res.close();

        return resString; // (╯°□°）╯︵ ┻━┻ Y U NO START FROM 0 ?!?
    }

    public void updatePassword(String username, String newPwd) throws IllegalArgumentException, SQLException {

        if (newPwd.length() > 255) {
            throw new IllegalArgumentException("Password length exceeded");
        }

        String sqlQuery = "UPDATE Utilisateurs SET password=? WHERE login=?;";
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        stmt.setString(1, newPwd);
        stmt.setString(2, username);
        stmt.executeQuery();
    }

    public void deleteUsers(String username) throws SQLException {
        String sqlQuery = "DELETE FROM Utilisateurs WHERE login=?;";
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        stmt.setString(1, username);
        stmt.executeQuery();
    }
}
