<%-- 
    Document   : EmployeeListResources
    Created on : Nov 9, 2015, 2:07:15 PM
    Author     : Luiza
--%>

<%@page import="application.Domain.Goal"%>
<%@page import="application.Impl.EmployeeServiceImpl"%>
<%@page import="application.Interface.EmployeeService"%>
<%@page import="application.Domain.Resource"%>
<%@page import="java.util.List"%>
<%@page import="application.Domain.Employee"%>
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
                        <li class="active"><a href="EmployeeHome.jsp">Home</a></li>
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
            EmployeeService service = new EmployeeServiceImpl();
            Employee e = (Employee) session.getAttribute("user");
            Room r = service.findRoom(e.getWorkRoomID());
            Goal g = service.findGoal(r.getDailyGoal().getIdentifier());           
        %>
        <div align="center" class="well">
            <font color="black" size="4"><b>Saldo:</b> <%= r.getCreditAmount()%></font> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <font color="black" size="4"><b>Meta:</b> <%= g.getValue()%> </font>              
        </div>
        <br>
        <% List<Resource> resources = r.getResourceList(); %>
        <h3 align="center">Sala <%= e.getWorkRoomID() %></h3>
        <br>
        <br>
        <div class="container">
            <div align="center" id="block">
                <table class="table table-hover" align="center">
                    <tr>
                            <td><b>Identificação</b></td>
                            <td><b>Tipo de recurso</b></td>
                            <td><b>Ação</b></td>
                            <td><b>Manutenção</b></td>
                    </tr>
                    <% for(Resource resource : resources) { %>
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
                                        <a href="./turn_off_employee?id=<%=resource.getIdentifier()%>" class="btn btn-danger"><b>OFF</b></a>
                                    </div>
                                </td>
                                <% } else { %>
                                    <td>
                                        <div class="btn-group" role="group">
                                            <a href="./turn_on_employee?id=<%=resource.getIdentifier()%>" class="btn btn-success"><b>ON</b></a>
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
    </body>
</html>
