<%-- 
    Document   : listerSites
    Author     : matheo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sio.paris2024.model.Site"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PARIS 2024</title>
        <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
        
        <title>Paris 2024</title>

        <style>
            body {
		padding-top: 50px;
            }
                .special {
		padding-top:50px;
	}
        </style>
    </head>
    <body>
      
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a  href ='../ServletSite/lister' class="navbar-brand" href=".">Système de gestion des sites</a>
			</div>
		</div>
	</nav>
    <body>
       <div class="container special">
            <h2 class="h2">Liste des sites</h2>
		<div class="table-responsive">
                <%
                    ArrayList<Site> lesSites = (ArrayList)request.getAttribute("sLesSites");
                %>
                <table class="table table-striped table-sm">  
                <thead>
                    <tr>             
                        <th>id</th>
                        <th>nom</th> 
                        <th>ville</th>                
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <%
                            for (Site s : lesSites)
                            {              
                                out.println("<tr><td>");
                                out.println(s.getId());
                                out.println("</td>");

                                out.println("<td><a href ='../ServletSite/consulter?idSite="+ s.getId()+ "'>");
                                out.println(s.getNom());
                                out.println("</a></td>"); 
                                
                                out.println("<td><a href ='../ServletSite/consulter?idSite="+ s.getId()+ "'>");
                                out.println(s.getVille());
                                out.println("</a></td>"); 
                               
                            }
                        %>
                    </tr>
                </tbody>
            </table>
        </body>
         </div>
       </div>
  
</html>
