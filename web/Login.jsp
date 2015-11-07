<%-- 
    Document   : Login
    Created on : Nov 2, 2015, 6:18:01 PM
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
        <div class="container">
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <form method="post" action="./login">
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon1">Email:</span>
                    <input type="text" class="form-control" placeholder="Digite seu email" name="email">
                </div>
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon2">Senha:</span>
                    <input type="password" class="form-control" placeholder="Digite sua senha" name="password">
                </div>
                <div>
                    <input type="submit" class="btn btn-danger" id="btn2" value="Login">
                </div>
            </form>   
        </div>
    </body>
</html>
