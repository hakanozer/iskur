package controllers;

import util.Const;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "homeServlet", value = "/userLogin")
public class HomeServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if ( email.equals("ali@mail.com") && password.equals("12345") ) {
            // dashboard redirect
            resp.sendRedirect(Const.base +"dashboard.jsp");
        }else {
            // error message
            String error = "Kullanıcı adı yada şifre hatalı";
            req.setAttribute("error", error);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            dispatcher.forward(req, resp);
        }

    }
}
