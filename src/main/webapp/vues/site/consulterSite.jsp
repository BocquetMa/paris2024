<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="sio.paris2024.model.Site" %>
<%@ page import="sio.paris2024.model.Sport" %>
<%@ page import="sio.paris2024.model.Epreuve" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>PARIS 2024</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
        }
        .site-container {
            background: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            overflow: hidden;
            display: flex;
            margin-bottom: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            align-items: center;
            justify-content: center;
            max-width: 800px; /* Centered container with max width */
            margin-left: auto;
            margin-right: auto;
            padding: 10px;
        }
        .site-image {
            width: 300px; /* Larger width for the image */
            height: 200px; /* Larger height for the image */
            object-fit: cover; /* Ensures image covers the area without distortion */
            border-right: 2px solid #ddd;
        }
        .site-info {
            padding: 15px;
            flex: 1;
        }
        .site-info h1 {
            margin: 0 0 10px;
            font-size: 28px; /* Larger font size for the site name */
            font-weight: bold;
        }
        .site-info p {
            margin: 5px 0;
            font-size: 18px;
            color: #555;
        }
        .site-info .site-city {
            font-weight: normal;
            color: #777;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table td {
            padding: 8px;
            border-bottom: 1px solid #ddd;
        }
    </style>
</head>
<body>
    <%
        Site s = (Site) request.getAttribute("pSite");
        if (s == null) {
    %>
        <p>Site non trouvé.</p>
    <%
        } else {
            String imageName = s.getImage(); // Appel de méthode avec parenthèses
            String imageUrl = (imageName != null && !imageName.isEmpty()) ? 
                               request.getContextPath() + "/vues/image/" + imageName : 
                               "https://via.placeholder.com/300x200";
    %>
        <div class="site-container">
            <img src="<%= imageUrl %>" alt="Image de <%= s.getNom() %>" class="site-image" />
            <div class="site-info">
                <h1><%= s.getNom() %></h1>
                <p class="site-city">Ville: <%= s.getVille() %></p>
            </div>
        </div>
        
        <table>
            
            <tr>
                <td>  
                    <% if (s.getLesSports() != null && !s.getLesSports().isEmpty()) { %>
                        <% for (Sport sp : s.getLesSports()) { %>
                        <a href="../ServletSport/consulter?idSport=<%= sp.getId() %>" target="_blank">
                            <%= sp.getId() %> <%= sp.getNom() %><br>
                        <% } %></a>
                    <% } else { %>
                        Aucun sport disponible pour ce site.
                    <% } %>
                    </td>
                
            </tr>
        </table>
    <%
        }
    %>
</body>
</html>
