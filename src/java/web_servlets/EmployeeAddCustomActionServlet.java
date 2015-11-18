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
import java.util.ArrayList;
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
public class EmployeeAddCustomActionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Preprocess request: we actually don't need to do any business stuff, so just display JSP.
        request.getRequestDispatcher("/EmployeeAddCustomAction.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee user = (Employee) session.getAttribute("user");
        String name = request.getParameter("name");

        EmployeeService service = new EmployeeServiceImpl();
        List<Resource> actionResources = new ArrayList<Resource>();

        String[] resources = request.getParameterValues("resources");
        
            try {
                for (int i = 0; i < resources.length; i++) 
                    actionResources.add(service.findResource(Integer.parseInt(resources[i])));
                
                service.createCustomAction(user.getIdentifier(), name, actionResources);
            } catch (SQLException ex) {
                Logger.getLogger(AdminAddCustomActionServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ConnectionException ex) {
                Logger.getLogger(AdminAddCustomActionServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DataNotFoundException ex) {
                Logger.getLogger(AdminAddCustomActionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        request.getRequestDispatcher("/EmployeeManageCustomAction.jsp").forward(request, response);
    }
}
