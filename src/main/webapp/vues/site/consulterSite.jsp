<%-- 
    Document   : consulterSite
    Author     : bocquet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sio.paris2024.model.Site"%>
<%@page import="sio.paris2024.model.Sport"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PARIS 2024</title>
    </head>
    <body>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PARIS 2024</title>
    </head>
    <body>
        <%
                Site s = (Site)request.getAttribute("pSite");
        %>
       
        
        <h1><%  out.println(s.getNom());%></h1>
            
                         
            <table>
            <tr>
                <td>Id: </td><td><%  out.println(s.getId());%></td>
            </tr>
            <tr>
                <td>Ville : </td><td><%  out.println(s.getVille());%></td>
            </tr>
            <tr>
                <td>
                    <% if (s.getLesSports() != null && !s.getLesSports().isEmpty()) { %>
    <% for (Sport sp : s.getLesSports()) { %>
        <%= sp.getId() %> <%= sp.getNom() %>
    <% } %>
<% } else { %>
    <p>Aucun sport disponible pour ce site.</p>
<% } %>
                </td>
            </tr>
        </table>
</html>
