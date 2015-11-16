<%-- 
    Document   : home_gerente
    Created on : Oct 11, 2015, 4:38:32 PM
    Author     : Luiza
--%>

<%@page import="application.Domain.Goal"%>
<%@page import="application.Domain.Room"%>
<%@page import="application.Impl.AdminServiceImpl"%>
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
        <br>
        <br>
        <br>

        <!-- /container -->
        <div class="container">
            <div align="center" id="block">
                <table>
                    <colgroup> 
                        <col span="2">
                    </colgroup>
                    <tbody>
                        <tr>
                            <td>
                                <form method="post" action="AdminActionMenu.jsp">
                                    <button type="submit" class="btn btn-primary" id="btn"><b>Ligar/Desligar recurso</b></button>
                                </form>
                            </td>
                            <td>
                                <form action="ManageCustomAction.jsp">
                                    <button type="submit" class="btn btn-primary" id="btn"><b>Ações personalizadas</b></button>
                                </form>
                                
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button type="button" class="btn btn-primary" id="btn"><b>Relatórios</b></button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-primary" id="btn"><b>Atualizar manutenção</b></button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form action="Manage.jsp">
                                    <button type="submit" class="btn btn-primary" id="btn"><b>Gerenciar <br>recurso/sala/funcionário</b></button>
                                </form>  
                            </td>
                            <td>
                                <form action="AdminAddBonus.jsp">
                                    <button type="submit" class="btn btn-primary" id="btn"><b>Atribuir bônus</b></button>
                                </form>
                            </td>
                        </tr>
                </table>
            </div>
        </div>
    </body>
</html>
