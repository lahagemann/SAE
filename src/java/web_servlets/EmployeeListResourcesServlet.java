/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_servlets;

import application.Domain.Employee;
import application.Domain.Resource;
import application.Impl.EmployeeServiceImpl;
import application.Interface.EmployeeService;
import database.Connection.ConnectionException;
import database.ServicesDB.DataNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
public class EmployeeListResourcesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Preprocess request: we actually don't need to do any business stuff, so just display JSP.
        request.getRequestDispatcher("/EmployeeHome.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee e = (Employee) session.getAttribute("user");

        EmployeeService service = new EmployeeServiceImpl();
        List<Resource> resources;
        try {
            resources = service.listResorcesWorkRoom(e.getIdentifier());
            request.setAttribute("resources", resources);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeListResourcesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
            Logger.getLogger(EmployeeListResourcesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataNotFoundException ex) {
            Logger.getLogger(EmployeeListResourcesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        request.getRequestDispatcher("/EmployeeListResources.jsp").forward(request, response);
    }
}
