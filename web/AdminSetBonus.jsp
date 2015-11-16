<%-- 
    Document   : AdminSetBonus
    Created on : Nov 15, 2015, 12:56:56 AM
    Author     : Luiza
--%>

<%@page import="application.Impl.AdminServiceImpl"%>
<%@page import="application.Domain.Goal"%>
<%@page import="application.Domain.Employee"%>
<%@page import="application.Interface.AdminService"%>
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
                        <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
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
        <h2 align="center">Adicionar bônus</h2>
        <% Room room = (Room) request.getAttribute("room"); %>
        
        <form class="form-horizontal" role="form" method="post" action="./add_bonus">
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Nome:</label>
                <input type="text" name="room" value="<%= room.getIdentifier() %>" disabled="disabled">
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="room">Sala:</label>
                <div class="col-sm-10">
                    <input type="hidden" name="room" value="<%= room.getIdentifier() %>">
                    <input type="number" min="0" step="0.1" class="form-control" placeholder="Digite o número da sala do funcionário" name="bonus">
                </div>
            </div>
            <div class="form-group"> 
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Adicionar</button>
                </div>
            </div>
        </form>
    </body>
</html>
