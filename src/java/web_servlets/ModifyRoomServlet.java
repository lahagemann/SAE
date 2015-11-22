/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_servlets;

import application.Domain.Resource;
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
public class ModifyRoomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Preprocess request: we actually don't need to do any business stuff, so just display JSP.
        request.getRequestDispatcher("/ModifyRoom.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int goalID = Integer.parseInt(request.getParameter("goal"));
        
        AdminService service = new AdminServiceImpl();
        
        try {
            Room r = service.findRoom(id);
            r.setName(name);
            r.setDailyGoal(service.findGoal(goalID));
            service.updateRoom(r);
        } catch (SQLException s) {
            s.printStackTrace();
        } catch (ConnectionException s) {
            s.printStackTrace();
        } catch (DataNotFoundException ex) {
            request.getRequestDispatcher("/ErrorNoRoomWithChosenNumber.jsp").forward(request, response);
        } catch (InconsistentDBException ex) {
            Logger.getLogger(ModifyRoomServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.getRequestDispatcher("/ModifyRoomList.jsp").forward(request, response);
    }
}
