<%-- 
    Document   : projectInfo
    Created on : May 28, 2017, 4:47:59 PM
    Author     : ovidiugiorgi
--%>

<%@page import="common.Student"%>
<%@page import="common.Team"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="<c:url value="/style.css" />" />
        <title>Informatii Proiect</title>
    </head>
    <body>
        <h1>Informatii proiect</h1>
        <h2><%= request.getParameter("titlu") %></h2>
        
        <% ArrayList<Team> echipe = (ArrayList<Team>) request.getAttribute("echipe"); %>
        <% if (echipe != null) {
            if (echipe.size() == 0) {
                %><h3>Nicio echipa nu este inregistrata pentru acest proiect.</h3><%
            } else {
                int nr_echipa = 1;
                for (Team echipa : echipe) {
                    %><h3>Studenti echipa <%= nr_echipa %></h3><%
                    
                    ArrayList<Student> studenti = echipa.getStudenti();
                    %>
                    <table style="width: 100%">
                        <tr>
                            <th>Nume</th>
                            <th>Prenume</th>
                            <th>Email</th>
                            <th>Prezente</th>
                        </tr>
                        <%
                        for (Student student : studenti) {
                            %>
                            <tr>
                                <td><%= student.getNume() %></td>
                                <td><%= student.getPrenume() %></td>
                                <td><%= student.getEmail() %></td>
                                <td><%= student.getNrPrezente() %></td>
                            </tr>
                            <%
                        }
                        %>
                    </table>
                <%
                    nr_echipa++;
                }
            }
        }
        %>

    </body>
</html>
