package fr.acceis.forum.servlet;

import fr.acceis.forum.classes.Sujet;
import fr.acceis.forum.dao.ImplDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccueilServlet extends HttpServlet {

    private ImplDao dao;

    public AccueilServlet() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        dao = ImplDao.getInstance();
    }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		retrieveAllThreads(req);
        req.getRequestDispatcher("/WEB-INF/jsp/threads.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private void retrieveAllThreads(HttpServletRequest req) {

        try {
            ArrayList<Sujet> table = dao.readAllThreads();
            req.getSession().setAttribute("sujets", table);
        } catch (SQLException e) {
            System.err.println("Server Error");
            System.err.println(e);
        }
    }

}
