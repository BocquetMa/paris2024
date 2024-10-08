<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="sio.paris2024.model.Athlete" %>
<%@ page import="sio.paris2024.model.Pays" %>
<%@ page import="java.util.ArrayList" %>
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
            width: 150px;
            height: 150px;
            border-radius: 50%;
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
            <a href="../ServletAthlete/lister" class="navbar-brand">Système de gestion des athlètes</a>
        </div>
    </div>
</nav>
<div class="container special">
    <h2 class="text-center">Liste des athlètes</h2>
    <div class="row">
        <%
            ArrayList<Athlete> lesAthletes = (ArrayList<Athlete>) request.getAttribute("pLesAthletes");
            if (lesAthletes != null) {
                int siteCount = 0;
                for (Athlete a : lesAthletes) {
                    if (siteCount % 8 == 0 && siteCount != 0) {
                        out.println("</div><div class='row'>");
                    }

                    String imageName = a.getImage(); // Correction : Utilisation de 'a' au lieu de 's'
                    String imageUrl = (imageName != null && !imageName.isEmpty()) ? 
                                       request.getContextPath() + "/vues/athlete/image/" + imageName : 
                                       "https://via.placeholder.com/150";
        %>
        <div class="col-xs-6 col-sm-4 col-md-3">
            <div class="site-card">
                <a href="../ServletAthlete/consulter?idAthlete=<%= a.getId() %>" target="_blank">
                    <img src="<%= imageUrl %>" alt="Image de <%= a.getNom() %>" class="site-image" />
                </a>
                <div class="site-name"><%= a.getNom() %></div>
                <div class="site-city"><%= a.getPays().getNom() %></div>
            </div>
        </div>
        <%
                    siteCount++;
                }
            }
        %>
    </div>
</div>
</body>
</html>
