package fr.acceis.forum.dao;

import fr.acceis.forum.classes.Sujet;
import fr.acceis.forum.classes.Utilisateur;

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

        stmt.close();
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

        stmt.close();
    }

    // R

    // Messages

    // Sujets
    public Sujet readThread(int idSujet) throws SQLException {
        String sqlQuery = "SELECT * FROM Sujets WHERE idSujet=?;";
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        stmt.setInt(1, idSujet); // (╯°□°）╯︵ ┻━┻ Y U NO START FROM 0 ?!?
        ResultSet res = stmt.executeQuery();
        res.next();

        int resIdThread = res.getInt(1);
        String resTitle = res.getString(2);
        Utilisateur resIdAut = readAuthorThread(res.getInt(3));
        int resNbMsg = res.getInt(4);

        stmt.close();
        res.close();

        return new Sujet(resIdThread, resTitle, resIdAut, resNbMsg);
    }

    public ArrayList<Sujet> readAllThreads() throws SQLException {
        String sqlQuery = "SELECT * FROM Sujets;";
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        ResultSet res = stmt.executeQuery();

        ArrayList<Sujet> resArray = new ArrayList<Sujet>();

        while(res.next()) {
            int resIdThread = res.getInt(1);
            String resTitle = res.getString(2);
            Utilisateur resIdAut = readAuthorThread(res.getInt(3));
            int resNbMsg = res.getInt(4);

            resArray.add(new Sujet(resIdThread, resTitle, resIdAut, resNbMsg));
        }
        stmt.close();
        res.close();

        return resArray;

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

    public Utilisateur readAuthorThread(int idThread) throws SQLException {
        String sqlQuery = "SELECT id, login FROM Utilisateurs FULL JOIN Sujets ON Utilisateurs.id = Sujets.auteur WHERE idSujet = ?;";
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        stmt.setInt(1, idThread);
        ResultSet res = stmt.executeQuery();
        res.next();

        int resId = res.getInt(1);
        String resLogin = res.getString(2);

        stmt.close();
        res.close();

        return new Utilisateur(resId, resLogin);
    }

    // U

    // Messages

    // Sujets
    public void updateTitle(int threadId, String newTitle) throws IllegalArgumentException, SQLException {

        if (newTitle.length() > 255) {
            throw new IllegalArgumentException("Title length exceeded");
        }

        String sqlQuery = "UPDATE Sujets SET titre=? WHERE idSujet=?;";
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        stmt.setString(1, newTitle);
        stmt.setInt(2, threadId);
        stmt.executeQuery();

        stmt.close();
    }

    public void updateNbMessages(int threadId, int newNbMsg) throws IllegalArgumentException, SQLException {

        String sqlQuery = "UPDATE Sujets SET nbMessages=? WHERE idSujet=?;";
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        stmt.setInt(1, newNbMsg);
        stmt.setInt(2, threadId);
        stmt.executeQuery();

        stmt.close();
    }

    // Utilisateurs
    public void updatePassword(String username, String newPwd) throws IllegalArgumentException, SQLException {

        if (newPwd.length() > 255) {
            throw new IllegalArgumentException("Password length exceeded");
        }

        String sqlQuery = "UPDATE Utilisateurs SET password=? WHERE login=?;";
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        stmt.setString(1, newPwd);
        stmt.setString(2, username);
        stmt.executeQuery();

        stmt.close();
    }

    // D

    // Messages

    // Sujets
    public void deleteThreads(int idThread) throws  SQLException {
        String sqlQuery = "DELETE FROM Sujets WHERE idSujet=?;";
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        stmt.setInt(1, idThread);
        stmt.executeQuery();

        stmt.close();
    }

    // Utilisateurs
    public void deleteUsers(String username) throws SQLException {
        String sqlQuery = "DELETE FROM Utilisateurs WHERE login=?;";
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        stmt.setString(1, username);
        stmt.executeQuery();

        stmt.close();
    }
}
