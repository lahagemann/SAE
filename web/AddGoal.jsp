<%-- 
    Document   : GoalForm
    Created on : Nov 6, 2015, 9:32:08 PM
    Author     : Luiza
--%>

<%@page import="application.Domain.Room"%>
<%@page import="application.Domain.Goal"%>
<%@page import="application.Impl.AdminServiceImpl"%>
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
        <h2 align="center">Inserir nova meta</h2>
        <br>
        <br>
        <div class="container">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <form class="form-horizontal" role="form" method="post" action="./add_goal">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="date">Data:</label>
                        <div class="col-sm-10">
                            <input type="date" class="form-control" placeholder="Digite o dia da meta" name="date">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="value">Valor:</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" placeholder="Digite o valor da meta" name="value">
                        </div>
                    </div>
                    <div class="form-group"> 
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default" style="width: 100%;"><span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;<b>Criar</b></button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-3"></div>
        </div>

    </body>
</html>
