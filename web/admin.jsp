<%-- 
    Document   : admin
    Created on : May 27, 2017, 11:42:41 PM
    Author     : ovidiugiorgi
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="common.ProjectDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<!DOCTYPE html>
<html lang="ro">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="<c:url value="/style.css" />" />
        <title>Administrare Proiecte</title>
    </head>
    <body>
        <h1>Administrare proiecte</h1>
        <h2>Prezenta studenti</h2>
        <p>Adauga student / adauga prezenta daca studentul deja este inregistrat: </p>
        <form action = "${pageContext.request.contextPath}/addStudent" method = "POST">
         Nume: <input type = "text" name = "nume">
         <br />
         Prenume: <input type = "text" name = "prenume" />
         <br />
         Email: <input type = "text" name = "email" />
         <input type = "submit" value = "Submit" />
        </form>
         
         <hr/>
         
         <h2>Adaugare proiect</h2>
         <p>Creeaza proiect nou:</p>
         <form action = "${pageContext.request.contextPath}/addProject" method = "POST" id="projectForm">
         Titlu proiect: <input type = "text" name = "titlu">
         <br />
         <textarea rows="6" cols="50" name="descriere" form="projectForm">Descriere proiect...</textarea>
         <br />
         Nr. maxim studenti / echipa <input type = "text" name = "nr_max_studenti" />
         <input type = "submit" value = "Submit" />
         </form>
         
         <hr/>
         
         <h2>Lista proiecte</h2>
         <table style="width: 100%">
             <tr>
                 <th>Id proiect</th>
                 <th>Titlu</th>
                 <th>Descriere</th>
                 <th>Nr. max studenti / echipa</th>
                 <th>Nr. echipe inscrise</th>
             </tr>
         <% ArrayList<ProjectDetails> projects = (ArrayList<ProjectDetails>)request.getAttribute("projects"); %>
         <% if (projects != null) {
             for (ProjectDetails project : projects) {
                %>
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/projectInfo?id_proiect=<%= project.getIdProiect() %>&titlu=<%= project.getTitlu() %>">
                        <%= project.getIdProiect() %>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/projectInfo?id_proiect=<%= project.getIdProiect() %>&titlu=<%= project.getTitlu() %>">
                        <%= project.getTitlu() %>
                    </td>
                    <td><%= project.getDescriere() %></td>
                    <td><%= project.getNrMaxStudenti() %></td>
                    <td><%= project.getNrEchipe() %></td>
                </tr><%
             }
           }
        %>
         </table>
    </body>
</html>
