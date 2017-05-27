<%-- 
    Document   : admin
    Created on : May 27, 2017, 11:42:41 PM
    Author     : ovidiugiorgi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrare Proiecte</title>
    </head>
    <body>
        <h1>Administrare proiecte</h1>
        <h2>Prezenta studenti</h2>
        <p>Adauga student / adauga prezenta daca studentul deja este inregistrat: </p>
        <form action = "${pageContext.request.contextPath}/addStudent" method = "POST">
         First Name: <input type = "text" name = "username">
         <br />
         Last Name: <input type = "password" name = "password" />
         <br />
         Email: <input type = "text" name = "email" />
         <input type = "submit" value = "Submit" />
      </form>
    </body>
</html>
