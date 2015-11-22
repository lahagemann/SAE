<%-- 
    Document   : EmployeeHome
    Created on : Nov 2, 2015, 11:53:11 PM
    Author     : Luiza
--%>

<%@page import="application.Domain.Goal"%>
<%@page import="application.Domain.Room"%>
<%@page import="application.Domain.Employee"%>
<%@page import="application.Interface.EmployeeService"%>
<%@page import="application.Impl.EmployeeServiceImpl"%>
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
        <!-- /container -->
        <div class="container" align="center">
            <div align="center" id="block">
                <table class="button_table" align="center">
                    <tr>
                        <td class="col-md-2"></td>
                        <td class="col-md-2"></td>
                        <td class="col-md-2">
                            <form method="post" action="EmployeeListResources.jsp">
                                <button type="submit" class="btn btn-default" id="btn"><span class="glyphicon glyphicon-off"></span><b>&nbsp;&nbsp;Ligar/Desligar recurso</b></button>
                            </form>
                        </td>
                        <td>
                            <form action="EmployeeManageCustomAction.jsp">
                                <button type="submit" class="btn btn-primary" id="btn"><span class="glyphicon glyphicon-user"></span><b>&nbsp;&nbsp;Ações personalizadas</b></button>
                            </form>
                        </td>
                </table>
            </div>
        </div>
    </body>
</html>
