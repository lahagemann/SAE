/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_servlets;

import application.Impl.AdminServiceImpl;
import application.Interface.AdminService;
import application.Interface.LicenceException;
import database.Connection.ConnectionException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luiza
 */
public class TurnOffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Preprocess request: we actually don't need to do any business stuff, so just display JSP.
        request.getRequestDispatcher("/ListAllResources.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        AdminService service = new AdminServiceImpl();
        try {
            service.turnOffResource(Integer.parseInt(id),(int) session.getAttribute("userID"));
        } catch (SQLException s) {
            s.printStackTrace();
        } catch (ConnectionException s) {
            s.printStackTrace();
        }catch (LicenceException s) {
            s.printStackTrace();
        }
        
        request.getRequestDispatcher("/ListAllResources.jsp").forward(request, response);
    }
}
