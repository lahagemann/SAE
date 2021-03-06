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
        <link href="css/sae.css" rel="stylesheet">
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
        <br>
        <br>
        <br>

        <!-- /container -->
        <div class="container" align="center">
            <div align="center" id="block">
                <table class="button_table" align="center">
                        <tr>
                            <td class="col-md-2"></td>
                            <td class="col-md-2"></td>
                            <td class="col-md-2">
                                <form method="post" action="AdminActionMenu.jsp">
                                    <button type="submit" class="btn btn-default" id="home_btn"><span class="glyphicon glyphicon-off"></span><b>&nbsp;&nbsp;Ligar/Desligar Recursos</b></button>
                                </form>
                            </td>
                            <td class="col-md-2">
                                <form action="AdminManageCustomAction.jsp">
                                    <button type="submit" class="btn btn-default" id="home_btn"><span class="glyphicon glyphicon-user"></span><b>&nbsp;&nbsp;Ações Personalizadas</b></button>
                                </form>
                                
                            </td>
                            <td class="col-md-2"></td>
                            <td class="col-md-2"></td>
                        </tr>
                        <tr>
                            <td class="col-md-2"></td>
                            <td class="col-md-2"></td>
                            <td class="col-md-2">
                                <form action="AdminSelectReportDate.jsp">
                                    <button type="submit" class="btn btn-default" id="home_btn"><span class="glyphicon glyphicon-file"></span><b>&nbsp;&nbsp;Relatórios</b></button>
                                </form>
                                
                            </td>
                            <td class="col-md-2">
                                <button type="button" class="btn btn-default" id="home_btn"><span class="glyphicon glyphicon-wrench"></span><b>&nbsp;&nbsp;Atualizar Manutenção</b></button>
                            </td>
                            <td class="col-md-2"></td>
                            <td class="col-md-2"></td>
                        </tr>
                        <tr>
                            <td class="col-md-2"></td>
                            <td class="col-md-2"></td>
                            <td class="col-md-2">
                                <form action="AdminManage.jsp">
                                    <button type="submit" class="btn btn-default" id="home_btn"><span class="glyphicon glyphicon-cog"></span><b>&nbsp;&nbsp;Gerenciar Dados</button>
                                </form>  
                            </td>
                            <td class="col-md-2">
                                <form action="AdminAddBonus.jsp">
                                    <button type="submit" class="btn btn-default" id="home_btn"><span class="glyphicon glyphicon-gift"></span><b>&nbsp;&nbsp;Atribuir Bônus</b></button>
                                </form>
                            </td>
                            <td class="col-md-2"></td>
                            <td class="col-md-2"></td>
                        </tr>
                </table>
            </div>
        </div>
    </body>
</html>
