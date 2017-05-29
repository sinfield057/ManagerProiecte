<%-- 
    Document   : teamStatus
    Created on : May 30, 2017, 1:05:37 AM
    Author     : wewlad
--%>

<%@page import="common.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Team Status</title>
    </head>
    <body>
        <% ArrayList< Student > students = ( ArrayList< Student > ) request.getAttribute( "students" ); 
           if ( students.size() > 0 ) { %>
           <h1> <%= request.getAttribute("titlu")  %></h1>
           <% for ( Student s : students ) { %>
           <div>
               <% String apendNew = s.getNume() + " " + s.getPrenume() + " - " + s.getNrPrezente(); %>
               <p> <%= apendNew %> </p>
           </div>
           <%}%>
        <%}%>
    </body>
</html>
