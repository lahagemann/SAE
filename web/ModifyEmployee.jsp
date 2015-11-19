<%-- 
    Document   : ModifyEmployee
    Created on : Nov 8, 2015, 10:06:37 PM
    Author     : Luiza
--%>

<%@page import="application.Domain.Admin"%>
<%@page import="application.Domain.Goal"%>
<%@page import="application.Impl.AdminServiceImpl"%>
<%@page import="application.Domain.Room"%>
<%@page import="application.Interface.AdminService"%>
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
        <% Employee employee = (Employee) request.getAttribute("employee"); %>
            
        <form class="form-horizontal" role="form" method="post" action="./modify_employee">
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Nome:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Digitar o nome do funcionário" name="name" value="<%= employee.getName() %>">
                    <input type="hidden" name="id" value="<%= employee.getIdentifier() %>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="cpf">CPF:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Digitar o cpf do funcionário" name="cpf" value="<%= employee.getCpf()%>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="email">Email:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Digitar o email do funcionário" name="email" value="<%= employee.getEmail()%>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="password">Senha:</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" placeholder="Digite uma senha para o funcionário" name="password" value="<%= employee.getPassword() %>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="room">Sala:</label>
                <div class="col-sm-10">
                    <input type="number" min="0" step="1" class="form-control" placeholder="Digite o número da sala do funcionário" name="room" value="<%= employee.getWorkRoomID() %>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">Tipo:</label>
                <div class="col-sm-10">
                    <% if(employee instanceof Admin) { %>
                        <label class="radio-inline"><input type="radio" name="type" value="admin" checked="checked">Admin</label>
                        <label class="radio-inline"><input type="radio" name="type" value="employee">Funcionário</label>
                    <% } else { %>
                        <label class="radio-inline"><input type="radio" name="type" value="admin">Admin</label>
                        <label class="radio-inline"><input type="radio" name="type" value="employee" checked="checked">Funcionário</label>
                    <% } %>
                </div>
            </div>
            <div class="form-group"> 
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-floppy-save"></span><b>&nbsp;&nbsp;Salvar</b></button>
                </div>
            </div>
        </form>
    </body>
</html>
