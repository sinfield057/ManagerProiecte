<%-- 
    Document   : student
    Created on : May 28, 2017, 12:30:01 PM
    Author     : wewlad
--%>

<%@page import="common.ProjectDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
         <table style="width: 100%">
             <tr>
                 <th>Id proiect</th>
                 <th>Titlu</th>
                 <th>Descriere</th>
                 <th>Studenti</th>
                 <th>Stare proiect</th>
             </tr>
         <% ArrayList<ProjectDetails> projects = (ArrayList<ProjectDetails>)request.getAttribute("projects"); %>
         <% if (projects != null) {
             for (ProjectDetails project : projects) {
                %>
                <tr>
                    <td style="border-top: solid black 1pt">
                        <%= project.getIdProiect() %>
                    </td>
                    <td style="border-top: solid black 1pt">
                        <%= project.getTitlu() %>
                    </td>
                    <td style="border-top: solid black 1pt"><%= project.getDescriere() %></td>
                    
                    <% if( project.getNrEchipe() < 2 ) { %>
                    <td style="border-top: solid black 1pt"> 
                        <div>
                            <form method="POST" action="${pageContext.request.contextPath}/addTeam">
                                <input type="hidden" value="<%= project.getNrMaxStudenti() %>" name="nrStudenti" />
                                <input type="hidden" value="<%= project.getIdProiect()%>" name="idProiect" />
                                <% for ( int i = 1; i <= project.getNrMaxStudenti(); i++ ) { %>
                                <p>Student <%= i%></p> 
                                    Nume: <input type = "text" name = "nume<%=i%>">
                                    <br />Prenume: <input type = "text" name = "prenume<%=i%>" />
                                    <br />Email: <input type = "text" name = "email<%=i%>" />
                                <% } %>
                                <br />
                                <br />
                                <input type = "submit" value = "Submit" />
                            </form>
                        </div>
                        <td style="border-top: solid black 1pt">
                            <% if ( project.getNrEchipe() == 1 ) {%>
                                Ales de o echipa
                            <%} else { %>
                                Liber
                            <%}%>
                        </td>
                   <% } else {%>
                    <td style="border-top: solid black 1pt"> Blocat </td>
                    <td style="border-top: solid black 1pt"> Blocat (ales de doua echipe)</td>
                    <% } %>
                    
                </tr><%
             }
           }
        %>
         </table>
    </body>
</html>
