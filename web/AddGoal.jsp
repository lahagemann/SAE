<%-- 
    Document   : GoalForm
    Created on : Nov 6, 2015, 9:32:08 PM
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
        <h2>Inserir nova meta</h2>
        <br>
        <br>
        <form class="form-horizontal" role="form" method="post" action="./manage">
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Data:</label>
                <div class="col-sm-10">
                    <input type="date" class="form-control" placeholder="Digite o dia da meta" name="name">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="cpf">Valor:</label>
                <div class="col-sm-10">
                    <input type="number" class="form-control" placeholder="Digite o valor da meta" name="cpf">
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
