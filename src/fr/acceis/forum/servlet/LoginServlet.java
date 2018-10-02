package fr.acceis.forum.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (authenticate(req)) {
            req.getRequestDispatcher("/WEB-INF/jsp/threads.jsp").forward(req, resp);
        } else {
            /*out.println("<script type=\"text/javascript\">");
            out.println("alert('User or password incorrect');");
            out.println("</script>");*/
        }
    }

    private boolean authenticate(HttpServletRequest req) {
        // temporary authentication thingy
        return (req.getParameter("username") == "root" && req.getParameter("password") == "pwd");
    }
}
