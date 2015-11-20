<%-- 
    Document   : EmployeeManageCustomAction
    Created on : Nov 16, 2015, 3:29:32 PM
    Author     : Luiza
--%>

<%@page import="application.Domain.Goal"%>
<%@page import="application.Domain.Room"%>
<%@page import="application.Impl.EmployeeServiceImpl"%>
<%@page import="application.Domain.Employee"%>
<%@page import="application.Interface.EmployeeService"%>
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
            Goal g = new Goal(null, 0);// = service.findGoal();            
        %>
        <div align="center" class="well">
            <font color="black" size="4"><b>Saldo:</b> <%= r.getCreditAmount()%></font> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <font color="black" size="4"><b>Meta:</b> <%= g.getValue()%> </font>              
        </div>
        <br>
        <div class="container">
            <div align="center" id="block">
                    <h3>Ação personalizada</h3>
                    <div class="btn-group" align="center">
                        <a href="EmployeeViewCustomAction.jsp" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span><b>&nbsp;&nbsp;Visualizar minhas ações</b></a>
                        <a href="EmployeeAddCustomAction.jsp" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span><b>&nbsp;&nbsp;Adicionar uma ação</b></a>
                    </div>
            </div>
        </div>
    </body>
</html>
