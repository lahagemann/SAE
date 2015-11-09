<%-- 
    Document   : AdminResourceByRoom
    Created on : Nov 9, 2015, 1:17:48 PM
    Author     : Luiza
--%>

<%@page import="application.Impl.AdminServiceImpl"%>
<%@page import="application.Interface.AdminService"%>
<%@page import="application.Domain.Room"%>
<%@page import="java.util.List"%>
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
        <%  AdminService service = new AdminServiceImpl();
            List<Room> rooms = service.listAllRooms(); %>
        <h2 align="center">Salas</h2>
        <br>
        <br>
        <div class="container">
            <div align="center" id="block">
                <table class="table table-hover" align="center">
                    <tr>
                        <td><b>Número</b></td>
                        <td></td>
                    </tr>
                    <% for(Room room : rooms) { %>
                    <tr>
                        <td><%= room.getIdentifier() %></td>
                        <td>
                            <form method="post" action="./list_by_room">
                                <input type="hidden" name="id" value="<%= room.getIdentifier() %>">
                                <button type="submit" class="btn btn-primary" ><b>Listar</b></button>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </table>
            </div>
        </div>
    </body>
</html>
