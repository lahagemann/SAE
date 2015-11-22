<%-- 
    Document   : ErrorNoRoomWithChosenNumber
    Created on : Nov 22, 2015, 6:50:12 PM
    Author     : Luiza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    </head>
    <body>
        <br>
        <br>
        <br>
        <div class="container">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="well">
                    <h2 align="center"><b>Erro: a sala selecionada não está cadastrada no sistema.</b></h2>
                    <br>
                    <h3 align="center">Por favor, escolha outra sala. Se esta sala existe e este erro persistir, contate o administrador.</h3>
                    <br>
                    <div align="center">
                        <a href="AdminManage.jsp" class="btn btn-default"><b>Leve-me de volta para o gerenciamento</b></a>
                    </div>
                    <div align="center">
                        <a href="AdminHome.jsp" class="btn btn-default"><b>Leve-me de volta para a tela inicial</b></a>
                    </div>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>
    </body>
</html>
