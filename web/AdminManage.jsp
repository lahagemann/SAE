<%-- 
    Document   : Manage
    Created on : Nov 6, 2015, 11:36:25 PM
    Author     : Luiza
--%>

<%@page import="application.Domain.Goal"%>
<%@page import="application.Domain.Room"%>
<%@page import="application.Domain.Employee"%>
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
            Goal g = new Goal(null, 0);// = service.findGoal();            
        %>
        <div align="center" class="well">
            <font color="black" size="4"><b>Saldo:</b> <%= r.getCreditAmount()%></font> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <font color="black" size="4"><b>Meta:</b> <%= g.getValue()%> </font>              
        </div>
        <br>
        <div class="container">
            <div align="center" id="block">
                    <h3>Funcionário</h3>
                    <div class="btn-group" align="center">
                        <a href="AddEmployee.jsp" class="btn btn-primary"><b>Adicionar</b></a>
                        <a href="ModifyEmployeeList.jsp" class="btn btn-primary"><b>Alterar</b></a>
                        <a href="DeleteEmployee.jsp" class="btn btn-primary"><b>Excluir</b></a>
                    </div>
            </div>
            <div align="center" id="block">
                    <h3>Sala</h3>
                    <div class="btn-group" align="center">
                        <a href="AddRoom.jsp" class="btn btn-primary"><b>Adicionar</b></a>
                        <a href="#" class="btn btn-primary"><b>Alterar</b></a>
                        <a href="DeleteRoom.jsp" class="btn btn-primary"><b>Excluir</b></a>
                    </div>
            </div>
            <div align="center" id="block">
                    <h3>Recurso</h3>
                    <div class="btn-group" align="center">
                        <a href="AddResource.jsp" class="btn btn-primary"><b>Adicionar</b></a>
                        <a href="ModifyResourceList.jsp" class="btn btn-primary"><b>Alterar</b></a>
                        <a href="DeleteResource.jsp" class="btn btn-primary"><b>Excluir</b></a>
                    </div>
            </div>
            <div align="center" id="block">
                    <h3>Meta</h3>
                    <div class="btn-group" align="center">
                        <a href="AddGoal.jsp" class="btn btn-primary"><b>Adicionar</b></a>
                        <a href="#" class="btn btn-primary"><b>Alterar</b></a>
                        <a href="#" class="btn btn-primary"><b>Excluir</b></a>
                    </div>
            </div>
        </div>
    </body>
</html>
