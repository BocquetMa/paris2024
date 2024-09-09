<%-- 
    Document   : ajouterPompier
    Created on : 18 mars 2024, 13:30:47
    Author     : zakina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sio.paris2024.model.Site"%>
<%@page import="sio.paris2024.form.FormSite"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>paris 2024</title>
    </head>
    <body>
        <h1>NOUVEAU SITE</h1>
        
            <%
                FormSite form = (FormSite)request.getAttribute("form");
            %>
        
        <form class="form-inline" action="ajouter" method="POST">
                <label for="nom">NOM : </label>
                <input id="nom" type="text" name="nom"  size="30" maxlength="30">
                </br>
                            
                <%-- Champ Liste des pays --%>
                <label for="ville">Ville : </label>
                <input id="ville" type="text" name="ville"  size="30" maxlength="30">
                </br>            
                               
            <input type="submit" name="valider" id="valider" value="Valider"/>
            </form>
        
        
        
        
    </body>
</html>