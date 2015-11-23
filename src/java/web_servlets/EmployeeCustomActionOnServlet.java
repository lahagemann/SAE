/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_servlets;

import application.Domain.Employee;
import application.Impl.AdminServiceImpl;
import application.Interface.AdminService;
import application.Interface.LicenceException;
import database.Connection.ConnectionException;
import database.ServicesDB.DataNotFoundException;
import database.ServicesDB.InconsistentDBException;
import java.io.IOException;
import java.sql.SQLException;
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
public class EmployeeCustomActionOnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        Employee user = (Employee) session.getAttribute("user");
        
        AdminService service = new AdminServiceImpl();
        try {
            service.customActionTurnOn(id, user.getIdentifier());
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeCustomActionOnServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
            Logger.getLogger(EmployeeCustomActionOnServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataNotFoundException ex) {
            Logger.getLogger(EmployeeCustomActionOnServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LicenceException ex) {
            Logger.getLogger(EmployeeCustomActionOnServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InconsistentDBException ex) {
            Logger.getLogger(EmployeeCustomActionOnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.getRequestDispatcher("/EmployeeViewCustomAction.jsp").forward(request, response);
    }
}
