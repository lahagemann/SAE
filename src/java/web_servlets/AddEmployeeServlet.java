/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_servlets;

import application.Impl.AdminServiceImpl;
import application.Interface.AdminService;
import database.Connection.ConnectionException;
import database.ServicesDB.DataNotFoundException;
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
public class AddEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Preprocess request: we actually don't need to do any business stuff, so just display JSP.
        request.getRequestDispatcher("/EmployeeForm.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            if(service.createEmployee(name, cpf, email, password, roomNumber, isAdmin))
                request.setAttribute("message", "foi adicionado com sucesso");
            else
                request.setAttribute("message", "não foi adicionado com sucesso");
        } catch (SQLException s) {
            s.printStackTrace();
        } catch (ConnectionException s) {
            s.printStackTrace();
        } catch (DataNotFoundException ex) {
            //nao achou sala
        }
        
        request.getRequestDispatcher("/AdminHome.jsp").forward(request, response);
    }
    
}
