<%-- 
    Document   : EmployeeAddCustomAction
    Created on : Nov 16, 2015, 3:42:02 PM
    Author     : Luiza
--%>

<%@page import="application.Domain.Resource"%>
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
                        <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <%
            EmployeeService service = new EmployeeServiceImpl();
            Employee e = (Employee) session.getAttribute("user");
            Room room = service.findRoom(e.getWorkRoomID());
            Goal g = service.findGoal(room.getDailyGoal().getIdentifier());           
%>
        <div align="center" class="well">
            <font color="black" size="4"><b>Saldo:</b> <%= room.getCreditAmount()%></font> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <font color="black" size="4"><b>Meta:</b> <%= g.getValue()%> </font>              
        </div>
        <br>
        <h2 align="center">Adicionar ação personalizada</h2>
        <br>
        <br>

        <div class="container">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <% List<Resource> resources = service.listResorcesWorkRoom(e.getWorkRoomID()); %>

                <form class="form-horizontal" role="form" method="post" action="./employee_add_action">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="name">Nome:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" placeholder="Digitar um nome para a ação" name="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="name">Recursos:</label>
                        <div class="col-sm-10">
                            <table class="table table-hover" align="center">
                                <tr class="danger">
                                    <td><b>Incluir</b></td>
                                    <td><b>Nome do recurso</b></td>
                                    <td><b>Tipo do recurso</b></td>
                                </tr>
                                <% for (Resource r : resources) {%>
                                <tr>
                                    <td><input type="checkbox" name="resources" value="<%= r.getIdentifier()%>"></td>
                                    <td><%= r.getName()%></td>
                                    <td><%= r.getType()%></td>                      
                                </tr>
                                <% }%>
                            </table>
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
