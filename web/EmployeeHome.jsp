<%-- 
    Document   : EmployeeHome
    Created on : Nov 2, 2015, 11:53:11 PM
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
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="#">Page 1</a></li>
                        <li><a href="#">Page 2</a></li> 
                        <li><a href="#">Page 3</a></li> 
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <br>
        <br>
        <br>
        <br>

        <h2 align="center">Olá, ${name}</h2>

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
                                <button type="button" class="btn btn-danger" id="btn1"><b>Meu consumo</b></button>
                            </td>
                            <td>
                                <form method="post" action="./employee_resources">
                                    <input type="submit" class="btn btn-danger" id="btn2" value="Ligar/Desligar recurso">
                                </form>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button type="button" class="btn btn-danger" id="btn4"><b>Minhas metas de consumo</b></button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" id="btn4"><b>Ações personalizadas</b></button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
