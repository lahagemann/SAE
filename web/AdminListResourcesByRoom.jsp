<%-- 
    Document   : AdminListResourcesByRoom
    Created on : Nov 9, 2015, 1:42:35 PM
    Author     : Luiza
--%>

<%@page import="application.Domain.Goal"%>
<%@page import="application.Domain.Employee"%>
<%@page import="application.Impl.AdminServiceImpl"%>
<%@page import="application.Interface.AdminService"%>
<%@page import="application.Domain.Resource"%>
<%@page import="application.Domain.Room"%>
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
        <% int roomID = (Integer) session.getAttribute("searchedRoom");
           Room room = service.findRoom(roomID); %>
           
        <h3 align="center">Sala <%= room.getIdentifier() %></h3>
        <br>
        <br>
        <div class="container">
            <div align="center" id="block">
                <table class="table table-hover" align="center">
                    <tr>
                            <td><b>Identificação</b></td>
                            <td><b>Tipo de recurso</b></td>
                            <td><b>Ação</b></td>
                            <td></td>
                            <td><b>Manutenção</b></td>
                    </tr>
                    <% for(Resource resource : room.getResourceList()) { %>
                        <tr>
                            <td>
                                <%= resource.getName() %> 
                            </td>
                            <td>
                                <%= resource.getType() %>
                            </td>
                            
                            <% if(resource.isOn()) { %>
                                <td>
                                    <button type="button" class="btn btn-success" disabled="disabled"><b>ON</b></button>
                                </td>
                                <td>
                                    <form action=" ./turn_off_by_room" method="post">
                                        <input type="hidden" name="id" value="<%= resource.getIdentifier() %>">
                                        <button type="submit" class="btn btn-danger"><b>OFF</b></button>
                                    </form>
                                </td>
                            <% } else { %>
                                <td>
                                    <form action=" ./turn_on_by_room" method="post">
                                        <input type="hidden" name="id" value="<%= resource.getIdentifier() %>">
                                        <button type="submit" class="btn btn-success" ><b>ON</b></button>
                                    </form>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-danger" disabled="disabled"><b>OFF</b></button>
                                </td>
                            <% } %>
                            
                            <td>    
                                <button type="button" class="btn btn-warning"><b>Solicitar</b></button>
                            </td>
                        </tr>
                    <% } %>
                </table>
            </div>
        </div>
        
        
    </body>
</html>
