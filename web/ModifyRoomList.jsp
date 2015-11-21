<%-- 
    Document   : ModifyRoomList
    Created on : Nov 20, 2015, 3:07:49 PM
    Author     : Luiza
--%>

<%@page import="java.util.List"%>
<%@page import="application.Interface.AdminService"%>
<%@page import="application.Domain.Goal"%>
<%@page import="application.Domain.Room"%>
<%@page import="application.Impl.AdminServiceImpl"%>
<%@page import="application.Domain.Employee"%>
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
                        <li><a href="#">Meu consumo</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Olá, ${name}</a></li>
                        <li><a href="./logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <%
            AdminService service = new AdminServiceImpl();
            Employee e = (Employee) session.getAttribute("user");
            Room r = service.findRoom(e.getWorkRoomID());
            Goal g = new Goal(null, 0);// = service.findGoal();            
        %>
        <div align="center" class="well">
            <font color="black" size="4"><b>Saldo:</b> <%= r.getCreditAmount()%></font> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <font color="black" size="4"><b>Meta:</b> <%= g.getValue()%> </font>              
        </div>
        <br>
        <% List<Room> rooms = service.listAllRooms(); %>
        
        <div class="container">
            <div align="center" id="block">
                <table class="table table-hover" align="center">
                    <tr>
                        <td><b>Nome da sala</b></td>
                        <td></td>
                    </tr>
                    
                    <% for(Room room : rooms) { %>
                    <tr>
                        <td><%= room.getName() %></td>
                        <% if(room.getIdentifier() == 0) { %>
                        <td>
                            <form method="post" action="./modify_room_form">
                                    <input type="hidden" name="id" value="<%= room.getIdentifier() %>">
                                    <button type="submit" class="btn btn-primary" disabled="disabled"><b>Alterar</b></button>
                            </form>
                        </td>
                        <% } else { %>
                        <td>
                            <form method="post" action="./modify_room_form">
                                    <input type="hidden" name="id" value="<%= room.getIdentifier() %>">
                                    <button type="submit" class="btn btn-primary"><b>Alterar</b></button>
                            </form>
                        </td>
                        <% } %>
                    </tr>
                    <% } %>
                </table>
            </div>
        </div>           
                        
    </body>
</html>
