/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_servlets;

import application.Domain.Admin;
import application.Domain.Employee;
import application.Impl.AdminServiceImpl;
import application.Interface.AdminService;
import database.Connection.ConnectionException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luiza
 */
public class ModifyEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Preprocess request: we actually don't need to do any business stuff, so just display JSP.
        request.getRequestDispatcher("/ModifyEmployee.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int roomNumber = Integer.parseInt(request.getParameter("room"));
        int isAdmin = 0;    //adicionar depois no novo método.
        if(request.getParameter("type").equals("admin"))
            isAdmin = 1;
        
        AdminService service = new AdminServiceImpl();
        
        try {
            Employee e = service.findEmployee(id);
            e.setCpf(cpf);
            e.setEmail(email);
            e.setName(name);
            e.setPassword(password);
            e.setWorkRoomID(roomNumber);
            
            service.updateEmployee(e);
            
            if(isAdmin == 1)
                service.promoteEmployee(id);
            
        } catch (SQLException s) {
            s.printStackTrace();
        } catch (ConnectionException s) {
            s.printStackTrace();
        }
        
        request.getRequestDispatcher("/Manage.jsp").forward(request, response);
    }
    
    
}
