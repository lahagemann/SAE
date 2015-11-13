<%-- 
    Document   : AddCustomAction
    Created on : Nov 13, 2015, 2:32:52 PM
    Author     : Luiza
--%>

<%@page import="application.Domain.Resource"%>
<%@page import="java.util.List"%>
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
                        <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <%
            AdminService service = new AdminServiceImpl();
            Employee e = (Employee) session.getAttribute("user");
            Room eRoom = service.findRoom(e.getWorkRoomID());
            Goal g = new Goal(null, 0);// = service.findGoal();            
%>
        <div align="center" class="well">
            <font color="black" size="4"><b>Saldo:</b> <%= eRoom.getCreditAmount()%></font> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <font color="black" size="4"><b>Meta:</b> <%= g.getValue()%> </font>              
        </div>
        <br>
        <h3 align="center">Adicionar ação personalizada</h3>
        
        <% List<Room> rooms = service.listAllRooms(); %>
        
        <form class="form-horizontal" role="form" method="post" action="">
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Nome:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Digitar um nome para a ação" name="name">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Recursos:</label>
                <div class="col-sm-10">
                    <% for (Room room : rooms) {%>

                    <h3 align="center">Sala <%= room.getIdentifier()%></h3>

                    <table class="table table-hover" align="center">
                        <tr class="danger">
                            <td><b>Incluir</b></td>
                            <td><b>Nome do recurso</b></td>
                            <td><b>Tipo do recurso</b></td>
                        </tr>
                        <% for (Resource r : room.getResourceList()) {%>
                        <tr>
                            <td><input type="checkbox" name="resources" value="<%= r.getIdentifier()%>"></td>
                            <td><%= r.getName()%></td>
                            <td><%= r.getType()%></td>                      
                        </tr>
                        <% } %>
                    </table>
                    <% }%>
                </div>
            </div>
            <div class="form-group"> 
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Criar</button>
                </div>
            </div>
        </form>
        
    </body>
</html>
