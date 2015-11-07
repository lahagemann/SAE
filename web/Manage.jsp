<%-- 
    Document   : Manage
    Created on : Nov 6, 2015, 11:36:25 PM
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
            <div align="center" id="block">
                <table>
                    <colgroup> 
                        <col span="2">
                    </colgroup>
                    <h2>Funcionário</h2>
                    <tbody>                      
                        <tr>
                            <td>
                                <form action="EmployeeForm.jsp">
                                    <button type="submit" class="btn btn-danger" id="btn"><b>Adicionar</b></button>
                                </form>
                            </td>
                            <td>
                                <form action="ListAllResources.jsp">
                                    <button type="submit" class="btn btn-danger" id="btn"><b>Alterar</b></button>
                                </form>
                            </td>
                            <td>
                                <form action="ListAllResources.jsp">
                                    <button type="submit" class="btn btn-danger" id="btn"><b>Excluir</b></button>
                                </form>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <h2>Sala</h2>
                <table>
                    <tbody>                        
                        <tr>
                            <td>
                                <form action="RoomForm.jsp">
                                    <button type="submit" class="btn btn-danger" id="btn"><b>Adicionar</b></button>
                                </form>
                            </td>
                            <td>
                                <form action="ListAllResources.jsp">
                                    <button type="submit" class="btn btn-danger" id="btn"><b>Alterar</b></button>
                                </form>
                            </td>
                            <td>
                                <form action="ListAllResources.jsp">
                                    <button type="submit" class="btn btn-danger" id="btn"><b>Excluir</b></button>
                                </form>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <h2>Meta</h2>
                <table>
                    <tbody>
                        <tr>
                            <td>
                                <form action="GoalForm.jsp">
                                    <button type="submit" class="btn btn-danger" id="btn"><b>Adicionar</b></button>
                                </form>
                            </td>
                            <td>
                                <form action="ListAllResources.jsp">
                                    <button type="submit" class="btn btn-danger" id="btn"><b>Alterar</b></button>
                                </form>
                            </td>
                            <td>
                                <form action="ListAllResources.jsp">
                                    <button type="submit" class="btn btn-danger" id="btn"><b>Excluir</b></button>
                                </form>
                            </td>
                        </tr>
                    </tbody>
                </table>
                        
                        
                <h2>Recurso</h2>
                <table>
                    <tbody>
                        <tr>
                            <td>
                                <form action="ResourceForm.jsp">
                                    <button type="submit" class="btn btn-danger" id="btn"><b>Adicionar</b></button>
                                </form>
                            </td>
                            <td>
                                <form action="ListAllResources.jsp">
                                    <button type="submit" class="btn btn-danger" id="btn"><b>Alterar</b></button>
                                </form>
                            </td>
                            <td>
                                <form action="ListAllResources.jsp">
                                    <button type="submit" class="btn btn-danger" id="btn"><b>Excluir</b></button>
                                </form>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
