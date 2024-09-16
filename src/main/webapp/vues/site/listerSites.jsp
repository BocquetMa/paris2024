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
    <style>
        body {
            padding-top: 50px;
            background-color: #f5f5f5;
        }
        .special {
            padding-top: 50px;
        }
        .site-card {
            text-align: center;
            margin-bottom: 30px;
        }
        .site-image {
            width: 220px;
            height: 150px;
            border-radius: 10%;
            object-fit: cover;
            cursor: pointer;
            transition: transform 0.3s ease;
        }
        .site-image:hover {
            transform: scale(1.1);
        }
        .site-name {
            font-weight: bold;
        }
        .site-city {
            color: #555;
        }
        .container {
            margin: 0 auto;
            max-width: 1200px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a href="../ServletSite/lister" class="navbar-brand">Système de gestion des sites</a>
        </div>
    </div>
</nav>
<div class="container special">
    <h1 class="text-center"><strong>Liste des sites</strong></h1>
    <br><br>
    <div class="row">
        <%
            ArrayList<Site> lesSites = (ArrayList)request.getAttribute("sLesSites");
            int siteCount = 0;
            for (Site s : lesSites) {
                if (siteCount % 8 == 0 && siteCount != 0) {
                    out.println("</div><div class='row'>");
                }

                String imageName = s.getImage();
                String imageUrl = (imageName != null && !imageName.isEmpty()) ? 
                                   request.getContextPath() + "/vues/image/" + imageName : 
                                   "https://via.placeholder.com/150";
        %>
        <div class="col-xs-6 col-sm-4 col-md-3">
            <div class="site-card">
                <a href="../ServletSite/consulter?idSite=<%= s.getId() %>" target="_blank">
                    <img src="<%= imageUrl %>" alt="Image de <%= s.getNom() %>" class="site-image" />
                </a>
                
                <div class="site-name"><%= s.getNom() %></div>
                <div class="site-city"><%= s.getVille() %></div>
            </div>
        </div>
        <%
                siteCount++;
            }
        %>
    </div>
</div>
</body>
</html>
