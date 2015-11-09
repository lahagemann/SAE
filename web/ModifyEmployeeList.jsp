<%-- 
    Document   : ModifyEmployeeList
    Created on : Nov 8, 2015, 9:16:53 PM
    Author     : Luiza
--%>

<%@page import="application.Domain.Employee"%>
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
        
        <%
            AdminService service = new AdminServiceImpl();
            List<Employee> employees = service.listAllEmployees();
        %>
        
        <div class="container">
            <div align="center" id="block">
                <table class="table table-hover" align="center">
                    <tr>
                        <td><b>Identificação</b></td>
                        <td><b>Nome</b></td>
                        <td></td>
                    </tr>
                    <% for(Employee e : employees) { %>
                        <tr>
                            <td><%= e.getIdentifier() %></td>
                            <td><%= e.getName() %></td>
                            <td>
                                <form method="post" action="./modify_employee_form">
                                    <input type="hidden" name="id" value="<%= e.getIdentifier() %>">
                                    <button type="submit" class="btn btn-primary" ><b>Alterar</b></button>
                                </form>
                            </td>                       
                        </tr>
                    <% } %>
                </table>
            </div>
        </div>
        
    </body>
</html>