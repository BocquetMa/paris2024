<%-- 
    Document   : consulterSite
    Author     : bocquet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sio.paris2024.model.Site"%>
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
                Site s = (Site)request.getAttribute("sSite");
        %>
       
        
        <h1><%  out.println(s.getNom());%></h1>
            
                         
            <table>
            <tr>
                <td>Id: </td><td><%  out.println(s.getId());%></td>
            </tr>
            <tr>
                <td>Vile : </td><td><%  out.println(s.getVille());%></td>
            </tr>
        </table>
</html>
