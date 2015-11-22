/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_servlets;

import application.Impl.AdminServiceImpl;
import application.Interface.AdminService;
import database.Connection.ConnectionException;
import database.ServicesDB.InvalidGoalException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
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
public class AddGoalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Preprocess request: we actually don't need to do any business stuff, so just display JSP.
        request.getRequestDispatcher("/GoalForm.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] date = request.getParameter("date").split("\\-");
        Date day = new Date(Integer.parseInt(date[0])-1900, Integer.parseInt(date[1])-1, Integer.parseInt(date[2]));
        float value = Float.parseFloat(request.getParameter("value"));
        
        AdminService service = new AdminServiceImpl();
        
        try {
            if(service.createGoal(day, value))
                request.setAttribute("message", "foi adicionado com sucesso");
            else
                request.setAttribute("message", "não foi adicionado com sucesso");
        } catch (SQLException s) {
            s.printStackTrace();
        } catch (ConnectionException s) {
            s.printStackTrace();
        } catch (InvalidGoalException ex) {
            Logger.getLogger(AddGoalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.getRequestDispatcher("/AdminManage.jsp").forward(request, response);
    }
}
