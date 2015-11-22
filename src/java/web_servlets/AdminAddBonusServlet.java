/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_servlets;

import application.Domain.Room;
import application.Impl.AdminServiceImpl;
import application.Interface.AdminService;
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

/**
 *
 * @author Luiza
 */
public class AdminAddBonusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Preprocess request: we actually don't need to do any business stuff, so just display JSP.
        request.getRequestDispatcher("/AdminAddBonus.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        float bonus = Float.parseFloat(request.getParameter("bonus"));
        
        AdminService service = new AdminServiceImpl();
        
        try {
            Room r = service.findRoom(id);
            r.setCreditAmount(r.getCreditAmount() + bonus);
            service.updateRoom(r);
        } catch (SQLException ex) {
            Logger.getLogger(AdminSetBonusServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
            Logger.getLogger(AdminSetBonusServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InconsistentDBException ex) {
            Logger.getLogger(AdminSetBonusServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataNotFoundException ex) {
            Logger.getLogger(AdminSetBonusServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/AdminAddBonus.jsp").forward(request, response);
    }
    
}
