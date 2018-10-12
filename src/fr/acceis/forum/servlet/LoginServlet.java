package fr.acceis.forum.servlet;

import fr.acceis.forum.dao.ChaosDao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    private ChaosDao dao;

    public LoginServlet() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        dao = ChaosDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (authenticate(req)) {
            req.getSession().setAttribute("auth", Boolean.TRUE);
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
            System.err.println(req.getParameter("password"));
            System.err.println(recPwd);
            return recPwd.equals(req.getParameter("password"));

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }

    }
}
