<%-- 
    Document   : DeleteResource
    Created on : Nov 9, 2015, 3:36:47 PM
    Author     : Luiza
--%>

<%@page import="application.Domain.Goal"%>
<%@page import="application.Domain.Employee"%>
<%@page import="application.Domain.Resource"%>
<%@page import="application.Domain.Room"%>
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
        <h2 align="center">Excluir recursos</h2>
        <br>
        <br>
        <% List<Room> rooms = service.listAllRooms(); %>

        <% for (Room room : rooms) {%>

        <h3 align="center"><%= room.getName()%></h3>

        <div class="container">
            <div class="col-md-3"></div>
            <div class="col-md-6" align="center" id="block">
                <table class="table table-hover" align="center">
                    <tr>
                        <td><b>Identificação</b></td>
                        <td><b>Nome</b></td>
                        <td><b>Tipo do recurso</b></td>
                        <td></td>
                    </tr>

                    <% for (Resource resource : room.getResourceList()) {%>
                    <tr>
                        <td><%= resource.getIdentifier()%></td>
                        <td><%= resource.getName()%></td>
                        <td><%= resource.getType()%></td>
                        <td>
                            <form method="post" action="./delete_resource">
                                <input type="hidden" name="id" value="<%= resource.getIdentifier()%>">
                                <button type="submit" class="btn btn-primary" onclick="return confirm('Você tem certeza?')"><span class="glyphicon glyphicon-remove"></span><b>&nbsp;&nbsp;Excluir</b></button>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </table>
            </div>
            <div class="col-md-3"></div>
        </div>
        <% }%>
        <br>
    </body>
</html>
