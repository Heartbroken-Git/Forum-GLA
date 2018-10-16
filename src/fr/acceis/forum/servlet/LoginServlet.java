package fr.acceis.forum.servlet;

import fr.acceis.forum.dao.ImplDao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            return recPwd.equals(req.getParameter("password"));

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }

    }
}
