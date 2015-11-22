<%-- 
    Document   : AdminReport
    Created on : Nov 21, 2015, 3:25:12 PM
    Author     : Luiza
--%>

<%@page import="application.Domain.TurnOnOrOffReport"%>
<%@page import="java.util.List"%>
<%@page import="application.Impl.AdminServiceImpl"%>
<%@page import="application.Domain.Goal"%>
<%@page import="application.Domain.Room"%>
<%@page import="application.Domain.Employee"%>
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
        
        <h2 align="center">Relatório do dia <%= (String) request.getAttribute("day") %></h2>
        <br>
        
        <% List<TurnOnOrOffReport> reports = (List<TurnOnOrOffReport>) request.getAttribute("reports"); %>
        
        <div class="container">
            <div align="center" id="block">
                <table class="table table-hover" align="center">
                    <tr class="warning">
                        <td><b>Recurso</b></td>
                        <td><b>Localização</b></td>
                        <td><b>Tempo de uso</b></td>
                        <td><b>Data de início</b></td>
                    </tr>
                    <% for(TurnOnOrOffReport report : reports) { %>
                    <tr>
                        <td><%= report.getResourceName() %></td>
                        <td><%= report.getRoomName() %></td>
                        <% if(report.secondsTurnedOn()/60 == 0) { %> 
                            <td><%= report.secondsTurnedOn() %> segundo(s)</td>
                        <% } else if(report.secondsTurnedOn()/60 > 59) { %>
                            <td><%= report.secondsTurnedOn()/3600 %> hora(s)</td>
                        <% } else { %>
                            <td><%= report.secondsTurnedOn()/60 %> minuto(s)</td>
                        <% } %>
                        <td><%= report.getInitialTime().toString() %></td>
                    </tr>
                    <% } %>
                </table>
            </div>
        </div>
    </body>
</html>
