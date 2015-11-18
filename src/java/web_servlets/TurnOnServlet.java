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
public class TurnOnServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Preprocess request: we actually don't need to do any business stuff, so just display JSP.
        request.getRequestDispatcher("/ListAllResources.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee user = (Employee) session.getAttribute("user");
        
        String id = request.getParameter("id");
        AdminService service = new AdminServiceImpl();
        try {
            service.turnOnResource(Integer.parseInt(id),user.getIdentifier());            
        } catch (SQLException s) {
            s.printStackTrace();
        } catch (ConnectionException s) {
            s.printStackTrace();
        } catch (LicenceException s) {
            s.printStackTrace();
        } catch (DataNotFoundException ex) {
            Logger.getLogger(TurnOnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("/ListAllResources.jsp").forward(request, response);
    }
}
