<%-- 
    Document   : EmployeeForm
    Created on : Nov 6, 2015, 5:28:01 PM
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
        
        <h2>Inserir novo funcionário</h2>
        <br>
        <br>
        <form class="form-horizontal" role="form" method="post" action="./manage_employee">
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Nome:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Digitar o nome do funcionário" name="name">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="cpf">CPF:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Digitar o cpf do funcionário" name="cpf">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="email">Email:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Digitar o email do funcionário" name="email">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="password">Senha:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Digite uma senha para o funcionário" name="password">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="room">Sala:</label>
                <div class="col-sm-10">
                    <input type="number" min="1" step="1" class="form-control" placeholder="Digite o número da sala do funcionário" name="room">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">Tipo:</label>
                <div class="col-sm-10">
                    <label class="radio-inline"><input type="radio" name="type" value="admin">Admin</label>
                    <label class="radio-inline"><input type="radio" name="type" value="employee">Funcionário</label>
                </div>
            </div>
            <div class="form-group"> 
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Submit</button>
                </div>
            </div>
        </form>
    </body>
</html>
