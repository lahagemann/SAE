<%-- 
    Document   : EmployeeViewCustomAction
    Created on : Nov 16, 2015, 3:33:09 PM
    Author     : Luiza
--%>

<%@page import="application.Domain.Resource"%>
<%@page import="application.Domain.CustomAction"%>
<%@page import="java.util.List"%>
<%@page import="application.Domain.Goal"%>
<%@page import="application.Domain.Room"%>
<%@page import="application.Domain.Employee"%>
<%@page import="application.Impl.EmployeeServiceImpl"%>
<%@page import="application.Interface.EmployeeService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SAE</title>
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
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
            Room room = service.findRoom(e.getWorkRoomID());
            Goal g = new Goal(null, 0);// = service.findGoal();            
        %>
        <div align="center" class="well">
            <font color="black" size="4"><b>Saldo:</b> <%= room.getCreditAmount()%></font> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <font color="black" size="4"><b>Meta:</b> <%= g.getValue()%> </font>              
        </div>
        <br>
        <% List<CustomAction> actions = service.listCustomActions(e.getIdentifier()); %>
        
        <table class="table table-hover" align="center">
            <tr>
                <td><b>Descrição</b></td>
                <td><b>Recursos</b></td>
                <td><b>Ação</b></td>
            </tr>
            <% for(CustomAction action : actions) { %>
            <tr>
                <td><%= action.getIdentifier() %></td>
                <td>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapse1"><span class="glyphicon glyphicon-triangle-bottom"></span>&nbsp;Recursos nesta ação:</a>
                                </h4>
                            </div>
                            <div id="collapse1" class="panel-collapse collapse">
                                <ul class="list-group">
                                    <% for(Resource r : action.getResourceList()) { %>
                                        <li class="list-group-item"><%= r.getName() %> &nbsp;&nbsp; (<%= r.getType() %>)</li>
                                    <% } %>    
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <form action="./custom_action_on" method="post">
                        <input type="hidden" name="id" value="<%= action.getIdentifier() %>">
                        <button type="button" class="btn btn-success"><b>ON</b></button>
                    </form>
                    <form action="./custom_action_off" method="post">
                        <input type="hidden" name="id" value="<%= action.getIdentifier() %>">
                        <button type="submit" class="btn btn-danger"><b>OFF</b></button>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
    </body>
</html>
