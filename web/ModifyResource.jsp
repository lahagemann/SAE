<%-- 
    Document   : ModifyResource
    Created on : Nov 8, 2015, 11:37:31 PM
    Author     : Luiza
--%>

<%@page import="application.Domain.Resource"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SAE</title>
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    </head>
    <body>
        <br>
        <br>
        <% Resource r = (Resource) request.getAttribute("resource"); %>
        
        <form class="form-horizontal" role="form" method="post" action="./modify_resource">
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Nome:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Digitar o nome identificador do recurso (ex.: LMP00001)" name="name" value="<%= r.getName() %>">
                    <input type="hidden" name="id" value="<%= r.getIdentifier() %>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="type">Tipo:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Digitar o tipo de recurso (ex.: Lâmpada)" name="type" value="<%= r.getType()%>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="consumption">Consumo:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Digitar o consumo em KWh do recurso (ex.: 0.01)" name="consumption" value="<%= r.getConsumption() %>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="room">Sala:</label>
                <div class="col-sm-10">
                    <input type="number" min="1" step="1" class="form-control" placeholder="Selecione a sala onde será colocado o recurso" name="room" value="<%= r.getLocationID()%>">
                </div>
            </div>
            <div class="form-group"> 
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Alterar</button>
                </div>
            </div>
        </form>
    </body>
</html>
