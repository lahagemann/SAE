/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_servlets;

import application.Domain.Admin;
import application.Domain.Employee;
import application.Impl.EmployeeServiceImpl;
import application.Interface.EmployeeService;
import application.Interface.InvalidUserException;
import database.Connection.ConnectionException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luiza
 */
public class ValidateLoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Preprocess request: we actually don't need to do any business stuff, so just display JSP.
        request.getRequestDispatcher("/Login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        EmployeeService service = new EmployeeServiceImpl();
        try {
            Employee user = service.login(email, password);
            if(user instanceof Admin) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("name", user.getName());
                session.setAttribute("isAdmin", true);
                request.getRequestDispatcher("/AdminHome.jsp").forward(request, response);
            } else if(user instanceof Employee) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("name", user.getName());
                session.setAttribute("isAdmin", false);
                request.getRequestDispatcher("/EmployeeHome.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/Error.jsp").forward(request, response);
            }
        } catch (ConnectionException s) {
            s.printStackTrace();
        } catch (InvalidUserException ex) {
            Logger.getLogger(ValidateLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
}
