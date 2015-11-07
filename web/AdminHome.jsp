<%-- 
    Document   : home_gerente
    Created on : Oct 11, 2015, 4:38:32 PM
    Author     : Luiza
--%>

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
                        <li><a href="#">Page 1</a></li>
                        <li><a href="#">Page 2</a></li> 
                        <li><a href="#">Page 3</a></li> 
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <br>
        <br>
        <br>
        <br>

        <h2 align="center">Olá, <%= session.getAttribute("name")%></h2>

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
                                <button type="button" class="btn btn-danger" id="btn"><b>Meu consumo</b></button>
                            </td>
                            <td>
                                <form action="ListAllResources.jsp">
                                    <input type="submit" class="btn btn-danger" id="btn" value="Ligar/Desligar recurso">
                                </form>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button type="button" class="btn btn-danger" id="btn"><b>Gerar relatório de consumo do dia</b></button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" id="btn"><b>Ações personalizadas</b></button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form action="Manage.jsp">
                                    <button type="submit" class="btn btn-warning" id="btn"><b>Gerenciar <br>recurso/sala/funcionário</b></button>
                                </form>
                              
                                
                            </td>
                            <td>
                                <button type="button" class="btn btn-warning" id="btn"><b>Atualizar manutenção de recurso</b></button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button type="button" class="btn btn-warning" id="btn"><b>Nova meta de consumo</b></button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-warning" id="btn"><b>Alterar valor de bônus</b></button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
