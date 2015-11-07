<%-- 
    Document   : RoomForm
    Created on : Nov 6, 2015, 8:38:46 PM
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
        <h2>Inserir nova sala</h2>
        <br>
        <br>
        <form class="form-horizontal" role="form" method="post" action="./manage_room">
            <div class="form-group">
                <label class="control-label col-sm-2" for="number">Sala:</label>
                <div class="col-sm-10">
                    <input type="number" min="1" step="1" class="form-control" placeholder="Digite o número da sala do funcionário" name="number">
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
