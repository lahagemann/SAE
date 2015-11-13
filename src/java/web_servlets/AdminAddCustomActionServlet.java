/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_servlets;

import application.Domain.CustomAction;
import application.Domain.Employee;
import application.Domain.Resource;
import application.Impl.AdminServiceImpl;
import application.Interface.AdminService;
import database.Connection.ConnectionException;
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
public class AdminAddCustomActionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Preprocess request: we actually don't need to do any business stuff, so just display JSP.
        request.getRequestDispatcher("/AdminAddCustomAction.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee user = (Employee) session.getAttribute("user");
        
        AdminService service = new AdminServiceImpl();
        List<Resource> actionResources = new ArrayList<Resource>();

        String[] resources = request.getParameterValues("resources");
        for (int i = 0; i < resources.length; i++) {
            try {
                actionResources.add(service.findResource(Integer.parseInt(resources[i])));
            } catch (SQLException ex) {
                Logger.getLogger(AdminAddCustomActionServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ConnectionException ex) {
                Logger.getLogger(AdminAddCustomActionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        CustomAction customAction = new CustomAction(user.getIdentifier(), actionResources);
        
        
        
    }
}
