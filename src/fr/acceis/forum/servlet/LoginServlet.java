package fr.acceis.forum.servlet;

import fr.acceis.forum.dao.ImplDao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginServlet extends HttpServlet {

    private ImplDao dao;

    public LoginServlet() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        dao = ImplDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (authenticate(req)) {
            req.getSession().setAttribute("auth", Boolean.TRUE);
            req.getSession().setAttribute("username", req.getParameter("username"));
            req.getRequestDispatcher("/WEB-INF/jsp/threads.jsp").forward(req, resp);
        } else {
            System.err.println("Bad login/password");
            req.getSession().setAttribute("auth", Boolean.FALSE);
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }
    }

    private boolean authenticate(HttpServletRequest req) {
        try {

            String recPwd = dao.readPassword(req.getParameter("username"));
            String recSalt = dao.readSalt(req.getParameter("username"));
            String strToTest = req.getParameter("password").concat(recSalt);
            return recPwd.equals(getSHA(strToTest));

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }

    }

    public static String getSHA(String input)
    {

        try {

            // Static getInstance method is called with hashing SHA
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // digest() method called
            // to calculate message digest of an input
            // and return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown"
                    + " for incorrect algorithm: " + e);

            return null;
        }
    }
}
