<%-- 
    Document   : login
    Created on : May 27, 2017, 9:21:49 PM
    Author     : wewlad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ro">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Profesor</title>
    </head>
    <body>
        <h1>Login Profesor</h1>
        <p>Introduceti datele de administrator</p>
        <form action = "${pageContext.request.contextPath}/login" method = "POST">
         Username: <input type = "text" name = "username">
         <br />
         Parola: <input type = "password" name = "password" />
         <input type = "submit" value = "Submit" />
        </form>
    </body>
</html>
