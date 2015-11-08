<%-- 
    Document   : ManageCustomAction
    Created on : Nov 8, 2015, 12:56:12 AM
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
        <div class="container">
            <div align="center" id="block">
                    <h3>Ação personalizada</h3>
                    <div class="btn-group" align="center">
                        <a href="#" class="btn btn-primary"><b>Visualizar minhas ações</b></a>
                        <a href="#" class="btn btn-primary"><b>Adicionar uma ação</b></a>
                    </div>
            </div>
        </div>
    </body>
</html>
