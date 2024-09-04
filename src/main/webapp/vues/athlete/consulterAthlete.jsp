<%-- 
    Document   : listerAthletes
    Created on : 14 août 2024, 12:27:51
    Author     : zakina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sio.paris2024.model.Athlete"%>
<%@page import="sio.paris2024.model.Pays"%>
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
                Athlete a = (Athlete)request.getAttribute("pAthlete");
        %>
       
        
        <h1><%  out.println(a.getNom());%></h1>
            
                         
            <table>
            <tr>
                <td>Id: </td><td><%  out.println(a.getId());%></td>
            </tr>
            <tr>
                <td>Prénom : </td><td><%  out.println(a.getPrenom());%></td>
            </tr>
            <tr>
                <td>Date de naissance : </td><td><%  out.println(a.getDateNaiss());%></td>
            </tr>
            <tr>
                <td>Pays : </td><td><%  out.println(a.getPays().getNom());%></td>
            </tr>
        </table>
</html>
