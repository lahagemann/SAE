<%-- 
    Document   : DeleteGoal
    Created on : Nov 9, 2015, 8:36:19 PM
    Author     : Luiza
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="application.Domain.Goal"%>
<%@page import="java.util.List"%>
<%@page import="application.Impl.AdminServiceImpl"%>
<%@page import="application.Interface.AdminService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SAE</title>
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">SAE</a>
                </div>
                <div>
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="AdminHome.jsp">Home</a></li>
                        <li><a href="#">Page 1</a></li>
                        <li><a href="#">Page 2</a></li> 
                        <li><a href="#">Page 3</a></li> 
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>        
        <br>
        <br>
        <br>
        <h3 align="center">Excluir meta</h3>
        <br>
        <br>
        <%  AdminService service = new AdminServiceImpl();
            List<Goal> goals = new ArrayList<Goal>(); %>
        <div class="container">
            <div align="center" id="block">
                <table class="table table-hover" align="center">
                    <tr>
                        <td><b>Número</b></td>                       
                        <td>Valor</td>
                        <td>Dia</td>
                        <td></td>
                    </tr>
                    <% for(Goal goal : goals) { %>
                    <tr>
                        <td><%= goal.getIdentifier() %></td>
                        <td><%= goal.getValue() %></td>
                        <td><%= goal.getDay() %></td>
                        <td>
                            <form method="post" action="./delete_goal">
                                <input type="hidden" name="id" value="<%= goal.getIdentifier() %>">
                                <button type="submit" class="btn btn-primary" onclick="return confirm('Você tem certeza?')"><b>Excluir</b></button>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </table>
            </div>
        </div>  
    </body>
</html>
