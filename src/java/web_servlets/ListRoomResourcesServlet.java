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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luiza
 */
public class ListRoomResourcesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Preprocess request: we actually don't need to do any business stuff, so just display JSP.
        request.getRequestDispatcher("/AdminListRooms.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomID = Integer.parseInt(request.getParameter("id"));
        
        AdminService service = new AdminServiceImpl();
        try {
            Room room = service.findRoom(roomID);
            request.setAttribute("room", room);
        } catch (SQLException s) {
            s.printStackTrace();
        } catch (ConnectionException s) {
            s.printStackTrace();
        }
        
        request.getRequestDispatcher("/AdminListResourcesByRoom.jsp").forward(request, response);
    }
}
