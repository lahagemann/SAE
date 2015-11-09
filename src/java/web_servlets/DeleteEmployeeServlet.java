/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_servlets;

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
public class DeleteEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Preprocess request: we actually don't need to do any business stuff, so just display JSP.
        request.getRequestDispatcher("/DeleteEmployee.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        AdminService service = new AdminServiceImpl();
        
        try {
            if(service.deleteEmployee(id))
                request.setAttribute("message", "foi excluído com sucesso");
            else
                request.setAttribute("message", "não foi excluído com sucesso");
        } catch (SQLException s) {
            s.printStackTrace();
        } catch (ConnectionException s) {
            s.printStackTrace();
        }
        
        request.getRequestDispatcher("/DeleteEmployee.jsp").forward(request, response);
    }
}