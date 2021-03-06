/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_servlets;

import application.Domain.Resource;
import application.Impl.AdminServiceImpl;
import application.Interface.AdminService;
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

/**
 *
 * @author Luiza
 */
public class ModifyResourceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Preprocess request: we actually don't need to do any business stuff, so just display JSP.
        request.getRequestDispatcher("/ModifyResource.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        float consumption = Float.parseFloat(request.getParameter("consumption"));
        int roomNumber = Integer.parseInt(request.getParameter("room"));
        
        AdminService service = new AdminServiceImpl();
        
        try {
            Resource r = service.findResource(id);
            r.setConsumption(consumption);
            r.setLocationID(roomNumber);
            r.setName(name);
            r.setType(type);
            service.updateResource(r);
        } catch (SQLException s) {
            s.printStackTrace();
        } catch (ConnectionException s) {
            s.printStackTrace();
        } catch (DataNotFoundException ex) {
            request.getRequestDispatcher("/ErrorNoRoomWithChosenNumber.jsp").forward(request, response);
        }
        
        request.getRequestDispatcher("/ModifyResourceList.jsp").forward(request, response);
    }
}
