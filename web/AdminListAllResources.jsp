<%-- 
    Document   : listar
    Created on : Oct 11, 2015, 5:01:18 PM
    Author     : Luiza
--%>

<%@page import="application.Domain.Goal"%>
<%@page import="application.Domain.Employee"%>
<%@page import="application.Impl.AdminServiceImpl"%>
<%@page import="application.Interface.AdminService"%>
<%@page import="application.Domain.Room"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="application.Domain.Resource"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SAE</title>
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="css/sae.css" rel="stylesheet">
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
            Goal g = service.findGoal(r.getDailyGoal().getIdentifier());           
        %>
        <div align="center" class="well">
            <font color="black" size="4"><b>Saldo:</b> <%= r.getCreditAmount()%></font> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <font color="black" size="4"><b>Meta:</b> <%= g.getValue()%> </font>              
        </div>
        <br>
        <h2 align="center">Recursos</h2>
        <br>
        <br>
        
        <% List<Room> rooms = service.listAllRooms(); %>
        
        <% for(Room room : rooms) { 
            if(room.getIdentifier() != 0) { %>

        <h3 align="center"><b><%= room.getName()%></b></h3>

        <div class="container">
            <div align="center" id="block">
                <table class="table table-hover" align="center">
                    
                        <tr>
                            <td><b>Identificação</b></td>
                            <td><b>Tipo de recurso</b></td>
                            <td><b>Ação</b></td>
                            <td><b>Manutenção</b></td>
                        </tr>
                                             
                        <%  for(Resource resource : room.getResourceList()) { %>
                            <tr>
                                <td>
                                    <%= resource.getName() %> 
                                </td>
                                <td>
                                    <%= resource.getType() %>
                                </td>
                            
                                <% if(resource.isOn()) { %>
                                <td>
                                    <div class="btn-group" role="group">
                                        <a href="#" class="btn btn-success disabled"><b>ON</b></a>
                                        <a href="./turn_off_all?id=<%=resource.getIdentifier()%>" class="btn btn-danger"><b>OFF</b></a>
                                    </div>
                                </td>
                                <% } else { %>
                                    <td>
                                        <div class="btn-group" role="group">
                                            <a href="./turn_on_all?id=<%=resource.getIdentifier()%>" class="btn btn-success"><b>ON</b></a>
                                            <a href="#" class="btn btn-danger disabled"><b>OFF</b></a>
                                        </div>
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
        <br>
        <%      } 
            } %>
    </body>
</html>
